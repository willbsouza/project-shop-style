package br.com.compass.mspayment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compass.mspayment.dto.PaymentDto;
import br.com.compass.mspayment.dto.PaymentFormDto;
import br.com.compass.mspayment.service.PaymentService;

@RestController
@RequestMapping("/v1/payments")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;

	@GetMapping
	public ResponseEntity<List<PaymentDto>> findAll() {
		return new ResponseEntity<List<PaymentDto>>(paymentService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<PaymentDto> save(@RequestBody @Valid PaymentFormDto paymentFormDto){
		return new ResponseEntity<PaymentDto>(paymentService.save(paymentFormDto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PaymentDto> update(@PathVariable Long id, @RequestBody @Valid PaymentFormDto paymentFormDto){
		return new ResponseEntity<PaymentDto>(paymentService.update(id, paymentFormDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		paymentService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
