package br.com.compass.mscatalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compass.mscatalog.dto.ProductFormDto;
import br.com.compass.mscatalog.entity.Product;
import br.com.compass.mscatalog.service.ProductService;

@RestController
@RequestMapping("/v1/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public String testController() {
		return "Controller works!";
	}
	
	@PostMapping
	public ResponseEntity<Product> save(@RequestBody ProductFormDto productFormDto){
		Product product = new Product(productFormDto);
		return new ResponseEntity<Product>(productService.save(product), HttpStatus.CREATED);
	}

}
