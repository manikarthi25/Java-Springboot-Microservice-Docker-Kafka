package com.microservice.order.service;

import java.util.List;

import com.microservice.order.dto.Order;

public interface OrderService {

	Order addOrder(Order order);

	List<Order> getAllOrder();

	Order updateOrder(Order order);

	void deleteOrder(Order order);

	Order getOrderByOrderId(Integer orderId);

}
