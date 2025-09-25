package com.microservice.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.order.dto.Order;
import com.microservice.order.service.OrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {

	@Autowired
	private OrderService ordeService;
	
	@Autowired
	private Environment environment;

	@GetMapping("/status")
	public ResponseEntity<String> getAppStatus() {
		return ResponseEntity.ok("Order Service up and running");
	}

	@PostMapping("/create")
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {
		Order orderResponse = ordeService.addOrder(order);
		return new ResponseEntity<Order>(orderResponse, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
		Order orderResponse = ordeService.updateOrder(order);
		if (null != orderResponse) {
			return new ResponseEntity<Order>(orderResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<Order>(orderResponse, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/get")
	public ResponseEntity<List<Order>> getAllOrder() {
		List<Order> orderList = ordeService.getAllOrder();
		if (null != orderList) {
			return new ResponseEntity<List<Order>>(orderList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Order>>(orderList, HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteOrder(@RequestBody Order order) {
		ordeService.deleteOrder(order);
		return ResponseEntity.ok("Delete");
	}

	@GetMapping("/getorder/{orderId}")
	public ResponseEntity<Order> getOrderByOrderId(@PathVariable Integer orderId) {
		System.out.println(environment.getProperty("local.server.port"));
		Order orderResponse = ordeService.getOrderByOrderId(orderId);
		if (null != orderResponse) {
			return new ResponseEntity<Order>(orderResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<Order>(orderResponse, HttpStatus.NO_CONTENT);
		}
	}
	
}
