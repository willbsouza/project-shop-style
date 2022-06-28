package br.com.compass.msorder.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.compass.msorder.client.dto.CartDto;
import br.com.compass.msorder.client.dto.CustomerDto;
import br.com.compass.msorder.client.dto.PaymentDto;
import br.com.compass.msorder.enums.Status;

@Document
public class Order {

	@Id
	private String id;
	
	private CustomerDto customer;
	
	private PaymentDto payment;
	
	private CartDto cart;
	
	private LocalDate date;
	
	private Status status;
	
	private Double total;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CustomerDto getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}

	public PaymentDto getPayment() {
		return payment;
	}

	public void setPayment(PaymentDto payment) {
		this.payment = payment;
	}

	public CartDto getCart() {
		return cart;
	}

	public void setCart(CartDto cart) {
		this.cart = cart;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
}
