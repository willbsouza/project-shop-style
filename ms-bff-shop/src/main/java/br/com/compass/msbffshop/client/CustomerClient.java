package br.com.compass.msbffshop.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.compass.msbffshop.client.customer.dto.AddressDto;
import br.com.compass.msbffshop.client.customer.dto.AddressFormDto;
import br.com.compass.msbffshop.client.customer.dto.CustomerChangePasswordDto;
import br.com.compass.msbffshop.client.customer.dto.CustomerDto;
import br.com.compass.msbffshop.client.customer.dto.CustomerFormDto;
import br.com.compass.msbffshop.client.customer.dto.CustomerLoginDto;

@Component
@FeignClient("customer")
public interface CustomerClient {

	@PostMapping("/v1/login")
	CustomerDto loginCustomer(@RequestBody CustomerLoginDto customerLoginDto);
	
	@PostMapping("/v1/customers")
	CustomerDto saveCustomer(@RequestBody CustomerFormDto customerFormDto);
	
	@GetMapping("/v1/customers/{id}")
	CustomerDto getCustomer(@PathVariable Long id);
	
	@PutMapping("/v1/customers/{id}")
	CustomerDto updateCustomerById(@PathVariable Long id, @RequestBody CustomerFormDto customerFormDto);
	
	@PutMapping("/v1/customers/{id}/password")
	CustomerDto changePasswordCustomer(@RequestBody CustomerChangePasswordDto passwordDto, @PathVariable Long id);
	
	@PostMapping("/v1/addresses")
	AddressDto saveAddress(@RequestBody AddressFormDto addressFormDto);

	@PutMapping("/v1/addresses/{id}")
	AddressDto updateAddressById(@PathVariable Long id, @RequestBody AddressFormDto addressFormDto);
	
	@DeleteMapping("/v1/addresses/{id}")
	void deleteAddressById(@PathVariable Long id);
}
