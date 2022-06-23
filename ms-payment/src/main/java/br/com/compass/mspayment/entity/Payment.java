package br.com.compass.mspayment.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.compass.mspayment.dto.PaymentFormDto;

@Entity
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
	
	public Payment() {}
	
	public Payment(PaymentFormDto paymentFormDto) {
		this.type = paymentFormDto.getType();
		this.active = paymentFormDto.getActive();
		this.installments = paymentFormDto.getInstallments();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getInstallments() {
		return installments;
	}

	public void setInstallments(Boolean installments) {
		this.installments = installments;
	}
}
