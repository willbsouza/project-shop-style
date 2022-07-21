package br.com.compass.mscustomer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compass.mscustomer.dto.CustomerDto;
import br.com.compass.mscustomer.dto.CustomerLoginDto;
import br.com.compass.mscustomer.service.CustomerService;

@RestController
@RequestMapping("/v1/login")
public class LoginController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping
	public ResponseEntity<CustomerDto> login(@RequestBody @Valid CustomerLoginDto customerLoginDto) {
		return new ResponseEntity<CustomerDto>(customerService.login(customerLoginDto), HttpStatus.ACCEPTED);
	}
}
