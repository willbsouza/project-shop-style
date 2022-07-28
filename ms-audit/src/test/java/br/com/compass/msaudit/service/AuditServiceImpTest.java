package br.com.compass.msaudit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import br.com.compass.msaudit.dto.OrderDto;
import br.com.compass.msaudit.entity.Address;
import br.com.compass.msaudit.entity.Customer;
import br.com.compass.msaudit.entity.Installment;
import br.com.compass.msaudit.entity.Order;
import br.com.compass.msaudit.entity.Payment;
import br.com.compass.msaudit.entity.Sku;
import br.com.compass.msaudit.enums.Sex;
import br.com.compass.msaudit.enums.State;
import br.com.compass.msaudit.enums.Status;
import br.com.compass.msaudit.repository.AuditRepository;
import br.com.compass.msaudit.service.exception.ObjectNotFoundException;

class AuditServiceImpTest {
	
	private static final long ID = 1L;
	private static final String ID_MONGO = "1";
	private static final LocalDate BIRTHDATE = LocalDate.now();
	
	@InjectMocks
	private AuditServiceImp auditService;
	
	@Mock
	private AuditRepository auditRepository;
	
	private Order order;
	
	private Optional<Order> optOrder;
	
	private Customer customer;
	
	private Address address;
	
	private Payment payment;
	
	private Installment installment;
	
	private Sku sku;

	@BeforeEach
	void setUp() {
		openMocks(this); startOrder();
	}

	@Test
	void whenSaveThenReturnAnOrderDto() {
		when(auditRepository.save(order)).thenReturn(order);
		
		OrderDto response = auditService.save(order);
		
		assertNotNull(response);
		assertEquals(OrderDto.class, response.getClass());
		assertEquals(ID_MONGO, response.getId());
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
	void whenFindByIdThenReturnAnOrderDto() {
		when(auditRepository.findById(anyString())).thenReturn(optOrder);
		
		OrderDto response = auditService.findById(ID_MONGO);
		
		assertNotNull(response);
		assertEquals(OrderDto.class, response.getClass());
		assertEquals(ID_MONGO, response.getId());
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
		assertEquals(1, response.getInstallment().getAmount());
		
	}
	
	@Test
	void whenFindByIdThenReturnObjectNotFoundException() {
		when(auditRepository.findById(anyString())).thenThrow(new ObjectNotFoundException("Order ID: " + ID_MONGO + " not found."));
		try {
			auditService.findById(ID_MONGO);
		} catch (Exception e) {
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
	}
}