package com.picpaysimplificado.services;

import java.math.BigDecimal;

import com.picpaysimplificado.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.picpaysimplificado.domain.user.*;
import com.picpaysimplificado.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {

        if(sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Usuário lojista não pode realizar transações");
        }
        
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente");
        }
    }
    
    /**
     *
    public User findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
     * @param user
     */
    public User findUserById(Long id) throws Exception {
        return this.repository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public User findUserByDocument(String document) throws Exception {
        return this.repository.findUserByDocument(document).orElseThrow(() -> new Exception("Documento não encontrado. "));
    }

    public User findByEmailOrDocument(String email, String document) throws Exception {
        return this.repository.findByEmailOrDocument(email, document).orElseThrow(() -> new Exception("Email ou documento não encontrado"));
    }

    public User createUser(UserDTO data) {
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }
    public List<User> getAllUsers() {
        return this.repository.findAll();
    }
    public void saveUser(User user) {
        this.repository.save(user);
    }
}
