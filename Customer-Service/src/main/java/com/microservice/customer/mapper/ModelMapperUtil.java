package com.microservice.customer.mapper;

import org.modelmapper.ModelMapper;

import com.microservice.customer.dto.Customer;
import com.microservice.customer.entity.CustomerEntity;

public class ModelMapperUtil {
	
	ModelMapper modelMapper = new ModelMapper();
	
	public Customer convertToDTO(CustomerEntity employeeEntity) {
		return modelMapper.map(employeeEntity, Customer.class);
	}
	
	public CustomerEntity convertToEntity(Customer employee) {
		return modelMapper.map(employee, CustomerEntity.class);
	}

}
