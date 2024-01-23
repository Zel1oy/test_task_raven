package com.app.testtaskraven.dto;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ArgumentNotValidResponseDto {
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String[] errors;
}
