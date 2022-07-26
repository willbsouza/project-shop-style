package br.com.compass.msaudit.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class Installment {

	private Integer amount;	
	private String brand;
	private Payment payment;
}