package br.com.compass.mscatalog.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.compass.mscatalog.dto.ProductFormDto;

@Document
public class Product {

	@Id
	private String id;
	private String name;
	private String description;
	private Boolean active;
	
	public Product() {}
	
	public Product(ProductFormDto productFormDto) {
		this.name = productFormDto.getName();
		this.description = productFormDto.getDescription();
		this.active = productFormDto.getActive();
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
}
