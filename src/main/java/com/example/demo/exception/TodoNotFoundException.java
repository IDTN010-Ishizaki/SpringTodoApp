package com.example.demo.exception;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(long id) {
        super("タスクが見つかりませんでした id=%d".formatted(id));
    }
}
