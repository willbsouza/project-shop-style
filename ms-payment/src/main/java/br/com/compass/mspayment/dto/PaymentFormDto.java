package br.com.compass.mspayment.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentFormDto {
	
	@NotNull @NotEmpty
	private String type;
	
	@NotNull
	private Boolean active;
	
	@NotNull
	private Boolean installments;
}
