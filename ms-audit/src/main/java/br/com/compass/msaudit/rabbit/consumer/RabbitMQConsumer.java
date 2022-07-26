package br.com.compass.msaudit.rabbit.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.compass.msaudit.entity.Order;
import br.com.compass.msaudit.service.AuditService;

@Component
public class RabbitMQConsumer {
	
	@Autowired
	private AuditService auditService;
	
	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@RabbitListener(queues = "${mq.queues.order-audit}")
	private void processMessage(Order order) {			
		auditService.save(order);
	}
}