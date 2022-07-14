package br.com.compass.mspayment.rabbit.consumer.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PaymentOrder implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String orderId;
	private PaymentDto payment;
}
