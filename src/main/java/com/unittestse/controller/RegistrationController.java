package com.unittestse.controller;

import com.unittestse.model.MyUser;
import com.unittestse.repo.MyUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerErrorException;

@RestController
public class RegistrationController {

    private final MyUserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public RegistrationController(MyUserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register/user")
    public MyUser createUser(@RequestBody MyUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleException(IllegalArgumentException exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ServerErrorException("NPE",exception));
    }



}
