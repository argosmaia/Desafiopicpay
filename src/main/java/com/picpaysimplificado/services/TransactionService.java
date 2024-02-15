package com.picpaysimplificado.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpaysimplificado.repositories.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;
    
    @Autowired
    private TransactionRepository repository;

    public void createTransaction(TransactionDTO transaction) {
        
    }
}
