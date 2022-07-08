package br.com.compass.mscatalog.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.compass.mscatalog.rabbitmq.consts.RabbitMQConsts;
import br.com.compass.mscatalog.rabbitmq.dto.CartDto;
import br.com.compass.mscatalog.service.SkuServiceImp;

@Component
public class RabbitMQConsumer {
	
	@Autowired
	private SkuServiceImp skuService;
	
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@RabbitListener(queues = RabbitMQConsts.QUEUE_STOCK)
	private void processMessage(CartDto cartDto) {
		skuService.updateOrderSku(cartDto.getSkuId(), cartDto.getQuantity());
	}
}
