package br.com.compass.mscatalog.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductFormDto {

	@NotNull @NotEmpty
	private String name;
	
	@NotNull @NotEmpty
	private String description;
	
	@NotNull @NotEmpty
	private String brand;
	
	private String material;
	
	@NotNull
	private Boolean active;
	
	@NotNull
	private Long categoryId;
}