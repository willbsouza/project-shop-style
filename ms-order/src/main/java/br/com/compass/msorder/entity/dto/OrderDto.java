package br.com.compass.msorder.entity.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.compass.msorder.client.dto.Address;
import br.com.compass.msorder.client.dto.Customer;
import br.com.compass.msorder.client.dto.Installment;
import br.com.compass.msorder.client.dto.Payment;
import br.com.compass.msorder.client.dto.Sku;
import br.com.compass.msorder.entity.Order;
import br.com.compass.msorder.enums.Status;

public class OrderDto {
	
	private String id;	
	private Customer customer;
	private List<Sku> cart;
	private Payment payment;
	private Installment installment;
	private Double total;
	private Address address;
	private LocalDate date;
	private Status status;
	
	
	public OrderDto() {}
	
	public OrderDto(Order order) {
		this.id = order.getId();
		this.customer = order.getCustomer();
		this.payment = order.getPayment();
		this.cart = order.getCart();
		this.date = order.getDate();
		this.total = order.getTotal();
		this.status = order.getStatus();
		this.address = order.getAddress();
		this.installment = order.getInstallment();
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Payment getPayment() {
		return payment;
	}
	
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public List<Sku> getCart() {
		return cart;
	}

	public Installment getInstallment() {
		return installment;
	}

	public void setInstallment(Installment installment) {
		this.installment = installment;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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
}
