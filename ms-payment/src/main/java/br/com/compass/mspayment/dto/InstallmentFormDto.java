package br.com.compass.mspayment.dto;

import javax.validation.constraints.NotNull;

public class InstallmentFormDto {

	@NotNull
	private Integer amount;
	
	private String brand;
	
	@NotNull
	private Long paymentId;

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	
	
}
