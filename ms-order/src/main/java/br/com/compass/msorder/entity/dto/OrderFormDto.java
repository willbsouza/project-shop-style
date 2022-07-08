package br.com.compass.msorder.entity.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderFormDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private CustomerDto customer;
	private PaymentDto payment;
	private List<CartDto> cart;
}
