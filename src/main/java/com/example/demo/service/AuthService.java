package com.example.demo.service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.component.PasswordEncrypter;
import com.example.demo.entity.AuthToken;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AuthService {
    private final UserRepository repository;
    private final PasswordEncrypter encrypter;

    public AuthService(UserRepository repository, PasswordEncrypter encrypter) {
        this.repository = repository;
        this.encrypter = encrypter;
    }

    public Optional<AuthToken> login(String id, String password) {
        Optional<User> findResult = repository.findById(id);

        log.info(findResult.toString());

        if (findResult.isEmpty()) {
            log.info("login fail");
            return Optional.empty();
        }
        User loginUser = findResult.get();

        log.debug(loginUser.toString());

        if (!loginUser.getPassword().equals(encrypter.encode(password))) {
            return Optional.empty();
        }

        log.info("login success %s".formatted(loginUser.getUserId()));

        AuthToken auth = new AuthToken(loginUser.getUserId());

        return Optional.of(auth);

    }

}
