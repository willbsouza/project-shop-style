package br.com.compass.msbffshop.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.compass.msbffshop.client.payment.dto.PaymentDto;

@Component
@FeignClient("payment")
public interface PaymentClient {

	@GetMapping("/v1/payments")
	List<PaymentDto> findAllPayments();
}