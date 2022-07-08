package br.com.compass.mscatalog.service;

import java.util.List;

import javax.validation.Valid;

import br.com.compass.mscatalog.dto.CategoryDto;
import br.com.compass.mscatalog.dto.CategoryFormDto;
import br.com.compass.mscatalog.dto.ProductDto;

public interface CategoryService {

	List<CategoryDto> findAll();
	
	List<ProductDto> findListProductsById(Long id);
	
	CategoryDto save(@Valid CategoryFormDto categoryFormDto);

	CategoryDto update(Long id, @Valid CategoryFormDto categoryFormDto);

	void deleteById(Long id);
}
