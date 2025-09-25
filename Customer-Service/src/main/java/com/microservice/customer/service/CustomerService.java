package com.microservice.customer.service;

import java.util.List;

import com.microservice.customer.dto.Customer;
import com.microservice.customer.dto.Order;

public interface CustomerService {

	Customer addCustomer(Customer customer);

	List<Customer> getAllCustomer();

	Customer updateCustomer(Customer customer);

	void deleteCustomer(Customer customer);

	Customer getCustomerByCustomerId(Integer customerId);

	Order getOrderByOrderId(Integer orderId);

}
