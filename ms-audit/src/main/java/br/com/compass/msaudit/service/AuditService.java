package br.com.compass.msaudit.service;

import br.com.compass.msaudit.dto.OrderDto;
import br.com.compass.msaudit.entity.Order;

public interface AuditService {

	OrderDto save(Order order);

	OrderDto findById(String id);
}
