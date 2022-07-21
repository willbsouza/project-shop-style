package br.com.compass.msbffshop.client;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.compass.msbffshop.client.enums.Status;
import br.com.compass.msbffshop.client.order.dto.OrderDto;
import br.com.compass.msbffshop.client.order.dto.OrderFormDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Component
@FeignClient("order")
public interface OrderClient {
	
	@PostMapping("/v1/orders")
	OrderDto saveOrder(@RequestBody OrderFormDto orderFormDto);

	@GetMapping("/v1/orders/customers/{customerId}")
	List<OrderDto> findOrdersByCustomerId(@PathVariable Long customerId, @RequestParam(required = false) LocalDate startDate, 
			@RequestParam(required = false) LocalDate endDate, @RequestParam(required = false) Status status);
}
