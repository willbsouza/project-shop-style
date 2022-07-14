package br.com.compass.msorder.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
import br.com.compass.msorder.enums.Status;
import br.com.compass.msorder.rabbitmq.entity.SkuOrder;
import br.com.compass.msorder.repository.OrderRepository;

@Service
public class OrderServiceImp implements OrderService {

	@Autowired
	private CustomerClient customerClient;

	@Autowired
	private PaymentClient paymentClient;

	@Autowired
	private CatalogClient catalogClient;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("${mq.queues.sku-order}")
	private String queueSkuOrder;

	public OrderDto save(OrderFormDto orderFormDto) {
		Order order = new Order();
		Customer customer = customerClient.getCustomer(orderFormDto.getCustomer().getId());
		Address address = customerClient.getAddress(orderFormDto.getCustomer().getAddressId());
		Payment payment = paymentClient.getPayment(orderFormDto.getPayment().getId());
		Installment installment = new Installment();
		installment.setAmount(orderFormDto.getPayment().getInstallments());
		installment.setPayment(payment);
		Double total = 0.0;
		List<Sku> cart = new ArrayList<>();
		for (CartDto cartDto : orderFormDto.getCart()) {
			Sku sku = catalogClient.getSku(cartDto.getSkuId());
			sku.setQuantity(cartDto.getQuantity());
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
		
		SkuOrder skuOrder = new SkuOrder();
		skuOrder.setOrderId(order.getId());
		skuOrder.setSkus(order.getCart());
		
		rabbitTemplate.convertAndSend(queueSkuOrder, skuOrder);
		return new OrderDto(orderRepository.save(order));
	}

	public List<OrderDto> findAll() {
		return orderRepository.findAll().stream().map(OrderDto::new).collect(Collectors.toList());
	}

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
}