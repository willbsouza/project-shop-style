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

import br.com.compass.mscatalog.dto.SkuFormDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Sku {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Double price;
	
	@NotNull
	private Integer quantity;
	
	@NotNull @NotEmpty
	private String color;
	
	@NotNull @NotEmpty
	private String size;
	
	@NotNull
	private Integer height;
	
	@NotNull 
	private Integer width;
	
	@NotNull
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "product_id")
	private Product product;
	
	@OneToMany(mappedBy = "sku", cascade = CascadeType.ALL)
	@NotNull @NotEmpty
	private List<Media> images = new ArrayList<>();
	
	public Sku(SkuFormDto skuFormDto, Product product) {
		this.price = skuFormDto.getPrice();
		this.quantity = skuFormDto.getQuantity();
		this.color = skuFormDto.getColor();
		this.size = skuFormDto.getSize();
		this.height = skuFormDto.getHeight();
		this.width = skuFormDto.getWidth();
		this.product = product;
	}
	
	public void addImages(Media media) {
		this.images.add(media);
	}
}
