package com.app.testtaskraven.dto.customer;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCustomerRequestDto {
    @NotNull
    @Size(min = 2, max = 50)
    private String fullName;
    @Size(min = 2, max = 100)
    @NotNull
    @Email
    @Column(unique = true)
    private String email;
    @Size(min = 6, max = 14)
    @Pattern(regexp = "^\\+\\d+$\n")
    private String phone;
}
