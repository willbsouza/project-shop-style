package br.com.compass.msbffshop.client.catalog.dto;

import java.util.List;

import br.com.compass.msbffshop.client.entity.Media;
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
}