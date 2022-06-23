package br.com.compass.mspayment.dto;

import br.com.compass.mspayment.entity.Installment;
import br.com.compass.mspayment.entity.Payment;

public class InstallmentDto {

	private Long id;
	private Integer amount;
	private String brand;
	private Payment payment;
	
	public InstallmentDto() {}
	
	public InstallmentDto(Installment installment) {
		this.id = installment.getId();
		this.amount = installment.getAmount();
		this.brand = installment.getBrand();
		this.payment = installment.getPayment();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
}
