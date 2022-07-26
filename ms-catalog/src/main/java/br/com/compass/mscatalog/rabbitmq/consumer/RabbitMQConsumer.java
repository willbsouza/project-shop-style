package br.com.compass.mscatalog.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.compass.mscatalog.entity.Sku;
import br.com.compass.mscatalog.rabbitmq.entity.SkuOrder;
import br.com.compass.mscatalog.service.SkuService;

@Component
public class RabbitMQConsumer {
	
	@Autowired
	private SkuService skuService;
	
	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@RabbitListener(queues = "${mq.queues.order-sku}")
	private void processMessage(SkuOrder skuOrder) {			
		for(Sku sku : skuOrder.getSkus()) {
			skuService.updateOrderSku(sku.getId(), sku.getQuantity());
		}
	}
}
