package br.com.compass.msorder.entity.dto;

public class PaymentDto {

	private Long id;
	private Integer installments;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getInstallments() {
		return installments;
	}
	
	public void setInstallments(Integer installments) {
		this.installments = installments;
	}
}
