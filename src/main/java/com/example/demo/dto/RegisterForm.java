package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterForm {
    @NotBlank(message = "ユーザーIDは必須です")
    private String id;

    @NotBlank(message = "パスワードは必須です")
    @Size(min = 8, message = "パスワードは8文字以上にしてください")
    private String password;

    @NotBlank(message = "再入力は必須です")
    @Size(min = 8, message = "パスワードは8文字以上にしてください")
    private String passwordRetype;
}
