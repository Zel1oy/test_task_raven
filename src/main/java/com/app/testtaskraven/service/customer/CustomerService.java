package com.app.testtaskraven.service.customer;

import com.app.testtaskraven.dto.customer.CreateCustomerRequestDto;
import com.app.testtaskraven.dto.customer.CustomerResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    CustomerResponseDto create(CreateCustomerRequestDto customer);

    List<CustomerResponseDto> getAll(Pageable pageable);

    CustomerResponseDto getById(Long id);

    CustomerResponseDto update(Long id, CreateCustomerRequestDto customer);

    void deleteById(Long id);
}
