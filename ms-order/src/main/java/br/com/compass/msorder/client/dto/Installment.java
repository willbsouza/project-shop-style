package br.com.compass.msorder.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Installment {

	private Integer amount;	
	private String brand;
	private Payment payment;
}
