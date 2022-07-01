package br.com.compass.mscatalog.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.compass.mscatalog.rabbitmq.consts.RabbitMQConsts;

@Component
public class RabbitMQConsumer {

	@RabbitListener(queues = RabbitMQConsts.QUEUE_STOCK)
	private void consumer(Long test) {
		System.out.println("--------------------------");
		System.out.println(test);
		System.out.println("--------------------------");	
	}
}
