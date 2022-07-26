package br.com.compass.mspayment.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import br.com.compass.mspayment.dto.InstallmentFormDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Installment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Integer amount;
	
	private String brand;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "payment_id", unique = true)
	private Payment payment;
	
	public Installment(InstallmentFormDto installmentFormDto, Payment payment) {
		this.amount = installmentFormDto.getAmount();
		this.brand = installmentFormDto.getBrand();
		this.payment = payment;
	}
}
