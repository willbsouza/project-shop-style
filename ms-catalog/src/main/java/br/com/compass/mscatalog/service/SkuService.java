package br.com.compass.mscatalog.service;

import java.util.List;

import javax.validation.Valid;

import br.com.compass.mscatalog.dto.SkuDto;
import br.com.compass.mscatalog.dto.SkuFormDto;

public interface SkuService {

	List<SkuDto> findAll();
	
	SkuDto findById(Long id);

	SkuDto save(@Valid SkuFormDto skuFormDto);

	SkuDto update(Long id, @Valid SkuFormDto skuFormDto);
	
	SkuDto updateOrderSku(Long id, Integer quantity);

	void deleteById(Long id);

	
}
