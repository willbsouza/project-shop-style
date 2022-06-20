package br.com.compass.mscatalog.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Category {
	
	@Transient
	public static final String SEQUENCE_NAME="category_sequence";

	@Id
	private Long id;
	
	private String name;
	private Boolean active;
	private List<Product> products = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public List<Product> getProducts() {
		return products;
	}
	
	public void addProducts(Product product) {
		this.products.add(product);
	}
}
