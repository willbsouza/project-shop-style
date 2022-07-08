package br.com.compass.mspayment.dto;

import br.com.compass.mspayment.entity.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentDto {

	private Long id;
	private String type;
	private Boolean active;
	private Boolean installments;
	
	public PaymentDto(Payment payment) {
		this.id = payment.getId();
		this.type = payment.getType();
		this.active = payment.getActive();
		this.installments = payment.getInstallments();
	}
}
