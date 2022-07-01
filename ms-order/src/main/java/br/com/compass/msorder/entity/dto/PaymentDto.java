package br.com.compass.msorder.entity.dto;

import java.io.Serializable;

public class PaymentDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
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
