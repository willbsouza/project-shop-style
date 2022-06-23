package br.com.compass.mspayment.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PaymentFormDto {
	
	@NotNull @NotEmpty
	private String type;
	
	@NotNull
	private Boolean active;
	
	@NotNull
	private Boolean installments;

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
