package br.com.compass.msaudit.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.compass.msaudit.client.entity.Address;
import br.com.compass.msaudit.client.entity.Customer;
import br.com.compass.msaudit.client.entity.Installment;
import br.com.compass.msaudit.client.entity.Payment;
import br.com.compass.msaudit.client.entity.Sku;
import br.com.compass.msaudit.client.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Document
@Getter
@NoArgsConstructor
public class Order {
	
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
