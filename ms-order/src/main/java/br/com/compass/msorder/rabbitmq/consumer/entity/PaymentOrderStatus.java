package br.com.compass.msorder.rabbitmq.consumer.entity;

import java.io.Serializable;

import br.com.compass.msorder.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentOrderStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	private String orderId;
	private Status status;
}
