package br.com.compass.msorder.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.compass.msorder.entity.dto.OrderDto;
import br.com.compass.msorder.entity.dto.OrderFormDto;
import br.com.compass.msorder.enums.Status;
import br.com.compass.msorder.rabbitmq.consts.RabbitMQConsts;
import br.com.compass.msorder.rabbitmq.service.RabbitMQService;
import br.com.compass.msorder.service.OrderService;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private RabbitMQService rabbitMQService;
		
	@GetMapping
	public ResponseEntity<List<OrderDto>> findAll(){
		return new ResponseEntity<List<OrderDto>>(orderService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/customers/{id}")
	public ResponseEntity<List<OrderDto>> findByCustomerId(@PathVariable Long id, @RequestParam(required = false) LocalDate startDate, 
			@RequestParam(required = false) LocalDate endDate, @RequestParam(required = false) Status status){
		return new ResponseEntity<List<OrderDto>>(orderService.findByCustomerId(id, startDate, endDate, status), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<OrderDto> save(@RequestBody OrderFormDto orderFormDto){
		this.rabbitMQService.sendMessage(RabbitMQConsts.QUEUE_STOCK, orderFormDto.getCustomer().getAddressId());
		return new ResponseEntity<OrderDto>(orderService.save(orderFormDto), HttpStatus.CREATED);
	}
}
