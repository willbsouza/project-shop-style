package br.com.compass.msorder.service;

import java.time.LocalDate;
import java.util.List;

import br.com.compass.msorder.entity.dto.OrderDto;
import br.com.compass.msorder.entity.dto.OrderFormDto;
import br.com.compass.msorder.enums.Status;

public interface OrderService {

	OrderDto save(OrderFormDto orderFormDto);

	List<OrderDto> findAll();

	List<OrderDto> findByCustomerId(Long id, LocalDate startDate, LocalDate endDate, Status status);
}
