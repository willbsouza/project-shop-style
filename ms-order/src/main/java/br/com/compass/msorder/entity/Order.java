package br.com.compass.msorder.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.compass.msorder.client.entity.Address;
import br.com.compass.msorder.client.entity.Customer;
import br.com.compass.msorder.client.entity.Installment;
import br.com.compass.msorder.client.entity.Payment;
import br.com.compass.msorder.client.entity.Sku;
import br.com.compass.msorder.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@Getter
@Setter
@NoArgsConstructor
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
}
