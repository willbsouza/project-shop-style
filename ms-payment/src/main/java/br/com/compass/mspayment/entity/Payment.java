package br.com.compass.mspayment.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.compass.mspayment.dto.PaymentFormDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull @NotEmpty
	private String type;
	
	@NotNull
	private Boolean active;
	
	@NotNull
	private Boolean installments;
	
	public Payment(PaymentFormDto paymentFormDto) {
		this.type = paymentFormDto.getType();
		this.active = paymentFormDto.getActive();
		this.installments = paymentFormDto.getInstallments();
	}
}
