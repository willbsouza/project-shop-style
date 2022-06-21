package br.com.compass.mscustomer.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.compass.mscustomer.dto.CustomerDto;
import br.com.compass.mscustomer.dto.CustomerFormDto;
import br.com.compass.mscustomer.dto.CustomerLoginDto;
import br.com.compass.mscustomer.entity.Customer;
import br.com.compass.mscustomer.repository.CustomerRepository;
import br.com.compass.mscustomer.service.exception.LoginException;
import br.com.compass.mscustomer.service.exception.ObjectNotFoundException;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	public CustomerDto findById(Long id) {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
		return new CustomerDto(customer);
	}

	public CustomerDto save(@Valid CustomerFormDto userFormDto) {
		return new CustomerDto(customerRepository.save(new Customer(userFormDto)));
	}

	public CustomerDto updateById(@Valid CustomerFormDto userFormDto, Long id) {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
			customer.setFirstName(userFormDto.getFirstName());
			customer.setLastName(userFormDto.getLastName());
			customer.setSex(userFormDto.getSex());
			customer.setCpf(userFormDto.getCpf());
			customer.setBirthdate(userFormDto.getBirthdate());
			customer.setEmail(userFormDto.getEmail());
			customer.setPassword(userFormDto.getPassword());
			customer.setActive(userFormDto.getActive());
			return new CustomerDto(customer);		
	}

	public CustomerDto login(CustomerLoginDto customerLoginDto) {
		Customer customer = customerRepository.findByEmail(customerLoginDto.getEmail());
		if(customer != null && new BCryptPasswordEncoder().matches(customerLoginDto.getPassword(), customer.getPassword())) {	
			return new CustomerDto(customer);		
		}
		throw new LoginException("Login e/ou senha incorretos!");
	}
}
