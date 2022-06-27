package br.com.compass.msorder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compass.msorder.client.CustomerClient;
import br.com.compass.msorder.client.dto.InfoCustomerDto;

@Service
public class OrderService {
	
	@Autowired
	private CustomerClient customerClient;
	
	public void getCustomer(Long id) {
		InfoCustomerDto customer = customerClient.getCustomer(id);
		System.out.println(customer.getEmail());
	}
}
