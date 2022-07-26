package br.com.compass.msorder.client.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Installment {

	private Integer amount;	
	private String brand;
	private Payment payment;
}
