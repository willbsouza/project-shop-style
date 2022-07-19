package br.com.compass.mscatalog.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.compass.mscatalog.entity.Category;
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

	public CategoryDto(Category category) {
		this.id = category.getId();
		this.name = category.getName();
		this.active = category.getActive();
		this.children = category.getChildren().stream().map(CategoryDto::new).collect(Collectors.toList());
	}
}