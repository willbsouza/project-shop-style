package br.com.compass.mscustomer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.MockitoAnnotations.openMocks;

import br.com.compass.mscustomer.dto.CustomerChangePasswordDto;
import br.com.compass.mscustomer.dto.CustomerDto;
import br.com.compass.mscustomer.dto.CustomerFormDto;
import br.com.compass.mscustomer.dto.CustomerFormUpdateDto;
import br.com.compass.mscustomer.dto.CustomerLoginDto;
import br.com.compass.mscustomer.entity.Customer;
import br.com.compass.mscustomer.entity.enums.Sex;
import br.com.compass.mscustomer.repository.CustomerRepository;
import br.com.compass.mscustomer.service.exception.ChangePasswordException;
import br.com.compass.mscustomer.service.exception.LoginException;
import br.com.compass.mscustomer.service.exception.ObjectNotFoundException;

class CustomerServiceImpTest {
	
	private static final long ID = 1L;
	private static final LocalDate BIRTHDATE = LocalDate.now();

	@InjectMocks
	private CustomerServiceImp customerService;
	
	@Mock
	private CustomerRepository customerRepository;
	
	private Customer customer;
	
	private Optional<Customer> optCustomer;
	
	private CustomerDto response;
	
	private CustomerLoginDto customerLoginDto;
	
	private CustomerChangePasswordDto customerChangePasswordDto;
	
	private CustomerFormDto customerFormDto;
	
	private CustomerFormUpdateDto customerFormUpdateDto;
	
	@BeforeEach
	void setUp() {
		openMocks(this);
		startCustomer();
	}
	
	@Test
	void whenFindByIdThenReturnACustomerDto() {
		when(customerRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("ID: " + ID + " not found"));
		
		try {
			customerService.findById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("ID: " + ID + " not found", e.getMessage());
		}	
	}
	
	@Test
	void whenFindByIdThenReturnObjectNotFoundException() {
		when(customerRepository.findById(anyLong())).thenReturn(optCustomer);
		
		response = customerService.findById(ID);
		
		assertNotNull(response);
		assertEquals(CustomerDto.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals("Willams", response.getFirstName());
		assertEquals("Souza", response.getLastName());
		assertEquals(Sex.MASCULINO, response.getSex());
		assertEquals(BIRTHDATE, response.getBirthdate());
		assertEquals("email@email.com", response.getEmail());
		assertEquals(true, response.getActive());		
	}
	
	@Test
	void whenSaveThenReturnACustomerDto() {
		when(customerRepository.save(any())).thenReturn(customer);
		
		response = customerService.save(customerFormDto);
		
		assertNotNull(response);
		assertEquals(CustomerDto.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals("Willams", response.getFirstName());
		assertEquals("Souza", response.getLastName());
		assertEquals(Sex.MASCULINO, response.getSex());
		assertEquals(BIRTHDATE, response.getBirthdate());
		assertEquals("email@email.com", response.getEmail());
		assertEquals(true, response.getActive());		
	}
	
	@Test
	void whenUpdateByIdThenReturnACustomerDto() {
		when(customerRepository.findById(anyLong())).thenReturn(optCustomer);
		
		response = customerService.updateById(customerFormUpdateDto, ID);
		
		assertNotNull(response);
		assertEquals(CustomerDto.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals("Willams", response.getFirstName());
		assertEquals("Souza", response.getLastName());
		assertEquals(Sex.MASCULINO, response.getSex());
		assertEquals(BIRTHDATE, response.getBirthdate());
		assertEquals("email@email.com", response.getEmail());
		assertEquals(true, response.getActive());		
	}
	
	@Test
	void whenUpdateByIdThenReturnObjectNotFoundException() {
		when(customerRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("ID: " + ID + " not found"));
		
		try {
			customerService.findById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("ID: " + ID + " not found", e.getMessage());
		}		
	}
	
	@Test
	void whenLoginThenReturnACustomerDto() {
		when(customerRepository.findByEmail(anyString())).thenReturn(customer);
		
		response = customerService.login(customerLoginDto);
		
		assertNotNull(response);
		assertEquals(CustomerDto.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals("Willams", response.getFirstName());
		assertEquals("Souza", response.getLastName());
		assertEquals(Sex.MASCULINO, response.getSex());
		assertEquals(BIRTHDATE, response.getBirthdate());
		assertEquals("email@email.com", response.getEmail());
		assertEquals(true, response.getActive());	
	}
	
	@Test
	void whenLoginThenReturnLoginException() {
		when(customerRepository.findByEmail(anyString())).thenThrow(new LoginException("Incorrect email and/or password!"));
		
		try {
			customerService.login(customerLoginDto);
		} catch (Exception e) {
			assertEquals(LoginException.class, e.getClass());
			assertEquals("Incorrect email and/or password!", e.getMessage());
		}		
	}
	
	@Test
	void whenfindByEmailThenReturnACustomer() {
		when(customerRepository.findByEmail(anyString())).thenReturn(customer);
		
		Customer response = customerService.findByEmail("email@email.com");
		
		assertNotNull(response);
		assertEquals(Customer.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals("Willams", response.getFirstName());
		assertEquals("Souza", response.getLastName());
		assertEquals(Sex.MASCULINO, response.getSex());
		assertEquals(BIRTHDATE, response.getBirthdate());
		assertEquals("email@email.com", response.getEmail());
		assertEquals(true, response.getActive());	
	}
	
	@Test
	void whenVerificationPasswordThenReturnACustomerDto() {
		when(customerRepository.findById(anyLong())).thenReturn(optCustomer);
		
		response = customerService.changePassword(customerChangePasswordDto, ID);
		
		assertNotNull(response);
		assertEquals(CustomerDto.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals("Willams", response.getFirstName());
		assertEquals("Souza", response.getLastName());
		assertEquals(Sex.MASCULINO, response.getSex());
		assertEquals(BIRTHDATE, response.getBirthdate());
		assertEquals("email@email.com", response.getEmail());
		assertEquals(true, response.getActive());	
				
	}
	
	@Test
	void whenVerificationPasswordThenReturnObjectNotFoundException() {
		when(customerRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("ID: " + ID + " not found."));
		
		try {
			customerService.changePassword(customerChangePasswordDto, ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("ID: " + ID + " not found.", e.getMessage());
		}		
	}
	
	@Test
	void whenVerificationPasswordThenReturnChangePasswordException() {
		when(customerRepository.findById(anyLong())).thenThrow(new ChangePasswordException("Incorrect data. Check the email information, cpf and the new password and password confirmation fields must be the same."));
		
		try {
			customerService.changePassword(customerChangePasswordDto, ID);
		} catch (Exception e) {
			assertEquals(ChangePasswordException.class, e.getClass());
			assertEquals("Incorrect data. Check the email information, cpf and the new password and password confirmation fields must be the same.", e.getMessage());
		}		
	}
	
	private void startCustomer() {
		
		customerFormDto = new CustomerFormDto();
		customerFormDto.setFirstName("Willams");
		customerFormDto.setLastName("Souza");
		customerFormDto.setSex(Sex.MASCULINO);
		customerFormDto.setCpf("60456467033");
		customerFormDto.setEmail("email@email.com");
		customerFormDto.setPassword("12345678");
		customerFormDto.setBirthdate(BIRTHDATE);
		customerFormDto.setActive(true);
		
		customerFormUpdateDto = new CustomerFormUpdateDto();
		customerFormUpdateDto.setFirstName("Willams");
		customerFormUpdateDto.setLastName("Souza");
		customerFormUpdateDto.setSex(Sex.MASCULINO);
		customerFormUpdateDto.setCpf("60456467033");
		customerFormUpdateDto.setBirthdate(BIRTHDATE);
		customerFormUpdateDto.setActive(true);
		
		customerLoginDto = new CustomerLoginDto();
		customerLoginDto.setEmail("email@email.com");
		customerLoginDto.setPassword("12345678");
		
		customerChangePasswordDto = new CustomerChangePasswordDto();
		customerChangePasswordDto.setEmail("email@email.com");
		customerChangePasswordDto.setCpf("60456467033");
		customerChangePasswordDto.setOldPassword("12345678");
		customerChangePasswordDto.setNewPassword("12345678");
		customerChangePasswordDto.setNewPasswordConfirmation("12345678");
		
		customer = new Customer(customerFormDto);
		customer.setId(ID);
		
		optCustomer = Optional.of(customer);
	}
}