package com.picpaysimplificado.services;

import java.math.BigDecimal;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dtos.TransactionDTO;
import com.picpaysimplificado.repositories.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;
    
    @Autowired
    private TransactionRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public void createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());
        
        if(!this.authorizeTransaction(sender, transaction.value())) {
            throw new Exception("Transação não autorizada");
        }
        // if (Long.valueOf(sender.getId()).equals(Long.valueOf(receiver.getId()))) {
        //     throw new Exception("Usuário não pode transferir para ele mesmo");
        // }
    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {
        ResponseEntity<Map> authorizationresponse = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc",
        Map.class);

        if(authorizationresponse.getStatusCode() == HttpStatus.OK) {
            String message = (String) authorizationresponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else return false;
    }
}
