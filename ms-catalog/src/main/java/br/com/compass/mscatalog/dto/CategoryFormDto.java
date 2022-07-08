package br.com.compass.mscatalog.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryFormDto {

	@NotNull @NotEmpty
	private String name;
	
	@NotNull
	private Boolean active;
	
	private Long parentId;
}