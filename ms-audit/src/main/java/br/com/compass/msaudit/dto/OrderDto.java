package br.com.compass.msaudit.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.compass.msaudit.client.entity.Address;
import br.com.compass.msaudit.client.entity.Customer;
import br.com.compass.msaudit.client.entity.Installment;
import br.com.compass.msaudit.client.entity.Payment;
import br.com.compass.msaudit.client.entity.Sku;
import br.com.compass.msaudit.client.enums.Status;
import br.com.compass.msaudit.entity.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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
}
