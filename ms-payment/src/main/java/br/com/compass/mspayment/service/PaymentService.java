package br.com.compass.mspayment.service;

import java.util.List;

import javax.validation.Valid;

import br.com.compass.mspayment.dto.PaymentDto;
import br.com.compass.mspayment.dto.PaymentFormDto;

public interface PaymentService {

	List<PaymentDto> findAll();
	
	PaymentDto findById(Long id);

	PaymentDto save(@Valid PaymentFormDto paymentFormDto);

	PaymentDto update(Long id, @Valid PaymentFormDto paymentFormDto);

	void deleteById(Long id);
}