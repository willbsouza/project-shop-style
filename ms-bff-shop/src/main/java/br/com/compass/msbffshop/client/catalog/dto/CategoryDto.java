package br.com.compass.msbffshop.client.catalog.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CategoryDto {

	private Long id;
	private String name;
	private Boolean active;
	private List<CategoryDto> children = new ArrayList<>();
}