package br.com.compass.mscatalog.dto;

import java.util.List;

public class ProductFormDto {
	
	private String name;
	private String description;
	private Boolean active;
	private List<Long> category_ids;
	
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
	public List<Long> getCategory_ids() {
		return category_ids;
	}	
}
