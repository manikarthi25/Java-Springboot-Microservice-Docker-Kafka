package com.microservice.customer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.customer.dto.Order;

//@FeignClient(value = "order-service",  url = "http://127.0.0.1:8082/api/order")
//@FeignClient(value = "order-service",  url = "http://localhost:8082/api/order")
//Handling load balancing 
@FeignClient(name = "http://ORDER-SERVICE/api/order")
public interface OrderFeignClient {

	@GetMapping("/getorder/{orderId}")
	public ResponseEntity<Order> getOrderByOrderId(@PathVariable Integer orderId); 
}
