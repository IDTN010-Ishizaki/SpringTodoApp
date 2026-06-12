package com.example.demo.exception;

public class UserDuplicateException extends RuntimeException {

    public UserDuplicateException(String id) {
        super("%sは登録されいてます".formatted(id));
    }

}
