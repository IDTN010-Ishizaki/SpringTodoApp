package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.component.PasswordEncrypter;
import com.example.demo.dto.RegisterForm;
import com.example.demo.entity.User;
import com.example.demo.exception.PasswordMismatchException;
import com.example.demo.exception.UserDuplicateException;
import com.example.demo.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncrypter encrypter;

    public UserService(UserRepository repository, PasswordEncrypter encrypter) {
        this.repository = repository;
        this.encrypter = encrypter;
    }

    public User register(RegisterForm form) {

        Optional<User> existingUser = repository.findById(form.getId());

        if (existingUser.isPresent()) {
            throw new UserDuplicateException(form.getId());
        }

        if (!form.getPassword().equals(form.getPasswordRetype())) {
            throw new PasswordMismatchException();
        }

        String encryptPassword = encrypter.encode(form.getPassword());

        User registeredUser = repository.save(new User(form.getId(), encryptPassword));

        return registeredUser;
    }
}
