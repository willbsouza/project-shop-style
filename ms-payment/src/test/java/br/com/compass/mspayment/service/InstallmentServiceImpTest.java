package br.com.compass.mspayment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.compass.mspayment.dto.InstallmentDto;
import br.com.compass.mspayment.dto.InstallmentFormDto;
import br.com.compass.mspayment.entity.Installment;
import br.com.compass.mspayment.entity.Payment;
import br.com.compass.mspayment.repository.InstallmentRepository;
import br.com.compass.mspayment.repository.PaymentRepository;
import br.com.compass.mspayment.service.exception.ObjectNotFoundException;
import br.com.compass.mspayment.service.exception.PaymentNotValidException;

class InstallmentServiceImpTest {

	private static final long ID = 1L;
	
	@InjectMocks
	private InstallmentServiceImp installmentService;
	
	@Mock
	private InstallmentRepository installmentRepository;
	
	@Mock
	private PaymentRepository paymentRepository;
	
	private Installment installment;
	
	private InstallmentFormDto installmentFormDto;
	
	private InstallmentFormDto installmentAmountLessThanZero;
	
	private Optional<Installment> optInstallment;
	
	private Payment payment;
	
	private Payment paymentNotActive;
	
	private Payment paymentNotInstallments;
	
	private Optional<Payment> optPayment;
	
	private Optional<Payment> optPaymentNotActive;
	
	private Optional<Payment> optPaymentNotInstallments;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startPayment();
	}
	
	@Test
	void whenSaveThenReturnAInstallmentDto() {
		when(paymentRepository.findById(anyLong())).thenReturn(optPayment);
		when(installmentRepository.save(any())).thenReturn(installment);
		
		InstallmentDto response = installmentService.save(installmentFormDto);
		
		assertNotNull(response);
		assertEquals(InstallmentDto.class, response.getClass());
		assertEquals(1, response.getAmount());
		assertEquals("Brand", response.getBrand());
		assertEquals(ID, response.getId());
	}
	
	@Test
	void whenSaveThenReturnObjectNotFoundException() {
		when(paymentRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Payment Id: " + ID + " not found."));
		when(installmentRepository.save(any())).thenReturn(installment);
		try {
			installmentService.save(installmentFormDto);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Payment Id: " + ID + " not found.", e.getMessage());
		}
	}
	
	@Test
	void whenSavePaymentNotActiveThenReturnPaymentNotValidException() {
		when(paymentRepository.findById(anyLong())).thenReturn(optPaymentNotActive);
		when(installmentRepository.save(any())).thenReturn(installment);
			
		try {
			installmentService.save(installmentFormDto);
		} catch (Exception e) {
			assertEquals(PaymentNotValidException.class, e.getClass());
			assertEquals("Choosed payment method not active!", e.getMessage());
		}
	}
	
	@Test
	void whenSavePaymentDoesntAcceptInstallmentsWithAmountGreaterThanZeroThenReturnPaymentNotValidException() {
		when(paymentRepository.findById(anyLong())).thenReturn(optPaymentNotInstallments);
		when(installmentRepository.save(any())).thenReturn(installment);
			
		try {
			installmentService.save(installmentFormDto);
		} catch (Exception e) {
			assertEquals(PaymentNotValidException.class, e.getClass());
			assertEquals("Choosed payment method does not accept installment!", e.getMessage());
		}
	}
	
	@Test
	void whenSaveWithAmountLessThanZeroThenReturnPaymentNotValidException() {
		when(paymentRepository.findById(anyLong())).thenReturn(optPayment);
		when(installmentRepository.save(any())).thenReturn(installment);
			
		try {
			installmentService.save(installmentAmountLessThanZero);
		} catch (Exception e) {
			assertEquals(PaymentNotValidException.class, e.getClass());
			assertEquals("Amount cannot be less than 0!", e.getMessage());
		}
	}
	
	@Test
	void whenDeleteByIdThenDeleteWithSuccess() {
		when(installmentRepository.findById(anyLong())).thenReturn(optInstallment);
		doNothing().when(installmentRepository).deleteById(anyLong());
		installmentRepository.deleteById(ID);
		
		verify(installmentRepository, times(1)).deleteById(anyLong());
	}
	
	@Test
	void whenDeleteByIdThenReturnObjectNotFoundException() {
		when(installmentRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Installment Id: " + ID + " not found."));
		
		try {
			installmentRepository.deleteById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Installment Id: " + ID + " not found.", e.getMessage());
		}
	}
	
	@Test
	void whenUpdateThenReturnAInstallmentDto() {
		when(installmentRepository.findById(anyLong())).thenReturn(optInstallment);
		when(paymentRepository.findById(anyLong())).thenReturn(optPayment);
		when(installmentRepository.save(any())).thenReturn(installment);
		
		InstallmentDto response = installmentService.update(ID, installmentFormDto);
		
		assertNotNull(response);
		assertEquals(InstallmentDto.class, response.getClass());
		assertEquals(1, response.getAmount());
		assertEquals("Brand", response.getBrand());
		assertEquals(ID, response.getId());
	}
	
	@Test
	void whenUpdateInstallmentNotFoundThenReturnObjectNotFoundException() {
		when(installmentRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Installment Id: " + ID + " not found."));
		when(paymentRepository.findById(anyLong())).thenReturn(optPayment);
		try {
			installmentService.update(ID, installmentFormDto);			
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Installment Id: " + ID + " not found.", e.getMessage());
		}
	}
	
	@Test
	void whenUpdatePaymentNotFoundThenReturnObjectNotFoundException() {
		when(installmentRepository.findById(anyLong())).thenReturn(optInstallment);
		when(paymentRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Payment Id: " + ID + " not found."));
		try {
			installmentService.update(ID, installmentFormDto);			
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Payment Id: " + ID + " not found.", e.getMessage());
		}
	}
	
	@Test
	void whenFindByPaymentIdThenReturnAInstallmentDto() {
		when(installmentRepository.findByPaymentId(anyLong())).thenReturn(installment);
		
		InstallmentDto response = installmentService.findByPaymentId(ID);			
			
		assertNotNull(response);
		assertEquals(InstallmentDto.class, response.getClass());
		assertEquals(1, response.getAmount());
		assertEquals("Brand", response.getBrand());
		assertEquals(ID, response.getId());
	}

	private void startPayment() {
		payment = new Payment();
		payment.setActive(true);
		payment.setInstallments(true);
		payment.setType("Type");
		payment.setId(ID);
		
		optPayment = Optional.of(payment);
		
		paymentNotActive = new Payment();
		paymentNotActive.setActive(false);
		paymentNotActive.setInstallments(true);
		paymentNotActive.setType("Type");
		paymentNotActive.setId(ID);
		
		optPaymentNotActive = Optional.of(paymentNotActive);
		
		paymentNotInstallments = new Payment();
		paymentNotInstallments.setActive(true);
		paymentNotInstallments.setInstallments(false);
		paymentNotInstallments.setType("Type");
		paymentNotInstallments.setId(ID);
		
		optPaymentNotInstallments = Optional.of(paymentNotInstallments);

		installmentFormDto = new InstallmentFormDto();
		installmentFormDto.setAmount(1);
		installmentFormDto.setBrand("Brand");
		installmentFormDto.setPaymentId(ID);
		
		installment = new Installment(installmentFormDto, payment);
		installment.setId(ID);
		
		optInstallment = Optional.of(installment);
		
		installmentAmountLessThanZero = new InstallmentFormDto();
		installmentAmountLessThanZero.setAmount(-1);
		installmentAmountLessThanZero.setBrand("Brand");
		installmentAmountLessThanZero.setPaymentId(ID);
	}
}