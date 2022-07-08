package br.com.compass.mscustomer.service;

import javax.validation.Valid;

import br.com.compass.mscustomer.dto.CustomerChangePasswordDto;
import br.com.compass.mscustomer.dto.CustomerDto;
import br.com.compass.mscustomer.dto.CustomerFormDto;
import br.com.compass.mscustomer.dto.CustomerLoginDto;

public interface CustomerService {

	CustomerDto findById(Long id);

	CustomerDto save(@Valid CustomerFormDto userFormDto);

	CustomerDto updateById(@Valid CustomerFormDto userFormDto, Long id);

	CustomerDto login(CustomerLoginDto customerLoginDto);

	CustomerDto changePassword(@Valid CustomerChangePasswordDto passwordDto, Long id);
}
