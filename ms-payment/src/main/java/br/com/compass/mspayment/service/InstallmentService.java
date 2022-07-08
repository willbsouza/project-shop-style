package br.com.compass.mspayment.service;

import javax.validation.Valid;

import br.com.compass.mspayment.dto.InstallmentDto;
import br.com.compass.mspayment.dto.InstallmentFormDto;

public interface InstallmentService {

	InstallmentDto save(@Valid InstallmentFormDto installmentFormDto);

	InstallmentDto update(Long id, @Valid InstallmentFormDto installmentFormDto);

	void deleteById(Long id);
}