package br.com.compass.msaudit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compass.msaudit.dto.OrderDto;
import br.com.compass.msaudit.entity.Order;
import br.com.compass.msaudit.service.AuditService;

@RestController
@RequestMapping("/v1/audit")
public class AuditController {
	
	@Autowired
	private AuditService auditService;
	
	@GetMapping("/orders/{id}")
	public ResponseEntity<OrderDto> findBYid(@PathVariable String id) {
		return new ResponseEntity<OrderDto>(auditService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<OrderDto> save(@RequestBody Order order){
		return new ResponseEntity<OrderDto>(auditService.save(order), HttpStatus.CREATED);
	}
}
