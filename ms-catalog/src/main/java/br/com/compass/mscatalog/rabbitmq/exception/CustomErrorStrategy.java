package br.com.compass.mscatalog.rabbitmq.exception;

import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler.DefaultExceptionStrategy;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;

import br.com.compass.mscatalog.service.exception.QuantityUnavailableException;

public class CustomErrorStrategy extends DefaultExceptionStrategy{

	@Override
	public boolean isFatal(Throwable t) {
		System.out.println(new String(((ListenerExecutionFailedException) t).getFailedMessage().getBody()));
		
		return t.getCause() instanceof QuantityUnavailableException;
	}
}
