package com.picpaysimplificado.security.authentication;

import com.picpaysimplificado.domain.user.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class AuthenticationRequest {
    private String document;
    private String password;
    @Autowired
    private User user;
}
