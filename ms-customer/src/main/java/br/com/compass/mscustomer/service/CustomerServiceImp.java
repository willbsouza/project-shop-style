package br.com.compass.mscustomer.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.compass.mscustomer.dto.CustomerChangePasswordDto;
import br.com.compass.mscustomer.dto.CustomerDto;
import br.com.compass.mscustomer.dto.CustomerFormDto;
import br.com.compass.mscustomer.dto.CustomerFormUpdateDto;
import br.com.compass.mscustomer.dto.CustomerLoginDto;
import br.com.compass.mscustomer.entity.Customer;
import br.com.compass.mscustomer.repository.CustomerRepository;
import br.com.compass.mscustomer.service.exception.ChangePasswordException;
import br.com.compass.mscustomer.service.exception.LoginException;
import br.com.compass.mscustomer.service.exception.ObjectNotFoundException;

@Service
public class CustomerServiceImp implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public CustomerDto findById(Long id) {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " not found"));
		return new CustomerDto(customer);
	}

	@Override
	public CustomerDto save(@Valid CustomerFormDto customerLoginDto) {
		return new CustomerDto(customerRepository.save(new Customer(customerLoginDto)));
	}
	
	@Override
	public CustomerDto updateById(@Valid CustomerFormUpdateDto customerFormUpdateDto, Long id) {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " not found"));
			customer.setFirstName(customerFormUpdateDto.getFirstName());
			customer.setLastName(customerFormUpdateDto.getLastName());
			customer.setSex(customerFormUpdateDto.getSex());
			customer.setCpf(customerFormUpdateDto.getCpf());
			customer.setBirthdate(customerFormUpdateDto.getBirthdate());
			customer.setActive(customerFormUpdateDto.getActive());
			return new CustomerDto(customer);		
	}
	
	@Override
	public CustomerDto login(CustomerLoginDto customerLoginDto) {
		Customer customer = customerRepository.findByEmail(customerLoginDto.getEmail());
		if(customer != null && new BCryptPasswordEncoder().matches(customerLoginDto.getPassword(), customer.getPassword())) {	
			return new CustomerDto(customer);		
		}
		throw new LoginException("Incorrect email and/or password!");
	}
	
	@Override
	public Customer findByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

	@Override
	public CustomerDto changePassword(@Valid CustomerChangePasswordDto passwordDto, Long id) {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " not found."));
		if(verificationPassword(customer, passwordDto)) {	
			customer.setPassword(passwordDto.getNewPassword());
			return new CustomerDto(customer);		
		}
		throw new ChangePasswordException("Incorrect data. Check the email information, cpf and the new password and password confirmation fields must be the same.");
	}
	
	private Boolean verificationPassword(Customer customer, CustomerChangePasswordDto passwordDto) {
		if(new BCryptPasswordEncoder().matches(passwordDto.getOldPassword(), customer.getPassword())
				&& passwordDto.getCpf().equals(customer.getCpf())
				&& passwordDto.getEmail().equals(customer.getEmail())
				&& passwordDto.getNewPassword().equals(passwordDto.getNewPasswordConfirmation())) {
			return true;
		}
		return false;
	}
}
