package com.example.demo.component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

import org.springframework.stereotype.Component;

@Component
public class PasswordEncrypter {
    private final MessageDigest digest;
    private final HexFormat hexFormat;

    public PasswordEncrypter() throws NoSuchAlgorithmException {
        this.digest = MessageDigest.getInstance("SHA-256");
        this.hexFormat = HexFormat.of().withLowerCase();
    }

    public String encode(String text) {

        return hexFormat.formatHex(digest.digest(text.getBytes()));
    }
}
