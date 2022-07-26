package br.com.compass.msorder.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.compass.msorder.client.AuditClient;
import br.com.compass.msorder.client.CatalogClient;
import br.com.compass.msorder.client.CustomerClient;
import br.com.compass.msorder.client.PaymentClient;
import br.com.compass.msorder.client.entity.Address;
import br.com.compass.msorder.client.entity.Customer;
import br.com.compass.msorder.client.entity.Installment;
import br.com.compass.msorder.client.entity.Payment;
import br.com.compass.msorder.client.entity.Sku;
import br.com.compass.msorder.entity.Order;
import br.com.compass.msorder.entity.dto.CartDto;
import br.com.compass.msorder.entity.dto.OrderDto;
import br.com.compass.msorder.entity.dto.OrderFormDto;
import br.com.compass.msorder.entity.dto.PaymentDto;
import br.com.compass.msorder.enums.Status;
import br.com.compass.msorder.rabbitmq.consumer.entity.PaymentOrderStatus;
import br.com.compass.msorder.rabbitmq.publisher.entity.PaymentOrder;
import br.com.compass.msorder.rabbitmq.publisher.entity.SkuOrder;
import br.com.compass.msorder.repository.OrderRepository;
import br.com.compass.msorder.service.exception.CustomerInactiveException;
import br.com.compass.msorder.service.exception.ObjectNotFoundException;
import br.com.compass.msorder.service.exception.QuantityUnavailableException;

@Service
public class OrderServiceImp implements OrderService {

	@Autowired
	private CustomerClient customerClient;

	@Autowired
	private PaymentClient paymentClient;

	@Autowired
	private CatalogClient catalogClient;
	
	@Autowired
	private AuditClient auditClient;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("${mq.queues.sku-order}")
	private String queueSkuOrder;
	
	@Value("${mq.queues.payment-order}")
	private String queuePaymentOrder;

	@Override
	public OrderDto save(@Valid OrderFormDto orderFormDto) {
		Order order = new Order();
		Customer customer = customerClient.getCustomer(orderFormDto.getCustomer().getId());
		if (!customer.getActive()) {
			throw new CustomerInactiveException("Customer is not active.");
		}
		Address address = customerClient.getAddress(orderFormDto.getCustomer().getAddressId());
		Payment payment = paymentClient.getPayment(orderFormDto.getPayment().getId());
		Installment installment = paymentClient.getInstallments(orderFormDto.getPayment().getId());
		installment.setAmount(orderFormDto.getPayment().getInstallments());
		installment.setPayment(payment);
		Double total = 0.0;
		List<Sku> cart = new ArrayList<>();
		for (CartDto cartDto : orderFormDto.getCart()) {
			Sku sku = catalogClient.getSku(cartDto.getSkuId());
			if(sku.getQuantity() >= cartDto.getQuantity()) {
				sku.setQuantity(cartDto.getQuantity());
			} else {
				throw new QuantityUnavailableException("Quantity unavailable Sku ID: " + sku.getId());
			}
			cart.add(sku);
			total += (sku.getPrice() * cartDto.getQuantity());
		}
		order.setCustomer(customer);
		order.setAddress(address);
		order.setPayment(payment);
		order.setInstallment(installment);
		order.setCart(cart);
		order.setDate(LocalDate.now());
		order.setStatus(Status.PROCESSING_PAYMENT);
		order.setTotal(total);
		
		orderRepository.save(order);
		auditClient.saveOrderAudit(order);
		rabbitTemplate.convertAndSend(queueSkuOrder, builderSkuOrder(order));
		rabbitTemplate.convertAndSend(queuePaymentOrder, builderPaymentOrder(order));
		return new OrderDto(order);
	}
	
	@Override
	public OrderDto updateStatusPayment(PaymentOrderStatus paymentOrderStatus) {
		Order order = orderRepository.findById(paymentOrderStatus.getOrderId()).orElseThrow(
				() -> new ObjectNotFoundException("Order ID: " + paymentOrderStatus.getOrderId() + " not found."));
		order.setStatus(paymentOrderStatus.getStatus());
		orderRepository.save(order);
		auditClient.saveOrderAudit(order);
		return new OrderDto(order);
	}

	@Override
	public List<OrderDto> findAll(LocalDate startDate,LocalDate endDate,Status status) {
		
		Stream<Order> ordersStream = orderRepository.findAll().stream().filter(o -> (o.getDate().isAfter(startDate) || o.getDate().isEqual(startDate)));
		if (endDate != null) {
			ordersStream = ordersStream.filter(o -> (o.getDate().isBefore(endDate) || o.getDate().isEqual(endDate)));
		}
		if (status != null) {
			ordersStream = ordersStream.filter(o -> (o.getStatus() == status));
		}
		return ordersStream.map(OrderDto::new).collect(Collectors.toList());
	}

	@Override
	public List<OrderDto> findByCustomerId(Long id, LocalDate startDate, LocalDate endDate, Status status) {

		Stream<Order> ordersStream = orderRepository.findByCustomerId(id).stream();
		if (status != null) {
			ordersStream = ordersStream.filter(o -> (o.getStatus() == status));
		}
		if (startDate != null) {
			ordersStream = ordersStream.filter(o -> (o.getDate().isAfter(startDate) || o.getDate().isEqual(startDate)));
		}
		if (endDate != null) {
			ordersStream = ordersStream.filter(o -> (o.getDate().isBefore(endDate) || o.getDate().isEqual(endDate)));
		}
		return ordersStream.map(OrderDto::new).collect(Collectors.toList());
	}
	
	private PaymentOrder builderPaymentOrder(Order order) {
		
		PaymentOrder paymentOrder = new PaymentOrder();
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setId(order.getPayment().getId());
		paymentDto.setInstallments(order.getInstallment().getAmount());
		paymentOrder.setOrderId(order.getId());
		paymentOrder.setPayment(paymentDto);
		return paymentOrder;
	}

	private SkuOrder builderSkuOrder(Order order) {
		SkuOrder skuOrder = new SkuOrder();
		skuOrder.setOrderId(order.getId());
		skuOrder.setSkus(order.getCart());
		return skuOrder;
	}
}