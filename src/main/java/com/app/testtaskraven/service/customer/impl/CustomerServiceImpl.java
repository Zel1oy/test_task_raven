package com.app.testtaskraven.service.customer.impl;

import com.app.testtaskraven.dto.customer.CreateCustomerRequestDto;
import com.app.testtaskraven.dto.customer.CustomerResponseDto;
import com.app.testtaskraven.entity.Customer;
import com.app.testtaskraven.mapper.CustomerMapper;
import com.app.testtaskraven.repository.customer.CustomerRepository;
import com.app.testtaskraven.service.customer.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponseDto create(CreateCustomerRequestDto customer) {
        Customer customerToSave = customerMapper.toEntity(customer);
        return customerMapper.toResponseDto(customerRepository.save(customerToSave));
    }

    @Override
    public List<CustomerResponseDto> getAll(Pageable pageable) {
        return customerRepository.findAll(pageable)
                .stream()
                .map(customerMapper::toResponseDto)
                .toList();
    }

    @Override
    public CustomerResponseDto getById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::toResponseDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find Customer with id: " + id));
    }

    @Override
    public CustomerResponseDto update(Long id, CreateCustomerRequestDto customer) {
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("Can't find Customer with id: " + id);
        }

        Customer customerForUpdate = customerMapper.toEntity(customer);
        customerForUpdate.setId(id);
        return customerMapper.toResponseDto(customerRepository.save(customerForUpdate));
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
