package br.com.compass.msorder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compass.msorder.service.OrderService;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/customer/{id}")
	public void testControllerCustomer(@PathVariable Long id) {
		orderService.getCustomer(id);
	}
	
	@GetMapping("/payment/{id}")
	public void testControllerPayment(@PathVariable Long id) {
		orderService.getPayment(id);
	}
	
	@GetMapping("/sku/{id}")
	public void testControllerSku(@PathVariable Long id) {
		orderService.getCatalog(id);
	}
	
}
