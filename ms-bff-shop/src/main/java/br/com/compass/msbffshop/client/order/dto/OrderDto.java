package br.com.compass.msbffshop.client.order.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.compass.msbffshop.client.entity.Address;
import br.com.compass.msbffshop.client.entity.Customer;
import br.com.compass.msbffshop.client.entity.Installment;
import br.com.compass.msbffshop.client.entity.Payment;
import br.com.compass.msbffshop.client.entity.Sku;
import br.com.compass.msbffshop.client.enums.Status;
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
}
