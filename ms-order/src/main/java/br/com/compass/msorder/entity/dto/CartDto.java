package br.com.compass.msorder.entity.dto;

import java.io.Serializable;

public class CartDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long skuId;
	private Integer quantity;
	
	public Long getSkuId() {
		return skuId;
	}
	
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
