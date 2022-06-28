package br.com.compass.msorder.client.dto;

public class CatalogDto {

	private Long id;
	private Double price;
	private Integer quantity;
	private String color;
	private String size;
	private Integer height;
	private Integer width;
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
	@Override
	public String toString() {
		return "CatalogDto [id=" + id + ", price=" + price + ", quantity=" + quantity + ", color=" + color + ", size="
				+ size + ", height=" + height + ", width=" + width + "]";
	}
}
