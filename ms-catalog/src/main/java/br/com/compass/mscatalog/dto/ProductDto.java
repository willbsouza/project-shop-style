package br.com.compass.mscatalog.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.compass.mscatalog.entity.Product;

public class ProductDto {

	private String name;
	private String description;
	private Boolean active;
	private List<VariationDto> variations;
	
	public ProductDto() {}
	
	public ProductDto(Product product) {
		this.name = product.getName();
		this.description = product.getDescription();
		this.active = product.getActive();
		this.variations = product.getVariations().stream().map(VariationDto::new).collect(Collectors.toList());
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

	public List<VariationDto> getVariations() {
		return variations;
	}
}
