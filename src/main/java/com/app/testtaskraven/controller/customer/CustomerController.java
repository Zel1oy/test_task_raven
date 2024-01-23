package com.app.testtaskraven.controller.customer;

import com.app.testtaskraven.dto.customer.CreateCustomerRequestDto;
import com.app.testtaskraven.dto.customer.CustomerResponseDto;
import com.app.testtaskraven.service.customer.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Customer API", description = "Controller for managing customer in DB")
@RestController
@RequestMapping(value = "/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    @Operation(summary = "Create a new customer",
            description = "Create a new customer with the provided data")
    public CustomerResponseDto create(@RequestBody @Valid
                                          CreateCustomerRequestDto customerRequest) {
        return customerService.create(customerRequest);
    }

    @GetMapping
    @Operation(summary = "Get all customers",
            description = "Retrieve some set of customers using pagination")
    public List<CustomerResponseDto> getAll(Pageable pageable) {
        return customerService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get customer by ID",
            description = "Retrieve a customer by its id")
    public CustomerResponseDto getById(@PathVariable Long id) {
        return customerService.getById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update customer by ID",
            description = "Modify an existing customer using its id")
    public CustomerResponseDto update(@PathVariable Long id,
                                      @RequestBody @Valid
                                      CreateCustomerRequestDto customerRequest) {
        return customerService.update(id, customerRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete customer by ID",
            description = "Delete an existing customer using its id")
    public void deleteById(@PathVariable Long id) {
        customerService.deleteById(id);
    }
}
