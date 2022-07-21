package br.com.compass.msbffshop.client.catalog.dto;

import java.util.List;

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
}
