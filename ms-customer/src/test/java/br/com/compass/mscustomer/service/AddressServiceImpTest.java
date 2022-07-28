package br.com.compass.mscustomer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.compass.mscustomer.dto.AddressDto;
import br.com.compass.mscustomer.dto.AddressFormDto;
import br.com.compass.mscustomer.entity.Address;
import br.com.compass.mscustomer.entity.Customer;
import br.com.compass.mscustomer.entity.enums.Sex;
import br.com.compass.mscustomer.entity.enums.State;
import br.com.compass.mscustomer.repository.AddressRepository;
import br.com.compass.mscustomer.repository.CustomerRepository;
import br.com.compass.mscustomer.service.exception.ObjectNotFoundException;

class AddressServiceImpTest {
	
	private static final long ID = 1L;
	private static final LocalDate BIRTHDATE = LocalDate.now();

	@InjectMocks
	private AddressServiceImp addressService;
	
	@Mock
	private AddressRepository addressRepository;
	
	@Mock
	private CustomerRepository customerRepository;
	
	private Address address;
	
	private Optional<Address> optAddress;
	
	private AddressDto response;
	
	private AddressFormDto addressFormDto;
	
	private Customer customer;
	
	private Optional<Customer> optCustomer;
	
	@BeforeEach
	void setUp() {
		openMocks(this); startAddress();
	}

	@Test
	void whenSaveThenReturnAnAddressDto() {
		when(customerRepository.findById(anyLong())).thenReturn(optCustomer);
		when(addressRepository.save(any())).thenReturn(address);
		
		response = addressService.save(addressFormDto);
		
		assertNotNull(response);
		assertEquals(AddressDto.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals("Street", response.getStreet());
		assertEquals("1", response.getNumber());
		assertEquals("Complement", response.getComplement());
		assertEquals("District", response.getDistrict());
		assertEquals("City", response.getCity());
		assertEquals(State.ACRE, response.getState());	
		assertEquals("53000000", response.getCep());
	}
	
	@Test
	void whenSaveThenReturnObjectNotFoundException() {
		when(addressRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Customer Id: " + ID + " not found."));
		
		try {
			addressService.save(addressFormDto);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Customer Id: " + ID + " not found.", e.getMessage());
		}
	}
	
	@Test
	void whenUpdateThenReturnAnAddressDto() {
		when(addressRepository.findById(anyLong())).thenReturn(optAddress);
		when(addressRepository.save(any())).thenReturn(address);
		
		response = addressService.update(ID, addressFormDto);
		
		assertNotNull(response);
		assertEquals(AddressDto.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals("Street", response.getStreet());
		assertEquals("1", response.getNumber());
		assertEquals("Complement", response.getComplement());
		assertEquals("District", response.getDistrict());
		assertEquals("City", response.getCity());
		assertEquals(State.ACRE, response.getState());	
		assertEquals("53000000", response.getCep());
	}
	
	@Test
	void whenUpdateThenReturnObjectNotFoundException() {
		when(addressRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Address Id: " + ID + " not found."));
		
		try {
			addressService.update(ID, addressFormDto);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Address Id: " + ID + " not found.", e.getMessage());
		}
	}
	
	@Test
	void whenDeleteByIdThenDeleteWithSuccess() {
		when(addressRepository.findById(anyLong())).thenReturn(optAddress);
		doNothing().when(addressRepository).deleteById(anyLong());
		addressService.deleteById(ID);
		
		verify(addressRepository, times(1)).deleteById(anyLong());
	}
	
	@Test
	void whenDeleteByIdThenReturnObjectNotFoundException() {
		when(addressRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Address Id: " + ID + " not found."));
		
		try {
			addressService.deleteById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Address Id: " + ID + " not found.", e.getMessage());
		}
	}
	
	@Test
	void whenFindByIdThenReturnAnAddressDto() {
		when(addressRepository.findById(anyLong())).thenReturn(optAddress);
		
		response = addressService.findById(ID);
		
		assertNotNull(response);
		assertEquals(AddressDto.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals("Street", response.getStreet());
		assertEquals("1", response.getNumber());
		assertEquals("Complement", response.getComplement());
		assertEquals("District", response.getDistrict());
		assertEquals("City", response.getCity());
		assertEquals(State.ACRE, response.getState());	
		assertEquals("53000000", response.getCep());
	}
	
	@Test
	void whenFindByIdThenReturnObjectNotFoundException() {
		when(addressRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Address Id: " + ID + " not found."));
		
		try {
			addressService.findById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Address Id: " + ID + " not found.", e.getMessage());
		}
	}
	
	private void startAddress() {
		
		customer = new Customer();
		
		customer.setId(ID);
		customer.setFirstName("Willams");
		customer.setLastName("Souza");
		customer.setSex(Sex.MASCULINO);
		customer.setCpf("60456467033");
		customer.setEmail("email@email.com");
		customer.setPassword("12345678");
		customer.setBirthdate(BIRTHDATE);
		customer.setActive(true);
		
		optCustomer = Optional.of(customer);
		
		addressFormDto = new AddressFormDto();
		addressFormDto.setStreet("Street");
		addressFormDto.setNumber("1");
		addressFormDto.setComplement("Complement");
		addressFormDto.setDistrict("District");
		addressFormDto.setCity("City");
		addressFormDto.setState(State.ACRE);
		addressFormDto.setCep("53000000");
		addressFormDto.setCustomerId(ID);
		
		address = new Address(addressFormDto, customer);
		address.setId(ID);
		
		optAddress = Optional.of(address);
	}
}