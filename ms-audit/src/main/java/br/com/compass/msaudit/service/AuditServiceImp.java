package br.com.compass.msaudit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compass.msaudit.dto.OrderDto;
import br.com.compass.msaudit.entity.Order;
import br.com.compass.msaudit.repository.AuditRepository;
import br.com.compass.msaudit.service.exception.ObjectNotFoundException;

@Service
public class AuditServiceImp implements AuditService{

	@Autowired
	private AuditRepository auditRepository;
	
	@Override
	public OrderDto save(Order order) {
		return new OrderDto(auditRepository.save(order));
	}

	@Override
	public OrderDto findById(String id) {
		
		return new OrderDto(auditRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Order ID: " + id + " not found.")));
	}
}
