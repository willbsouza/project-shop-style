package br.com.compass.mscatalog.dto;

import java.util.List;

import br.com.compass.mscatalog.entity.Category;

public class CategoryDto {

	private Long id;
	private String name;
	private Boolean active;
	private List<Category> children;
	
	public CategoryDto() {}

	public CategoryDto(Category category) {
		this.id = category.getId();
		this.name = category.getName();
		this.active = category.getActive();
		this.children = category.getChildren();
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
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Category> getChildren() {
		return children;
	}
}