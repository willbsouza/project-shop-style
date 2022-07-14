package br.com.compass.mscatalog.rabbitmq.config;

import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

//	@Bean
//	public RabbitListenerContainerFactory<DirectMessageListenerContainer> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
//		DirectRabbitListenerContainerFactory factory = new DirectRabbitListenerContainerFactory();
//		
//		factory.setConnectionFactory(connectionFactory);
//		factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
//		
//		factory.setPrefetchCount(4);
//		
//		factory.setErrorHandler(errorHandler());
//		
//		return factory;
//	}
//	
//	@Bean
//	public FatalExceptionStrategy customErrorStrategy() {
//		return new CustomErrorStrategy(); 
//	}
//	
//	@Bean
//	public ErrorHandler errorHandler() {
//		return new ConditionalRejectingErrorHandler(customErrorStrategy());
//	}
}
