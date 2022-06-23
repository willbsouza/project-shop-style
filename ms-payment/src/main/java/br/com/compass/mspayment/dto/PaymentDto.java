package br.com.compass.mspayment.dto;

import br.com.compass.mspayment.entity.Payment;

public class PaymentDto {

	private Long id;
	private String type;
	private Boolean active;
	private Boolean installments;
	
	public PaymentDto() {}
	
	public PaymentDto(Payment payment) {
		this.id = payment.getId();
		this.type = payment.getType();
		this.active = payment.getActive();
		this.installments = payment.getInstallments();
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
