package br.com.compass.msorder.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.compass.msorder.client.dto.PaymentDto;

@Component
@FeignClient("payment")
public interface PaymentClient {

	@RequestMapping("/v1/payments/{id}")
	PaymentDto getPayment(@PathVariable Long id);
	
}
