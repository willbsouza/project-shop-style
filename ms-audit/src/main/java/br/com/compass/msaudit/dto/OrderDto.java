package br.com.compass.msaudit.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.compass.msaudit.entity.Address;
import br.com.compass.msaudit.entity.Customer;
import br.com.compass.msaudit.entity.Installment;
import br.com.compass.msaudit.entity.Order;
import br.com.compass.msaudit.entity.Payment;
import br.com.compass.msaudit.entity.Sku;
import br.com.compass.msaudit.enums.Status;
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
	private LocalDate date;
	private Double total;
	private Address address;
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
