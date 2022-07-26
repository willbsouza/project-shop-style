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
import br.com.compass.mspayment.service.exception.PaymentNotValidException;

@Service
public class InstallmentServiceImp implements InstallmentService{
	
	@Autowired
	private InstallmentRepository installmentRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public InstallmentDto save(@Valid InstallmentFormDto installmentFormDto) {
		Payment payment = paymentRepository.findById(installmentFormDto.getPaymentId()).orElseThrow(
				() -> new ObjectNotFoundException("Payment Id: " + installmentFormDto.getPaymentId() + " not found."));
		installmentValidation(installmentFormDto, payment);
			return new InstallmentDto(installmentRepository.save(new Installment(installmentFormDto, payment)));
	}

	@Override
	public InstallmentDto update(Long id, @Valid InstallmentFormDto installmentFormDto) {
		installmentRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Installment Id: " + id + " not found."));
		Payment payment = paymentRepository.findById(installmentFormDto.getPaymentId()).orElseThrow(
				() -> new ObjectNotFoundException("Payment Id: " + installmentFormDto.getPaymentId() + " not found."));
		installmentValidation(installmentFormDto, payment);
		return new InstallmentDto(installmentRepository.save(new Installment(installmentFormDto, payment)));
	}

	@Override
	public void deleteById(Long id) {
		installmentRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Installment Id: " + id + " not found."));
		installmentRepository.deleteById(id);
	}
	
	@Override
	public InstallmentDto findByPaymentId(Long paymanetId) {
		Installment installment = installmentRepository.findByPaymentId(paymanetId);
		if(installment != null) {
			return new InstallmentDto(installment);
		}
		throw new ObjectNotFoundException("Installment not found to paymentId: " + paymanetId + " informed.");
	}
	
	private Boolean installmentValidation(InstallmentFormDto installmentFormDto, Payment payment) {
		if(!payment.getActive()) {
			throw new PaymentNotValidException("Choosed payment method not active!");
		}
		if(!payment.getInstallments() && installmentFormDto.getAmount() > 0) {
			throw new PaymentNotValidException("Choosed payment method does not accept installment!");
		}
		if(installmentFormDto.getAmount() < 0) {
			throw new PaymentNotValidException("Amount cannot be less than 0!");
		}
		return true;
	}
}