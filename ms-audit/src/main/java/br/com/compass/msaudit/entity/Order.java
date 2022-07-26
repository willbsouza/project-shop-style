package br.com.compass.msaudit.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import br.com.compass.msaudit.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document
@Getter
@NoArgsConstructor
@ToString
public class Order {
	
	private String id;
	private Customer customer;
	private Address address;
	private Payment payment;
	private Installment installment;
	private List<Sku> cart;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate date;
	private Status status;
	private Double total;
}
