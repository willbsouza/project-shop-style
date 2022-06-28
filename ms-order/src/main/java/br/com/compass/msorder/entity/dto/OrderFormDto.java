package br.com.compass.msorder.entity.dto;

import java.util.List;

public class OrderFormDto {

	private CustomerDto customer;
	private PaymentDto payment;
	private List<CartDto> cart;

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

	public List<CartDto> getCart() {
		return cart;
	}
}
