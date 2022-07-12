package br.com.compass.msorder.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.compass.msorder.client.entity.Payment;

@Component
@FeignClient("payment")
public interface PaymentClient {

	@RequestMapping("/v1/payments/{id}")
	Payment getPayment(@RequestParam Long id);
	
}
