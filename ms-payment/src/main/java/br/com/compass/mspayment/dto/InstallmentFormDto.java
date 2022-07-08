package br.com.compass.mspayment.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InstallmentFormDto {

	@NotNull
	private Integer amount;
	
	private String brand;
	
	@NotNull
	private Long paymentId;
}