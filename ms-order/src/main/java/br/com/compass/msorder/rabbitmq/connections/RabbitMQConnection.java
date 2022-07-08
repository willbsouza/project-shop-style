package br.com.compass.msorder.rabbitmq.connections;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.compass.msorder.rabbitmq.consts.RabbitMQConsts;

@Component
public class RabbitMQConnection {

	private static final String EXCHANGE_NAME = "amq.direct";
	
	@Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
	
	@Autowired
	private AmqpAdmin ampAdmin;
	
	private Queue queue(String queueName) {
		return new Queue(queueName, true, false, false);
	}
	
	private DirectExchange directExchange() {
		return new DirectExchange(EXCHANGE_NAME);
	}
	
	private Binding relate(Queue queue, DirectExchange exchange) {
		return new Binding(queue.getName(),Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
	}
	
	@PostConstruct
	private void add() {
		Queue queueStock = this.queue(RabbitMQConsts.QUEUE_STOCK);
		
		DirectExchange exchange = this.directExchange();
		
		Binding relate = this.relate(queueStock, exchange);
		
		this.ampAdmin.declareQueue(queueStock);
		
		this.ampAdmin.declareExchange(exchange);
		
		this.ampAdmin.declareBinding(relate);
	}
}
