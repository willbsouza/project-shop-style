package br.com.compass.msorder.entity.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderFormDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private CustomerDto customer;
	@NotNull
	private PaymentDto payment;
	@NotNull
	private List<CartDto> cart;
}
