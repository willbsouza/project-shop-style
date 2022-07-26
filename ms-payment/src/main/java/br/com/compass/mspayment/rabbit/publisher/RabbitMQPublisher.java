package br.com.compass.mspayment.rabbit.publisher;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQPublisher {
	
	@Value("${mq.queues.payment-order}")
	private String queuePaymentOrder;
	
	@Value("${mq.exchange.order}")
	private String exchange;
	
	@Autowired
	private AmqpAdmin ampAdmin;
	
	private Queue queue(String queueName) {
		return new Queue(queueName, true, false, false);
	}
	
	private DirectExchange directExchange() {
		return new DirectExchange(exchange);
	}
	
	private Binding relate(Queue queue, DirectExchange exchange) {
		return new Binding(queue.getName(),Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
	}
	
	@PostConstruct
	private void add() {
		Queue orderPaymentQueue = this.queue(queuePaymentOrder);
		
		DirectExchange exchange = this.directExchange();
		
		Binding relateOrderPayment = this.relate(orderPaymentQueue, exchange);
		
		this.ampAdmin.declareQueue(orderPaymentQueue);
		
		this.ampAdmin.declareExchange(exchange);
		
		this.ampAdmin.declareBinding(relateOrderPayment);
	}
}
