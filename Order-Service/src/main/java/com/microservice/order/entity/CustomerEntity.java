package com.microservice.order.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//This setup ensures that one Customer can have multiple Orders, and each Order is associated with one Customer.

@Entity
@Table(name = "customer")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;

	private String customerName;
	
	/*
	 The @OneToMany annotation establishes the one-to-many relationship.
     The mappedBy attribute refers to the customer field in the Order class.
     cascade = CascadeType.ALL ensures that operations like persist, merge, and remove are cascaded to the associated orders.
     orphanRemoval = true ensures that if an order is removed from the list, it is also deleted from the database.
	*/
	@OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntity> orderEntityList;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<OrderEntity> getOrderEntityList() {
		return orderEntityList;
	}

	public void setOrderEntityList(List<OrderEntity> orderEntityList) {
		this.orderEntityList = orderEntityList;
	}
	
}
