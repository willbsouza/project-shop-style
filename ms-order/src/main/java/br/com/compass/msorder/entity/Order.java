package br.com.compass.msorder.entity;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import br.com.compass.msorder.client.entity.Address;
import br.com.compass.msorder.client.entity.Customer;
import br.com.compass.msorder.client.entity.Installment;
import br.com.compass.msorder.client.entity.Payment;
import br.com.compass.msorder.client.entity.Sku;
import br.com.compass.msorder.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Order {
	
	@Id
	private String id;
	@NotNull
	private Customer customer;
	@NotNull
	private Address address;
	@NotNull
	private Payment payment;
	@NotNull
	private Installment installment;
	@NotNull
	private List<Sku> cart;
	@NotNull
    @JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate date;
	@NotNull
	private Status status;
	@NotNull
	private Double total;
}
