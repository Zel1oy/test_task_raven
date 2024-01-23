package com.app.testtaskraven.contoller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.app.testtaskraven.controller.customer.CustomerController;
import com.app.testtaskraven.dto.customer.CreateCustomerRequestDto;
import com.app.testtaskraven.dto.customer.CustomerResponseDto;
import com.app.testtaskraven.service.customer.CustomerService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    public CustomerControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        CreateCustomerRequestDto requestDto = new CreateCustomerRequestDto();
        CustomerResponseDto expectedResponseDto = new CustomerResponseDto();

        when(customerService.create(requestDto)).thenReturn(expectedResponseDto);

        CustomerResponseDto actualResponseDto = customerController.create(requestDto);

        assertEquals(expectedResponseDto, actualResponseDto);
        verify(customerService).create(requestDto);
    }

    @Test
    void testGetAll() {
        Pageable pageable = mock(Pageable.class);
        List<CustomerResponseDto> expectedResponseDtos = Collections.emptyList();

        when(customerService.getAll(pageable)).thenReturn(expectedResponseDtos);

        List<CustomerResponseDto> actualResponseDtos = customerController.getAll(pageable);

        assertEquals(expectedResponseDtos, actualResponseDtos);
        verify(customerService).getAll(pageable);
    }

    @Test
    void testGetById() {
        Long customerId = 1L;
        CustomerResponseDto expectedResponseDto = new CustomerResponseDto();

        when(customerService.getById(customerId)).thenReturn(expectedResponseDto);

        CustomerResponseDto actualResponseDto = customerController.getById(customerId);

        assertEquals(expectedResponseDto, actualResponseDto);
        verify(customerService).getById(customerId);
    }

    @Test
    void testUpdate() {
        Long customerId = 1L;
        CreateCustomerRequestDto requestDto = new CreateCustomerRequestDto();
        CustomerResponseDto expectedResponseDto = new CustomerResponseDto();

        when(customerService.update(customerId, requestDto)).thenReturn(expectedResponseDto);

        CustomerResponseDto actualResponseDto = customerController.update(customerId, requestDto);

        assertEquals(expectedResponseDto, actualResponseDto);
        verify(customerService).update(customerId, requestDto);
    }

    @Test
    void testDeleteById() {
        Long customerId = 1L;

        customerController.deleteById(customerId);

        verify(customerService).deleteById(customerId);
    }
}
