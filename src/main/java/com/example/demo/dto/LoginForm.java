package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginForm {
    @NotBlank(message = "ユーザーIDは必須です")
    private String id;

    @NotBlank(message = "パスワードは必須です")
    private String password;
}
