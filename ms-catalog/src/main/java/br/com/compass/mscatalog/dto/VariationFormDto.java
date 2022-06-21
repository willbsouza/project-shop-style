package br.com.compass.mscatalog.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class VariationFormDto {

	@NotNull @NotEmpty
	private String color;
	
	@NotNull @NotEmpty
	private String size;
	
	@NotNull
	private Double price;
	
	@NotNull
	private Integer quantity;
	
	@NotNull
	private Long product_id;
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	
	
}
