package com.microservice.order.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.order.dto.Order;
import com.microservice.order.entity.OrderEntity;
import com.microservice.order.mapper.ModelMapperUtil;
import com.microservice.order.repo.OrderRepo;
import com.microservice.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepo orderRepo;

	ModelMapperUtil modelMapperUtil = new ModelMapperUtil();

	@Override
	public Order addOrder(Order order) {
		OrderEntity orderEntity = orderRepo.save(modelMapperUtil.convertToEntityManual(order));
		return modelMapperUtil.convertToDTOManual(orderEntity);
	}

	@Override
	public List<Order> getAllOrder() {
		List<Order> orderList = new ArrayList<>();
		List<OrderEntity> orderEntityList = orderRepo.findAll();
		orderEntityList.forEach(orderEntity -> {
			orderList.add(modelMapperUtil.convertToDTOManual(orderEntity));
		});
		return orderList;
	}

	@Override
	public Order updateOrder(Order order) {
		Optional<OrderEntity> orderEntityOptional = orderRepo.findById(order.getOrderId());
		if (orderEntityOptional.isPresent()) {
			OrderEntity orderEntity = orderRepo.saveAndFlush(modelMapperUtil.convertToEntityManual(order));
			return modelMapperUtil.convertToDTOManual(orderEntity);
		}
		return null;
	}

	@Override
	public void deleteOrder(Order order) {
		orderRepo.delete(modelMapperUtil.convertToEntityManual(order));
	}

	@Override
	public Order getOrderByOrderId(Integer orderId) {
		Optional<OrderEntity> orderEntityOptional = orderRepo.findById(orderId);
		if (orderEntityOptional.isPresent()) {
			return modelMapperUtil.convertToDTOManual(orderEntityOptional.get());
		}
		return null;
	}
	
}

