package com.app.testtaskraven.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.app.testtaskraven.dto.customer.CreateCustomerRequestDto;
import com.app.testtaskraven.dto.customer.CustomerResponseDto;
import com.app.testtaskraven.entity.Customer;
import com.app.testtaskraven.mapper.CustomerMapper;
import com.app.testtaskraven.repository.customer.CustomerRepository;
import com.app.testtaskraven.service.customer.impl.CustomerServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        CreateCustomerRequestDto requestDto = new CreateCustomerRequestDto();
        Customer customerToSave = new Customer();
        Customer savedCustomer = new Customer();
        CustomerResponseDto expectedResponseDto = new CustomerResponseDto();

        when(customerMapper.toEntity(requestDto)).thenReturn(customerToSave);
        when(customerRepository.save(customerToSave)).thenReturn(savedCustomer);
        when(customerMapper.toResponseDto(savedCustomer)).thenReturn(expectedResponseDto);

        CustomerResponseDto actualResponseDto = customerService.create(requestDto);

        assertEquals(expectedResponseDto, actualResponseDto);
        verify(customerMapper).toEntity(requestDto);
        verify(customerRepository).save(customerToSave);
        verify(customerMapper).toResponseDto(savedCustomer);
    }

    @Test
    void testGetAll() {
        Pageable pageable = mock(Pageable.class);
        Page<Customer> page = new PageImpl<>(Collections.emptyList());

        when(customerRepository.findAll(pageable)).thenReturn(page);

        List<CustomerResponseDto> responseDtos = customerService.getAll(pageable);

        assertNotNull(responseDtos);
        assertTrue(responseDtos.isEmpty());
        verify(customerRepository).findAll(pageable);
    }

    @Test
    void testGetById() {
        Long customerId = 1L;
        Customer customer = new Customer();
        CustomerResponseDto expectedResponseDto = new CustomerResponseDto();

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerMapper.toResponseDto(customer)).thenReturn(expectedResponseDto);

        CustomerResponseDto actualResponseDto = customerService.getById(customerId);

        assertEquals(expectedResponseDto, actualResponseDto);
        verify(customerRepository).findById(customerId);
        verify(customerMapper).toResponseDto(customer);
    }

    @Test
    void testGetByIdNotFound() {
        Long customerId = 1L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> customerService.getById(customerId));
        verify(customerRepository).findById(customerId);
    }

    @Test
    void testUpdate() {
        Long customerId = 1L;
        CreateCustomerRequestDto requestDto = new CreateCustomerRequestDto();
        Customer customerForUpdate = new Customer();
        CustomerResponseDto expectedResponseDto = new CustomerResponseDto();

        when(customerRepository.existsById(customerId)).thenReturn(true);
        when(customerMapper.toEntity(requestDto)).thenReturn(customerForUpdate);
        when(customerRepository.save(customerForUpdate)).thenReturn(customerForUpdate);
        when(customerMapper.toResponseDto(customerForUpdate)).thenReturn(expectedResponseDto);

        CustomerResponseDto actualResponseDto = customerService.update(customerId, requestDto);

        assertEquals(expectedResponseDto, actualResponseDto);
        verify(customerRepository).existsById(customerId);
        verify(customerMapper).toEntity(requestDto);
        verify(customerRepository).save(customerForUpdate);
        verify(customerMapper).toResponseDto(customerForUpdate);
    }

    @Test
    void testUpdateNotFound() {
        Long customerId = 1L;
        CreateCustomerRequestDto requestDto = new CreateCustomerRequestDto();

        when(customerRepository.existsById(customerId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class,
                () -> customerService.update(customerId, requestDto));
        verify(customerRepository).existsById(customerId);
    }

    @Test
    void testDeleteById() {
        Long customerId = 1L;

        customerService.deleteById(customerId);

        verify(customerRepository).deleteById(customerId);
    }
}
