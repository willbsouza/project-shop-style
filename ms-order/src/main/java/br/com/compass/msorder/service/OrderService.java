package br.com.compass.msorder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compass.msorder.client.CatalogClient;
import br.com.compass.msorder.client.CustomerClient;
import br.com.compass.msorder.client.PaymentClient;
import br.com.compass.msorder.client.dto.CatalogDto;
import br.com.compass.msorder.client.dto.CustomerDto;
import br.com.compass.msorder.client.dto.PaymentDto;

@Service
public class OrderService {
	
	@Autowired
	private CustomerClient customerClient;
	
	@Autowired
	private PaymentClient paymentClient;
	
	@Autowired
	private CatalogClient catalogClient;
	
	public void getCustomer(Long id) {
		CustomerDto customer = customerClient.getCustomer(id);
		System.out.println(customer);
	}

	public void getPayment(Long id) {
		PaymentDto payment = paymentClient.getPayment(id);
		System.out.println(payment);
	}

	public void getCatalog(Long id) {
		CatalogDto catalog = catalogClient.getCatalog(id);
		System.out.println(catalog);
	}
}
