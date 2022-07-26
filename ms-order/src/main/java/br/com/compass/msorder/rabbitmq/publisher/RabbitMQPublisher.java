package br.com.compass.msorder.rabbitmq.publisher;

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
	
	@Value("${mq.queues.order-sku}")
	private String queueOrderSku;
	
	@Value("${mq.queues.order-payment}")
	private String queueOrderPayment;
	
	@Value("${mq.queues.order-audit}")
	private String queueOrderAudit;
	
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
		Queue orderSkuQueue = this.queue(queueOrderSku);
		Queue orderPaymentQueue = this.queue(queueOrderPayment);
		Queue orderAuditQueue = this.queue(queueOrderAudit);
		
		DirectExchange exchange = this.directExchange();
		
		Binding relateOrderSku = this.relate(orderSkuQueue, exchange);
		Binding relateOrderPayment = this.relate(orderPaymentQueue, exchange);
		Binding relateOrderAudit = this.relate(orderAuditQueue, exchange);
		
		this.ampAdmin.declareQueue(orderSkuQueue);
		this.ampAdmin.declareQueue(orderPaymentQueue);
		this.ampAdmin.declareQueue(orderAuditQueue);
		
		this.ampAdmin.declareExchange(exchange);
		
		this.ampAdmin.declareBinding(relateOrderSku);
		this.ampAdmin.declareBinding(relateOrderPayment);
		this.ampAdmin.declareBinding(relateOrderAudit);
	}
}
