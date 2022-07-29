package br.com.compass.msbffshop.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.compass.msbffshop.client.CatalogClient;
import br.com.compass.msbffshop.client.CustomerClient;
import br.com.compass.msbffshop.client.OrderClient;
import br.com.compass.msbffshop.client.PaymentClient;
import br.com.compass.msbffshop.client.catalog.dto.CategoryDto;
import br.com.compass.msbffshop.client.catalog.dto.ProductDto;
import br.com.compass.msbffshop.client.customer.dto.AddressDto;
import br.com.compass.msbffshop.client.customer.dto.AddressFormDto;
import br.com.compass.msbffshop.client.customer.dto.CustomerChangePasswordDto;
import br.com.compass.msbffshop.client.customer.dto.CustomerDto;
import br.com.compass.msbffshop.client.customer.dto.CustomerFormDto;
import br.com.compass.msbffshop.client.customer.dto.CustomerLoginDto;
import br.com.compass.msbffshop.client.enums.Status;
import br.com.compass.msbffshop.client.order.dto.OrderDto;
import br.com.compass.msbffshop.client.order.dto.OrderFormDto;
import br.com.compass.msbffshop.client.payment.dto.PaymentDto;

@RestController
public class BffShopController {
	
	@Autowired
	private CustomerClient customerClient;
	
	@Autowired
	private CatalogClient catalogClient;
	
	@Autowired
	private PaymentClient paymentClient;
	
	@Autowired
	private OrderClient orderClient;
	
	@PostMapping("/v1/login")
	public ResponseEntity<CustomerDto> loginCustomer(@RequestBody CustomerLoginDto customerFormDto) {
		return new ResponseEntity<CustomerDto>(customerClient.loginCustomer(customerFormDto), HttpStatus.ACCEPTED);
	}

	@GetMapping("/bffshop/v1/customers/{id}")
	public ResponseEntity<CustomerDto> findCustomerById(@PathVariable Long id) {
		return new ResponseEntity<CustomerDto>(customerClient.findCustomerById(id), HttpStatus.OK);
	}
	
	@PostMapping("/bffshop/v1/customers")
	public ResponseEntity<CustomerDto> saveCustomer(@RequestBody CustomerFormDto customerFormDto) {
		return new ResponseEntity<CustomerDto>(customerClient.saveCustomer(customerFormDto), HttpStatus.CREATED);
	}
	
	@PutMapping("/bffshop/v1/customers/{id}")
	public ResponseEntity<CustomerDto> updateCustomerById(@RequestBody CustomerFormDto customerFormDto, @PathVariable Long id) {
		return new ResponseEntity<CustomerDto>(customerClient.updateCustomerById(id, customerFormDto), HttpStatus.OK);
	}
	
	@PutMapping("/bffshop/v1/customers/{id}/password")
	public ResponseEntity<CustomerDto> changePassword(@RequestBody CustomerChangePasswordDto passwordDto, @PathVariable Long id) {
		return new ResponseEntity<CustomerDto>(customerClient.changePasswordCustomer(passwordDto, id), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/bffshop/v1/addresses")
	public ResponseEntity<AddressDto> saveAddress(@RequestBody AddressFormDto addressFormDto){
		return new ResponseEntity<AddressDto>(customerClient.saveAddress(addressFormDto), HttpStatus.CREATED);
	}
	
	@PutMapping("/bffshop/v1/addresses/{id}")
	public ResponseEntity<AddressDto> updateAddressById(@PathVariable Long id, @RequestBody AddressFormDto addressFormDto){
		return new ResponseEntity<AddressDto>(customerClient.updateAddressById(id, addressFormDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/bffshop/v1/addresses/{id}")
	public ResponseEntity<Void> deleteAddressById(@PathVariable Long id){
		customerClient.deleteAddressById(id); 
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/bffshop/v1/products")
	public ResponseEntity<List<ProductDto>> findAllProducts() {
		return new ResponseEntity<List<ProductDto>>(catalogClient.findAllProducts(), HttpStatus.OK);
	}
	
	@GetMapping("/bffshop/v1/products/{id}")
	public ResponseEntity<ProductDto> findProductById(@PathVariable Long id) {
		return new ResponseEntity<ProductDto>(catalogClient.findProductById(id), HttpStatus.OK);
	}
	
	@GetMapping("/bffshop/v1/categories")
	public ResponseEntity<List<CategoryDto>> findAllCategories() {
		return new ResponseEntity<List<CategoryDto>>(catalogClient.findAllCategories(), HttpStatus.OK);
	}
	
	@GetMapping("/bffshop/v1/categories/{id}/products")
	public ResponseEntity<List<ProductDto>> findListProductsByIdCategory(@PathVariable Long id) {
		return new ResponseEntity<List<ProductDto>>(catalogClient.findListProductsByIdCategory(id), HttpStatus.OK);
	}
	
	@GetMapping("/bffshop/v1/payments")
	public ResponseEntity<List<PaymentDto>> findAllPayments() {
		return new ResponseEntity<List<PaymentDto>>(paymentClient.findAllPayments(), HttpStatus.OK);
	}
	
	@PostMapping("/bffshop/v1/orders")
	public ResponseEntity<OrderDto> saveOrder(@RequestBody OrderFormDto orderFormDto){
		return new ResponseEntity<OrderDto>(orderClient.saveOrder(orderFormDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/bffshop/v1/orders/customers/{customerId}")
	public ResponseEntity<List<OrderDto>> findOrdersByCustomerId(@PathVariable Long customerId, @RequestParam(required = false) LocalDate startDate, 
			@RequestParam(required = false) LocalDate endDate, @RequestParam(required = false) Status status) {
		return new ResponseEntity<List<OrderDto>>(orderClient.findOrdersByCustomerId(customerId, startDate, endDate, status), HttpStatus.OK);
	}
}
