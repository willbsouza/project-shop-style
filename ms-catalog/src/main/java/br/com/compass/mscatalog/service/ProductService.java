package br.com.compass.mscatalog.service;

import java.util.List;

import javax.validation.Valid;

import br.com.compass.mscatalog.dto.ProductDto;
import br.com.compass.mscatalog.dto.ProductFormDto;

public interface ProductService {

	List<ProductDto> findAll();
	
	ProductDto findById(Long id);
	
	ProductDto save(@Valid ProductFormDto productFormDto);
	
	ProductDto update(Long id, @Valid ProductFormDto productFormDto);

	void deleteById(Long id);
}
