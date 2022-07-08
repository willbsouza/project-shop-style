package br.com.compass.mscatalog.entity;

import java.util.ArrayList;
import java.util.List;

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

import br.com.compass.mscatalog.dto.ProductFormDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull @NotEmpty
	private String name;
	
	@NotNull @NotEmpty
	private String description;
	
	@NotNull @NotEmpty
	private String brand;
	
	private String material;
	
	@NotNull
	private Boolean active;

	@OneToMany(mappedBy = "product")
	@JsonIgnore
	private List<Sku> skus = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	@NotNull
	private Category category;
	
	public Product(ProductFormDto productFormDto, Category category) {
		this.name = productFormDto.getName();
		this.description = productFormDto.getDescription();
		this.brand = productFormDto.getBrand();
		this.material = productFormDto.getMaterial();
		this.active = productFormDto.getActive();
		this.category = category;
	}
}