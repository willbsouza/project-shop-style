package br.com.compass.mscustomer.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compass.mscustomer.dto.AddressDto;
import br.com.compass.mscustomer.dto.AddressFormDto;
import br.com.compass.mscustomer.entity.Address;
import br.com.compass.mscustomer.entity.Customer;
import br.com.compass.mscustomer.repository.AddressRepository;
import br.com.compass.mscustomer.repository.CustomerRepository;
import br.com.compass.mscustomer.service.exception.ObjectNotFoundException;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public AddressDto save(@Valid AddressFormDto addressFormDto) {
		Customer customer = customerRepository.findById(addressFormDto.getCustomerId()).orElseThrow(
				() -> new ObjectNotFoundException("Customer Id: " + addressFormDto.getCustomerId() + " not found."));
		
		Address address = new Address();
		address.setStreet(addressFormDto.getStreet());
		address.setNumber(addressFormDto.getNumber());
		address.setComplement(addressFormDto.getComplement());
		address.setDistrict(addressFormDto.getDistrict());
		address.setCity(addressFormDto.getCity());
		address.setState(addressFormDto.getState());
		address.setCep(addressFormDto.getCep());
		address.setCustomer(customer);

		return new AddressDto(addressRepository.save(address));
	}

	public AddressDto update(Long id, @Valid AddressFormDto addressFormDto) {
		Address address = addressRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("Address Id: " + id + " not found."));
		address.setStreet(addressFormDto.getStreet());
		address.setNumber(addressFormDto.getNumber());
		address.setComplement(addressFormDto.getComplement());
		address.setDistrict(addressFormDto.getDistrict());
		address.setCity(addressFormDto.getCity());
		address.setState(addressFormDto.getState());
		address.setCep(addressFormDto.getCep());
		
		return new AddressDto(addressRepository.save(address));
	}

	public void deleteById(Long id) {
		addressRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("Address Id: " + id + " not found."));
		addressRepository.deleteById(id);
	}
}
