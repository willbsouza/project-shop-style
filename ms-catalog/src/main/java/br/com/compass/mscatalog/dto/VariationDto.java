package br.com.compass.mscatalog.dto;

import br.com.compass.mscatalog.entity.Variation;

public class VariationDto {

	private String color;
	private String size;
	private Double price;
	private Integer quantity;
	
	public VariationDto() {}
	
	public VariationDto(Variation variation) {
		this.color = variation.getColor();
		this.size = variation.getSize();
		this.price = variation.getPrice();
		this.quantity = variation.getQuantity();
	}
	
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
	
	
}
