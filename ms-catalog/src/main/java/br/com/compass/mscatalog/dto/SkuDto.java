package br.com.compass.mscatalog.dto;

import java.util.List;

import br.com.compass.mscatalog.entity.Media;
import br.com.compass.mscatalog.entity.Sku;

public class SkuDto {

	private Long id;
	private Double price;
	private Integer quantity;
	private String color;
	private String size;
	private Integer height;
	private Integer width;
	
	private List<Media> images;
	
	public SkuDto() {}
	
	public SkuDto(Sku sku) {
		this.id = sku.getId();
		this.price = sku.getPrice();
		this.quantity = sku.getQuantity();
		this.color = sku.getColor();
		this.size = sku.getSize();
		this.height = sku.getHeight();
		this.width = sku.getWidth();
		this.images = sku.getImages();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public List<Media> getImages() {
		return images;
	}
}
