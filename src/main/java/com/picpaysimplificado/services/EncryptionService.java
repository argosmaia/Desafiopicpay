package com.picpaysimplificado.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EncryptionService {

    private static final String ALGORITHM = "AES";
    private Map<String, SecretKey> keys = new HashMap<>();
    private final Map<String, LocalDateTime> keyCreationTimes = new HashMap<>();

    public String encrypt(String data, String user) throws Exception {
        checkKeyValidity(user);
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, keys.get(user));
        byte[] encValue = c.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encValue);
    }

    public String decrypt(String encryptedData, String user) throws Exception {
        checkKeyValidity(user);
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, keys.get(user));
        byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        return new String(decValue);
    }

    public String encryptTransaction(String data, long sender, long receiver) throws Exception {
        String user = sender + "->" + receiver;
        return encrypt(data, user);
    }

    public String decryptTransaction(String encryptedData, String sender, String receiver) throws Exception {
        String user = sender + "->" + receiver;
        return decrypt(encryptedData, user);
    }

    private void checkKeyValidity(String user) throws NoSuchAlgorithmException {
        if (!keys.containsKey(user) || !keyCreationTimes.containsKey(user) || 
            ChronoUnit.MINUTES.between(keyCreationTimes.get(user), LocalDateTime.now()) > 10) {
            generateKey(user);
        }
    }

    private void generateKey(String user) throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(128); // for example
        SecretKey secretKey = keyGen.generateKey();
        keys.put(user, secretKey);
        keyCreationTimes.put(user, LocalDateTime.now());
    }
}