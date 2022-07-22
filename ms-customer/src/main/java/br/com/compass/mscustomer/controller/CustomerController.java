package br.com.compass.mscustomer.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.compass.mscustomer.dto.CustomerChangePasswordDto;
import br.com.compass.mscustomer.dto.CustomerDto;
import br.com.compass.mscustomer.dto.CustomerFormDto;
import br.com.compass.mscustomer.dto.CustomerLoginDto;
import br.com.compass.mscustomer.entity.Customer;
import br.com.compass.mscustomer.service.CustomerService;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@GetMapping("/{id}")
	public ResponseEntity<CustomerDto> findById(@PathVariable Long id) {
		return new ResponseEntity<CustomerDto>(customerService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<CustomerDto> save(@RequestBody @Valid CustomerFormDto customerFormDto) {
		return new ResponseEntity<CustomerDto>(customerService.save(customerFormDto), HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<CustomerDto> login(@RequestBody @Valid CustomerLoginDto customerLoginDto) {
		return new ResponseEntity<CustomerDto>(customerService.login(customerLoginDto), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CustomerDto> update(@RequestBody @Valid CustomerFormDto customerFormDto, @PathVariable Long id) {
		return new ResponseEntity<CustomerDto>(customerService.updateById(customerFormDto, id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}/password")
	@Transactional
	public ResponseEntity<CustomerDto> changePassword(@RequestBody @Valid CustomerChangePasswordDto passwordDto, @PathVariable Long id) {
		return new ResponseEntity<CustomerDto>(customerService.changePassword(passwordDto, id), HttpStatus.ACCEPTED);
	}
	
	@GetMapping
	public ResponseEntity<Customer> findByEmail(@RequestParam(required = true) String email) {
		return new ResponseEntity<Customer>(customerService.findByEmail(email), HttpStatus.OK);
	}
}
