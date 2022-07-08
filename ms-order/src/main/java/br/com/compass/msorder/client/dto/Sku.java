package br.com.compass.msorder.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
