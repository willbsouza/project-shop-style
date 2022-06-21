package br.com.compass.mscatalog.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.compass.mscatalog.dto.ProductFormDto;

@Document
public class Product {
	
	@Transient
	public static final String SEQUENCE_NAME = "product_sequence";

	@Id
	private Long id;
	
	@NotNull @NotEmpty
	private String name;
	
	@NotNull @NotEmpty
	private String description;
	
	@NotNull
	private Boolean active;
	
	@DBRef
	private List<Category> categories = new ArrayList<>();
	
	@DBRef
	private List<Variation> variations = new ArrayList<>();
	
	public Product() {}
	
	public Product(ProductFormDto productFormDto) {
		this.name = productFormDto.getName();
		this.description = productFormDto.getDescription();
		this.active = productFormDto.getActive();
	}

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
	
	public List<Category> getCategories() {
		return categories;
	}
	
	public void addCategory(Category category) {
		this.categories.add(category);
	}

	public List<Variation> getVariations() {
		return variations;
	}
	
	public void addVariation(Variation variation) {
		this.variations.add(variation);
	}
}
