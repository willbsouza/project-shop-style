package br.com.compass.mscatalog.dto;

import br.com.compass.mscatalog.entity.Category;

public class CategoryDto {
	
	private String name;
	private Boolean active;
	
	public CategoryDto() {}

	public CategoryDto(Category category) {
		this.name = category.getName();
		this.active = category.getActive();
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

}
