package br.com.compass.msorder.entity.dto;

public class CartDto {

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
