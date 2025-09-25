package com.microservice.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.microservice.customer.dto.Customer;
import com.microservice.customer.dto.Order;
import com.microservice.customer.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin("*")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/status")
	public ResponseEntity<String> getAppStatus() {
		return ResponseEntity.ok("up and running");
	}

	@PostMapping("/create")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		Customer customerResponse = customerService.addCustomer(customer);
		return new ResponseEntity<Customer>(customerResponse, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
		Customer customerResponse = customerService.updateCustomer(customer);
		if (null != customerResponse) {
			return new ResponseEntity<Customer>(customerResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<Customer>(customerResponse, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/get")
	public ResponseEntity<List<Customer>> getAllCustomer() {
		List<Customer> customerList = customerService.getAllCustomer();
		if (null != customerList) {
			return new ResponseEntity<List<Customer>>(customerList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Customer>>(customerList, HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteCustomer(@RequestBody Customer customer) {
		customerService.deleteCustomer(customer);
		return ResponseEntity.ok("Delete");
	}

	@GetMapping("/getcustomer/{customerId}")
	public ResponseEntity<Customer> getCustomerByCustomerId(@PathVariable Integer customerId) {
		Customer customerResponse = customerService.getCustomerByCustomerId(customerId);
		if (null != customerResponse) {
			return new ResponseEntity<Customer>(customerResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<Customer>(customerResponse, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/getorder/{orderId}")
	public ResponseEntity<Order> getOrderByOrderId(@PathVariable Integer orderId) {
		Order orderResponse = customerService.getOrderByOrderId(orderId);
		if (null != orderResponse) {
			return new ResponseEntity<Order>(orderResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<Order>(orderResponse, HttpStatus.NO_CONTENT);
		}
	}

}
