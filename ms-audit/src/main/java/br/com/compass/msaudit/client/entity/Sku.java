package br.com.compass.msaudit.client.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Sku {

	private Long id;
	private Double price;
	private Integer quantity;
	private String color;
	private String size;
	private Integer height;
	private Integer width;
}