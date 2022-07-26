package br.com.compass.msorder.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.compass.msorder.client.entity.Installment;
import br.com.compass.msorder.client.entity.Payment;

@Component
@FeignClient("payment")
public interface PaymentClient {

	@GetMapping("/v1/payments/{id}")
	Payment getPayment(@RequestParam Long id);
	
	@GetMapping("/v1/installments/{id}")
	Installment getInstallments(@RequestParam Long id);
}
