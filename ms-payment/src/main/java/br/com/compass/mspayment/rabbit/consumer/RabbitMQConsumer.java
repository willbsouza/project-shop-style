package br.com.compass.mspayment.rabbit.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.compass.mspayment.rabbit.entity.PaymentOrder;

@Component
public class RabbitMQConsumer {
		
	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@RabbitListener(queues = "${mq.queues.payment-order}")
	private void processMessage(PaymentOrder paymentOrder) {
		System.out.println(paymentOrder);
	}
}
