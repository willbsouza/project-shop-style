package br.com.compass.mscustomer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compass.mscustomer.dto.AddressDto;
import br.com.compass.mscustomer.dto.AddressFormDto;
import br.com.compass.mscustomer.service.AddressService;

@RestController
@RequestMapping("/v1/addresses")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/{id}")
	public ResponseEntity<AddressDto> findById(@PathVariable Long id){
		return new ResponseEntity<AddressDto>(addressService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<AddressDto> save(@RequestBody @Valid AddressFormDto addressFormDto){
		return new ResponseEntity<AddressDto>(addressService.save(addressFormDto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AddressDto> update(@PathVariable Long id, @RequestBody @Valid AddressFormDto addressFormDto){
		return new ResponseEntity<AddressDto>(addressService.update(id, addressFormDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<AddressDto> deleteById(@PathVariable Long id){
		addressService.deleteById(id); 
		return new ResponseEntity<AddressDto>(HttpStatus.NO_CONTENT);
	}
}
