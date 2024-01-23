package com.app.testtaskraven.dto.customer;

import lombok.Data;

@Data
public class CustomerResponseDto {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
}
