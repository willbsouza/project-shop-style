package br.com.compass.msorder.rabbitmq.config;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	
	@Value("${mq.queues.sku-order}")
	private String queueName;
	
	@Value("${mq.exchange.sku-order}")
	private String exchangeSkuOrder;
	
	@Autowired
	private AmqpAdmin ampAdmin;
	
	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	private Queue queue(String queueName) {
		return new Queue(queueName, true, false, false);
	}
	
	private DirectExchange directExchange() {
		return new DirectExchange(exchangeSkuOrder);
	}
	
	private Binding relate(Queue queue, DirectExchange exchange) {
		return new Binding(queue.getName(),Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
	}
	
	@PostConstruct
	private void add() {
		Queue queueSkuOrder = this.queue(queueName);
		
		DirectExchange exchange = this.directExchange();
		
		Binding relate = this.relate(queueSkuOrder, exchange);
		
		this.ampAdmin.declareQueue(queueSkuOrder);
		
		this.ampAdmin.declareExchange(exchange);
		
		this.ampAdmin.declareBinding(relate);
	}
}
