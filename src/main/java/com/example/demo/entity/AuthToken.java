package com.example.demo.entity;

import java.util.Optional;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthToken {
    private String userId;

    public static Optional<AuthToken> fromSession(HttpSession session) {
        if (session.getAttribute("auth") instanceof AuthToken auth) {
            return Optional.of(auth);
        } else {
            return Optional.empty();
        }
    }
}
