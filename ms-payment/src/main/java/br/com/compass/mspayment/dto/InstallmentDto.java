package br.com.compass.mspayment.dto;

import br.com.compass.mspayment.entity.Installment;
import br.com.compass.mspayment.entity.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InstallmentDto {

	private Long id;
	private Integer amount;
	private String brand;
	private Payment payment;
	
	public InstallmentDto(Installment installment) {
		this.id = installment.getId();
		this.amount = installment.getAmount();
		this.brand = installment.getBrand();
		this.payment = installment.getPayment();
	}
}
