package br.com.compass.mspayment.rabbit.consumer;

import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.compass.mspayment.dto.InstallmentDto;
import br.com.compass.mspayment.entity.Payment;
import br.com.compass.mspayment.rabbit.consumer.entity.PaymentOrder;
import br.com.compass.mspayment.rabbit.publisher.entity.PaymentOrderStatus;
import br.com.compass.mspayment.rabbit.publisher.enums.Status;
import br.com.compass.mspayment.repository.PaymentRepository;
import br.com.compass.mspayment.service.InstallmentService;

@Component
public class RabbitMQConsumer {
		
	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Value("${mq.queues.payment-order}")
	private String queuePaymentOrder;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private InstallmentService installmentService;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@RabbitListener(queues = "${mq.queues.order-payment}")
	private void processMessage(PaymentOrder paymentOrder) {
		Status status = statusCheck(paymentOrder);
		rabbitTemplate.convertAndSend(queuePaymentOrder, new PaymentOrderStatus(paymentOrder.getOrderId(), status));
	}
	
	private Status statusCheck(PaymentOrder paymentOrder) {
		
		Optional<Payment> optPayment = paymentRepository.findById(paymentOrder.getPayment().getId());
		InstallmentDto installmentDto = installmentService.findByPaymentId(paymentOrder.getPayment().getId());
		Status status = Status.PAYMENT_SUCCESSFUL;
		if(optPayment.isPresent()) {
			if(optPayment.get().getActive()) {
				if (paymentOrder.getPayment().getInstallments() > 0) {
					if(!optPayment.get().getInstallments()) {
						status = Status.PAYMENT_NOT_INSTALLMENT;
					}
					else if (paymentOrder.getPayment().getInstallments() <= installmentDto.getAmount()){
						status = Status.PAYMENT_SUCCESSFUL;
					}
					else {
						status = Status.PAYMENT_AMOUNT_NOT_AVAILABLE;
					}
				} else {
					status = Status.PAYMENT_SUCCESSFUL;
				}
			} else {
				status = Status.PAYMENT_INACTIVE;
			}
		} else {
			status = Status.PAYMENT_NOT_FOUND;
		}
		return status;
	}
}
