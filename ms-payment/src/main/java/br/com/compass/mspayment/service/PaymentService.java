package br.com.compass.mspayment.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compass.mspayment.dto.PaymentDto;
import br.com.compass.mspayment.dto.PaymentFormDto;
import br.com.compass.mspayment.entity.Payment;
import br.com.compass.mspayment.repository.PaymentRepository;
import br.com.compass.mspayment.service.exception.ObjectNotFoundException;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;

	public List<PaymentDto> findAll() {
		return paymentRepository.findAll().stream().map(PaymentDto::new).collect(Collectors.toList());
	}

	public PaymentDto save(@Valid PaymentFormDto paymentFormDto) {
		return new PaymentDto(paymentRepository.save(new Payment(paymentFormDto)));
	}

	public PaymentDto update(Long id, @Valid PaymentFormDto paymentFormDto) {
		Payment payment = paymentRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Payment Id: " + id + " not found."));
		payment.setType(paymentFormDto.getType());
		payment.setActive(paymentFormDto.getActive());
		payment.setInstallments(paymentFormDto.getInstallments());
		
		return new PaymentDto(payment);
	}

	public void deleteById(Long id) {
		paymentRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Payment Id: " + id + " not found."));
		paymentRepository.deleteById(id);
	}

	public PaymentDto findById(Long id) {
		return new PaymentDto(paymentRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Payment Id: " + id + " not found.")));
	}
}
