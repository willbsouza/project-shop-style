package br.com.compass.mscatalog.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.compass.mscatalog.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductDto {

	private Long id;
	private String name;
	private String description;
	private String brand;
	private String material;
	private Boolean active;
	private List<SkuDto> skus;
	
	public ProductDto(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.brand = product.getBrand();
		this.material = product.getMaterial();
		this.active = product.getActive();
		this.skus = product.getSkus().stream().map(SkuDto::new).collect(Collectors.toList());
	}
}
