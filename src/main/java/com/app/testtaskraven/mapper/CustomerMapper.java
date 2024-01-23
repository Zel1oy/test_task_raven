package com.app.testtaskraven.mapper;

import com.app.testtaskraven.config.MapperConfig;
import com.app.testtaskraven.dto.customer.CreateCustomerRequestDto;
import com.app.testtaskraven.dto.customer.CustomerResponseDto;
import com.app.testtaskraven.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CustomerMapper {
    Customer toEntity(CreateCustomerRequestDto requestDto);

    CustomerResponseDto toResponseDto(Customer customer);
}
