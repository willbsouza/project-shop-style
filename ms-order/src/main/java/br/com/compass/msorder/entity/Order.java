package br.com.compass.msorder.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.compass.msorder.client.dto.Address;
import br.com.compass.msorder.client.dto.Customer;
import br.com.compass.msorder.client.dto.Installment;
import br.com.compass.msorder.client.dto.Payment;
import br.com.compass.msorder.client.dto.Sku;
import br.com.compass.msorder.enums.Status;

@Document
public class Order {
	
	@Id
	private String id;
	
	private Customer customer;
	private Address address;
	private Payment payment;
	private Installment installment;
	private List<Sku> cart;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	private Status status;
	private Double total;
	
	public Order() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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
	
	public Installment getInstallment() {
		return installment;
	}

	public void setInstallment(Installment installment) {
		this.installment = installment;
	}

	public List<Sku> getCart() {
		return cart;
	}

	public void setCart(List<Sku> cart) {
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
