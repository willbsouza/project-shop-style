package br.com.compass.msorder.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.compass.msorder.client.entity.Address;
import br.com.compass.msorder.client.entity.Customer;

@Component
@FeignClient("customer")
public interface CustomerClient {

	@GetMapping("/v1/customers/{id}")
	Customer getCustomer(@PathVariable Long id);
	
	@GetMapping("/v1/addresses/{id}")
	Address getAddress(@PathVariable Long id);
}
