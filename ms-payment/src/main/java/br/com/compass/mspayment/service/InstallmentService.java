package br.com.compass.mspayment.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compass.mspayment.dto.InstallmentDto;
import br.com.compass.mspayment.dto.InstallmentFormDto;
import br.com.compass.mspayment.entity.Installment;
import br.com.compass.mspayment.entity.Payment;
import br.com.compass.mspayment.repository.InstallmentRepository;
import br.com.compass.mspayment.repository.PaymentRepository;
import br.com.compass.mspayment.service.exception.ObjectNotFoundException;

@Service
public class InstallmentService {
	
	@Autowired
	private InstallmentRepository installmentRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;

	public InstallmentDto save(@Valid InstallmentFormDto installmentFormDto) {
		Payment payment = paymentRepository.findById(installmentFormDto.getPaymentId()).orElseThrow(
				() -> new ObjectNotFoundException("Payment Id: " + installmentFormDto.getPaymentId() + " not found."));
		return new InstallmentDto(installmentRepository.save(new Installment(installmentFormDto, payment)));
	}

	public InstallmentDto update(Long id, @Valid InstallmentFormDto installmentFormDto) {
		Installment installment = installmentRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Installment Id: " + id + " not found."));
		Payment payment = paymentRepository.findById(installmentFormDto.getPaymentId()).orElseThrow(
				() -> new ObjectNotFoundException("Payment Id: " + installmentFormDto.getPaymentId() + " not found."));
		installment.setPayment(payment);
		installment.setAmount(installmentFormDto.getAmount());
		installment.setBrand(installmentFormDto.getBrand());
		return new InstallmentDto(installment);
	}

	public void deleteById(Long id) {
		installmentRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Installment Id: " + id + " not found."));
		installmentRepository.deleteById(id);
	}
}
