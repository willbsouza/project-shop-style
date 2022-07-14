package br.com.compass.mspayment.rabbit.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PaymentDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Integer installments;
}
