package br.com.compass.mspayment.controller;

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

import br.com.compass.mspayment.dto.InstallmentDto;
import br.com.compass.mspayment.dto.InstallmentFormDto;
import br.com.compass.mspayment.service.InstallmentService;

@RestController
@RequestMapping("/v1/installments")
public class InstallmentController {

	@Autowired
	private InstallmentService installmentService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<InstallmentDto> save(@RequestBody @Valid InstallmentFormDto installmentFormDto){
		return new ResponseEntity<InstallmentDto>(installmentService.save(installmentFormDto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<InstallmentDto> update(@PathVariable Long id, @RequestBody @Valid InstallmentFormDto installmentFormDto){
		return new ResponseEntity<InstallmentDto>(installmentService.update(id, installmentFormDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		installmentService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{paymanetId}")
	public ResponseEntity<InstallmentDto> findByPaymentId(@PathVariable Long paymanetId) {
		return new ResponseEntity<InstallmentDto>(installmentService.findByPaymentId(paymanetId), HttpStatus.OK);
	}
}