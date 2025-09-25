package com.microservice.order.mapper;

import org.modelmapper.ModelMapper;

import com.microservice.order.dto.Customer;
import com.microservice.order.dto.Order;
import com.microservice.order.entity.CustomerEntity;
import com.microservice.order.entity.OrderEntity;

public class ModelMapperUtil {

	ModelMapper modelMapper = new ModelMapper();

	public Order convertToDTOManual(OrderEntity orderEntity) {
		Order order = new Order();
		
		Customer customer = new Customer();
		customer.setCustomerId(orderEntity.getCustomerEntity().getCustomerId());
		customer.setCustomerName(orderEntity.getCustomerEntity().getCustomerName());
		
		order.setCustomer(customer);
		order.setOrderId(orderEntity.getOrderId());
		order.setOrderName(orderEntity.getOrderName());
		order.setOrderQty(orderEntity.getOrderQty());
		return order;
	}
	
	public OrderEntity convertToEntityManual(Order order) {
		OrderEntity orderEntity = new OrderEntity();
		
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setCustomerId(order.getCustomer().getCustomerId());
		customerEntity.setCustomerName(order.getCustomer().getCustomerName());
		
		orderEntity.setCustomerEntity(customerEntity);
		orderEntity.setOrderName(order.getOrderName());
		orderEntity.setOrderQty(order.getOrderQty());
		return orderEntity;
	}
	
	public Order convertToDTO(OrderEntity orderEntity) {
		return modelMapper.map(orderEntity, Order.class);
	}

	public OrderEntity convertToEntity(Order order) {
		return modelMapper.map(order, OrderEntity.class);
	}
}
