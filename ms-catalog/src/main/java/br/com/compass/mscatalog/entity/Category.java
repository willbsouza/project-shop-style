package br.com.compass.mscatalog.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.compass.mscatalog.dto.CategoryFormDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull @NotEmpty
	private String name;
	
	@NotNull
	private Boolean active;
	
	@ManyToOne
	@JoinColumn(name = "parent_id")
	@JsonIgnore
	private Category parent;
	
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	private List<Category> children = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	@JsonIgnore
	private List<Product> products;
	
	public Category(CategoryFormDto categoryDto, Category parentCategory) {
		this.name = categoryDto.getName();
		this.active = categoryDto.getActive();
		this.parent = parentCategory;
	}
	
	public Category(CategoryFormDto categoryDto) {
		this.name = categoryDto.getName();
		this.active = categoryDto.getActive();
	}
	
	public void addChildren(Category category) {
		this.children.add(category);
	}
}