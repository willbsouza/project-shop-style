package br.com.compass.mscatalog.dto;

import java.util.List;

import br.com.compass.mscatalog.entity.Media;
import br.com.compass.mscatalog.entity.Sku;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SkuDto {

	private Long id;
	private Double price;
	private Integer quantity;
	private String color;
	private String size;
	private Integer height;
	private Integer width;
	private List<Media> images;
	
	public SkuDto(Sku sku) {
		this.id = sku.getId();
		this.price = sku.getPrice();
		this.quantity = sku.getQuantity();
		this.color = sku.getColor();
		this.size = sku.getSize();
		this.height = sku.getHeight();
		this.width = sku.getWidth();
		this.images = sku.getImages();
	}
}