package br.com.compass.msorder.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.compass.msorder.client.dto.CustomerDto;

@Component
@FeignClient("customer")
public interface CustomerClient {

	@RequestMapping("/v1/customers/{id}")
	CustomerDto getCustomer(@PathVariable Long id);
}
