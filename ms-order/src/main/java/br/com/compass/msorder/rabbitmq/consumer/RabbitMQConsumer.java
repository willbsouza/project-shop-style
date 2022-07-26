package br.com.compass.msorder.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.compass.msorder.rabbitmq.consumer.entity.PaymentOrderStatus;
import br.com.compass.msorder.service.OrderService;

@Component
public class RabbitMQConsumer {
	
	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Autowired
	private OrderService orderService;

	@RabbitListener(queues = "${mq.queues.payment-order}")
	private void processMessage(PaymentOrderStatus paymentOrderStatus) {			
		orderService.updateStatusPayment(paymentOrderStatus);
	}
}
