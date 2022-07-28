package br.com.compass.mspayment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.compass.mspayment.dto.PaymentDto;
import br.com.compass.mspayment.dto.PaymentFormDto;
import br.com.compass.mspayment.entity.Payment;
import br.com.compass.mspayment.repository.PaymentRepository;
import br.com.compass.mspayment.service.exception.ObjectNotFoundException;

class PaymentServiceImpTest {
	
	private static final long ID = 1L;
	
	@InjectMocks
	private PaymentServiceImp paymentService;
	
	@Mock
	private PaymentRepository paymentRepository;
	
	private Payment payment;
	
	private PaymentFormDto paymentFormDto;
	
	private Optional<Payment> optPayment;
	
	private List<Payment> paymentList;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startPayment();
	}

	@Test
	void whenFindAllThenReturnListOfPaymentDto() {
		when(paymentRepository.findAll()).thenReturn(paymentList);
		
		List<PaymentDto> response = paymentService.findAll();
		
		assertNotNull(response);
		assertEquals(PaymentDto.class, response.get(0).getClass());
		assertEquals(1, response.size());
		assertEquals(true, response.get(0).getActive());
		assertEquals(true, response.get(0).getInstallments());
		assertEquals("Type", response.get(0).getType());
		assertEquals(ID, response.get(0).getId());
	}
	
	@Test
	void whenSaveThenReturnAPaymentDto() {
		when(paymentRepository.save(any())).thenReturn(payment);
		
		PaymentDto response = paymentService.save(paymentFormDto);
		
		assertNotNull(response);
		assertEquals(PaymentDto.class, response.getClass());
		assertEquals(true, response.getActive());
		assertEquals(true, response.getInstallments());
		assertEquals("Type", response.getType());
		assertEquals(ID, response.getId());
	}
	
	@Test
	void whenUpdateThenReturnAPaymentDto() {
		when(paymentRepository.findById(anyLong())).thenReturn(optPayment);
		when(paymentRepository.save(any())).thenReturn(payment);
		
		PaymentDto response = paymentService.update(ID, paymentFormDto);
		
		assertNotNull(response);
		assertEquals(PaymentDto.class, response.getClass());
		assertEquals(true, response.getActive());
		assertEquals(true, response.getInstallments());
		assertEquals("Type", response.getType());
		assertEquals(ID, response.getId());
	}
	
	@Test
	void whenUpdateThenReturnObjectNotFoundException() {
		when(paymentRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Payment Id: " + ID + " not found."));
		
		try {
			paymentService.update(ID, paymentFormDto);			
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Payment Id: " + ID + " not found.", e.getMessage());
		}
	}
	
	@Test
	void whenDeleteByIdThenDeleteWithSuccess() {
		when(paymentRepository.findById(anyLong())).thenReturn(optPayment);
		doNothing().when(paymentRepository).deleteById(anyLong());
		paymentRepository.deleteById(ID);
		
		verify(paymentRepository, times(1)).deleteById(anyLong());
	}
	
	@Test
	void whenDeleteByIdThenReturnObjectNotFoundException() {
		when(paymentRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Payment Id: " + ID + " not found."));
		
		try {
			paymentService.deleteById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Payment Id: " + ID + " not found.", e.getMessage());
		}
	}
	
	@Test
	void whenFindByIdThenReturnAPaymentDto() {
		when(paymentRepository.findById(anyLong())).thenReturn(optPayment);
		
		PaymentDto response = paymentService.findById(ID);
		
		assertNotNull(response);
		assertEquals(PaymentDto.class, response.getClass());
		assertEquals(true, response.getActive());
		assertEquals(true, response.getInstallments());
		assertEquals("Type", response.getType());
		assertEquals(ID, response.getId());
	}
	
	@Test
	void whenFindByIdThenReturnObjectNotFoundException() {
		when(paymentRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Payment Id: " + ID + " not found."));
		
		try {
			paymentService.findById(ID);			
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Payment Id: " + ID + " not found.", e.getMessage());
		}
	}

	private void startPayment() {
		paymentFormDto = new PaymentFormDto();
		paymentFormDto.setActive(true);
		paymentFormDto.setInstallments(true);
		paymentFormDto.setType("Type");
		
		payment = new Payment(paymentFormDto);
		payment.setId(ID);
		
		optPayment = Optional.of(payment);
		
		paymentList = List.of(payment);
	}
}
