package br.com.compass.msorder.rabbitmq.connections;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.compass.msorder.rabbitmq.consts.RabbitMQConsts;

@Component
public class RabbitMQConnection {

	private static final String EXCHANGE_NAME = "amq.direct";
	
	@Autowired
	private AmqpAdmin ampAdmin;
	
	private Queue queue(String queueName) {
		return new Queue(queueName, true, false, false);
	}
	
	private DirectExchange directChange() {
		return new DirectExchange(EXCHANGE_NAME);
	}
	
	private Binding relationship(Queue queue, DirectExchange change) {
		return new Binding(queue.getName(),Binding.DestinationType.QUEUE, change.getName(), queue.getName(), null);
	}
	
	@PostConstruct
	private void add() {
		Queue queueStock = this.queue(RabbitMQConsts.QUEUE_STOCK);
		
		DirectExchange change = this.directChange();
		
		Binding callStock = this.relationship(queueStock, change);
		
		this.ampAdmin.declareQueue(queueStock);
		
		this.ampAdmin.declareExchange(change);
		
		this.ampAdmin.declareBinding(callStock);
	}
}
