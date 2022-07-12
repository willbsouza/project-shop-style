package br.com.compass.msorder.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.compass.msorder.client.entity.Address;
import br.com.compass.msorder.client.entity.Customer;

@Component
@FeignClient("customer")
public interface CustomerClient {

	@RequestMapping("/v1/customers/{id}")
	Customer getCustomer(@PathVariable Long id);
	
	@RequestMapping("/v1/addresses/{id}")
	Address getAddress(@PathVariable Long id);
}
