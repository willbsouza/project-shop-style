package br.com.compass.msaudit.client.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Installment {

	private Integer amount;	
	private String brand;
	private Payment payment;
}