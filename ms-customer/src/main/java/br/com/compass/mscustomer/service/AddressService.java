package br.com.compass.mscustomer.service;

import javax.validation.Valid;

import br.com.compass.mscustomer.dto.AddressDto;
import br.com.compass.mscustomer.dto.AddressFormDto;

public interface AddressService {

	AddressDto findById(Long id);
	
	AddressDto save(@Valid AddressFormDto addressFormDto);

	AddressDto update(Long id, @Valid AddressFormDto addressFormDto);

	void deleteById(Long id);
}
