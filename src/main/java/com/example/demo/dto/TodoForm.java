package com.example.demo.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TodoForm {
    @NotBlank(message = "タイトルは必須です")
    private String title;

    private String detail;

    @Min(1)
    @Max(5)
    private int priority;

    @Future
    @NotNull(message = "日付は必須です")
    private LocalDateTime deadline;
}
