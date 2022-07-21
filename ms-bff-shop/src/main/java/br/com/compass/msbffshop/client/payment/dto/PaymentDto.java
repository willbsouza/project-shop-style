package br.com.compass.msbffshop.client.payment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentDto {

	private Long id;
	private String type;
	private Boolean active;
	private Boolean installments;
}
