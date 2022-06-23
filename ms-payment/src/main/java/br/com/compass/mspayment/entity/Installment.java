package br.com.compass.mspayment.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.compass.mspayment.dto.InstallmentFormDto;

@Entity
public class Installment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Integer amount;
	
	@NotNull @NotEmpty
	private String brand;
	
	@OneToOne
	@JoinColumn(name = "payment_id")
	private Payment payment;
	
	public Installment() {}
	
	public Installment(InstallmentFormDto installmentFormDto, Payment payment) {
		this.amount = installmentFormDto.getAmount();
		this.brand = installmentFormDto.getBrand();
		this.payment = payment;
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
