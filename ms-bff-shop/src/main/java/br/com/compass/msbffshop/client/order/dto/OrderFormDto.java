package br.com.compass.msbffshop.client.order.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderFormDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private CustomerFormDto customer;
	@NotNull
	private PaymentFormDto payment;
	@NotNull
	private List<CartFormDto> cart;
}
