package br.com.compass.msorder.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import br.com.compass.msorder.client.CatalogClient;
import br.com.compass.msorder.client.CustomerClient;
import br.com.compass.msorder.client.PaymentClient;
import br.com.compass.msorder.client.entity.Address;
import br.com.compass.msorder.client.entity.Customer;
import br.com.compass.msorder.client.entity.Installment;
import br.com.compass.msorder.client.entity.Payment;
import br.com.compass.msorder.client.entity.Sku;
import br.com.compass.msorder.client.enums.Sex;
import br.com.compass.msorder.client.enums.State;
import br.com.compass.msorder.entity.Order;
import br.com.compass.msorder.entity.dto.CartDto;
import br.com.compass.msorder.entity.dto.CustomerDto;
import br.com.compass.msorder.entity.dto.OrderDto;
import br.com.compass.msorder.entity.dto.OrderFormDto;
import br.com.compass.msorder.entity.dto.PaymentDto;
import br.com.compass.msorder.enums.Status;
import br.com.compass.msorder.rabbitmq.consumer.entity.PaymentOrderStatus;
import br.com.compass.msorder.repository.OrderRepository;
import br.com.compass.msorder.service.exception.CustomerInactiveException;
import br.com.compass.msorder.service.exception.ObjectNotFoundException;
import br.com.compass.msorder.service.exception.QuantityUnavailableException;

class OrderServiceImpTest {

	private static final long ID = 1L;
	private static final String ID_MONGO = "1";
	private static final LocalDate BIRTHDATE = LocalDate.now();
	private static final LocalDate STARTDATE = LocalDate.now();
	private static final LocalDate ENDDATE = LocalDate.now();
	
	@InjectMocks
	private OrderServiceImp orderService;
	
	@Mock
	private OrderRepository orderRepository;
	
	@Mock
	private CustomerClient customerClient;
	
	@Mock
	private PaymentClient paymentClient;

	@Mock
	private CatalogClient catalogClient;
	
	@Mock
	private RabbitTemplate rabbitTemplate;
	
	private Order order;
	
	private List<Order> orderList;
	
	private Optional<Order> optOrder;
	
	private Customer customer;
	
	private Customer customerNotActive;
	
	private Address address;
	
	private Payment payment;
	
	private Installment installment;
	
	private Sku sku;
	
	private OrderFormDto orderFormDto;
	
	private OrderFormDto orderFormDtoCartLargeQuantity;
	
	private CustomerDto customerDto;
	
	private PaymentDto paymentDto;
	
	private CartDto cartDto;
	
	private CartDto cartDtoLargeQuantity;
	
	private PaymentOrderStatus paymentOrderStatus;

	@BeforeEach
	void setUp() {
		openMocks(this); startOrder();
	}
	
	@Test
	void whenFindAllThenReturnListOfOrderDto() {
		when(customerClient.getCustomer(anyLong())).thenReturn(customer);
		when(customerClient.getAddress(anyLong())).thenReturn(address);
		when(paymentClient.getPayment(anyLong())).thenReturn(payment);
		when(paymentClient.getInstallments(anyLong())).thenReturn(installment);
		when(catalogClient.getSku(anyLong())).thenReturn(sku);
		when(orderRepository.save(any())).thenReturn(order);
		
		OrderDto response = orderService.save(orderFormDto);
	
		assertNotNull(response);
		assertEquals(OrderDto.class, response.getClass());
		assertEquals(1.0, response.getTotal());
		assertEquals(Status.PROCESSING_PAYMENT, response.getStatus());
		assertEquals("Street", response.getAddress().getStreet());
		assertEquals("Complement", response.getAddress().getComplement());
		assertEquals("53000000", response.getAddress().getCep());
		assertEquals("Willams", response.getCustomer().getFirstName());
		assertEquals("Souza", response.getCustomer().getLastName());
		assertEquals(BIRTHDATE, response.getCustomer().getBirthdate());
		assertEquals(1.0, response.getCart().get(0).getPrice());
		assertEquals("Color", response.getCart().get(0).getColor());
		assertEquals(1, response.getCart().get(0).getWidth());
		assertEquals(true, response.getPayment().getActive());
		assertEquals("Type", response.getPayment().getType());
		assertEquals("Brand", response.getInstallment().getBrand());
	}
	
	@Test
	void whenFindAllThenReturnCustomerInactiveException() {
		when(customerClient.getCustomer(anyLong())).thenReturn(customerNotActive);
		when(customerClient.getAddress(anyLong())).thenReturn(address);
		when(paymentClient.getPayment(anyLong())).thenReturn(payment);
		when(paymentClient.getInstallments(anyLong())).thenReturn(installment);
		when(catalogClient.getSku(anyLong())).thenReturn(sku);
		when(orderRepository.save(any())).thenReturn(order);
		
		try {
			orderService.save(orderFormDto);
		} catch (Exception e) {
			assertEquals(CustomerInactiveException.class, e.getClass());
			assertEquals("Customer is not active.", e.getMessage());
		}
	}
	
	@Test
	void whenFindAllThenReturnCustomerQuantityUnavailableException() {
		when(customerClient.getCustomer(anyLong())).thenReturn(customer);
		when(customerClient.getAddress(anyLong())).thenReturn(address);
		when(paymentClient.getPayment(anyLong())).thenReturn(payment);
		when(paymentClient.getInstallments(anyLong())).thenReturn(installment);
		when(catalogClient.getSku(anyLong())).thenReturn(sku);
		when(orderRepository.save(any())).thenReturn(order);
		
		try {
			orderService.save(orderFormDtoCartLargeQuantity);
		} catch (Exception e) {
			assertEquals(QuantityUnavailableException.class, e.getClass());
			assertEquals("Quantity unavailable Sku ID: " + ID, e.getMessage());
		}
	}
	
	@Test
	void whenFindByCustomerIdThenReturnListOfOrderDto() {
		when(orderRepository.findByCustomerId(anyLong())).thenReturn(orderList);
		
		List<OrderDto> response = orderService.findByCustomerId(ID, STARTDATE, ENDDATE, Status.PROCESSING_PAYMENT);

		assertNotNull(response);
		assertEquals(OrderDto.class, response.get(0).getClass());
		assertEquals(1.0, response.get(0).getTotal());
		assertEquals(Status.PROCESSING_PAYMENT, response.get(0).getStatus());
		assertEquals("Street", response.get(0).getAddress().getStreet());
		assertEquals("Complement", response.get(0).getAddress().getComplement());
		assertEquals("53000000", response.get(0).getAddress().getCep());
		assertEquals("Willams", response.get(0).getCustomer().getFirstName());
		assertEquals("Souza", response.get(0).getCustomer().getLastName());
		assertEquals(BIRTHDATE, response.get(0).getCustomer().getBirthdate());
		assertEquals(1.0, response.get(0).getCart().get(0).getPrice());
		assertEquals("Color", response.get(0).getCart().get(0).getColor());
		assertEquals(1, response.get(0).getCart().get(0).getWidth());
		assertEquals(true, response.get(0).getPayment().getActive());
		assertEquals("Type", response.get(0).getPayment().getType());
		assertEquals("Brand", response.get(0).getInstallment().getBrand());
			
	}
	
	@Test
	void whenUpdateStatusPaymentThenReturnAnOrderDto() {
		when(orderRepository.findById(anyString())).thenReturn(optOrder);
		when(orderRepository.save(any())).thenReturn(order);
		
		OrderDto response = orderService.updateStatusPayment(paymentOrderStatus);
	
		assertNotNull(response);
		assertEquals(OrderDto.class, response.getClass());
		assertEquals(1.0, response.getTotal());
		assertEquals(Status.PAYMENT_SUCCESSFUL, response.getStatus());
		assertEquals("Street", response.getAddress().getStreet());
		assertEquals("Complement", response.getAddress().getComplement());
		assertEquals("53000000", response.getAddress().getCep());
		assertEquals("Willams", response.getCustomer().getFirstName());
		assertEquals("Souza", response.getCustomer().getLastName());
		assertEquals(BIRTHDATE, response.getCustomer().getBirthdate());
		assertEquals(1.0, response.getCart().get(0).getPrice());
		assertEquals("Color", response.getCart().get(0).getColor());
		assertEquals(1, response.getCart().get(0).getWidth());
		assertEquals(true, response.getPayment().getActive());
		assertEquals("Type", response.getPayment().getType());
		assertEquals("Brand", response.getInstallment().getBrand());
	}
	
	@Test
	void whenUpdateStatusPaymentThenReturnObjectNotFoundException() {
		when(orderRepository.findById(anyString())).thenThrow(new ObjectNotFoundException("Order ID: " + ID_MONGO + " not found."));
		when(orderRepository.save(any())).thenReturn(order);
		try {
			orderService.updateStatusPayment(paymentOrderStatus);
		}catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Order ID: " + ID_MONGO + " not found.", e.getMessage());
		}
	}
	
	private void startOrder() {
		
		address = new Address();
		address.setId(ID);
		address.setStreet("Street");
		address.setNumber("1");
		address.setComplement("Complement");
		address.setDistrict("District");
		address.setCity("City");
		address.setState(State.ACRE);
		address.setCep("53000000");
		
		customer = new Customer();
		customer.setId(ID);
		customer.setFirstName("Willams");
		customer.setLastName("Souza");
		customer.setSex(Sex.MASCULINO);
		customer.setEmail("email@email.com");
		customer.setBirthdate(BIRTHDATE);
		customer.setActive(true);
		
		customerNotActive = new Customer();
		customerNotActive.setId(ID);
		customerNotActive.setFirstName("Willams");
		customerNotActive.setLastName("Souza");
		customerNotActive.setSex(Sex.MASCULINO);
		customerNotActive.setEmail("email@email.com");
		customerNotActive.setBirthdate(BIRTHDATE);
		customerNotActive.setActive(false);
		
		sku = new Sku();
		sku.setId(ID);
		sku.setPrice(1.0);
		sku.setQuantity(1);
		sku.setHeight(1);
		sku.setWidth(1);
		sku.setSize("Size");
		sku.setColor("Color");
		
		payment = new Payment();
		payment.setId(ID);
		payment.setActive(true);
		payment.setInstallments(true);
		payment.setType("Type");
		
		installment = new Installment();
		installment.setAmount(1);
		installment.setBrand("Brand");
		installment.setPayment(payment);
		
		order = new Order();
		order.setId(ID_MONGO);
		order.setDate(BIRTHDATE);
		order.setStatus(Status.PROCESSING_PAYMENT);
		order.setTotal(1.0);
		order.setCart(List.of(sku));
		order.setPayment(payment);
		order.setInstallment(installment);
		order.setCustomer(customer);
		order.setAddress(address);
		
		optOrder = Optional.of(order);
		
		orderList = List.of(order);
		
		customerDto = new CustomerDto();
		customerDto.setAddressId(ID);
		customerDto.setId(ID);
		
		paymentDto = new PaymentDto();
		paymentDto.setId(ID);
		paymentDto.setInstallments(1);
		
		cartDto = new CartDto();
		cartDto.setSkuId(ID);
		cartDto.setQuantity(1);
		
		cartDtoLargeQuantity = new CartDto();
		cartDtoLargeQuantity.setSkuId(ID);
		cartDtoLargeQuantity.setQuantity(100);
		
		orderFormDto = new OrderFormDto();
		orderFormDto.setCustomer(customerDto);
		orderFormDto.setCart(List.of(cartDto));
		orderFormDto.setPayment(paymentDto);
		
		orderFormDtoCartLargeQuantity = new OrderFormDto();
		orderFormDtoCartLargeQuantity.setCustomer(customerDto);
		orderFormDtoCartLargeQuantity.setCart(List.of(cartDtoLargeQuantity));
		orderFormDtoCartLargeQuantity.setPayment(paymentDto);
		
		paymentOrderStatus = new PaymentOrderStatus();
		paymentOrderStatus.setOrderId(ID_MONGO);
		paymentOrderStatus.setStatus(Status.PAYMENT_SUCCESSFUL);
		
	}
}