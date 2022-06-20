package br.com.compass.mscatalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compass.mscatalog.dto.ProductDto;
import br.com.compass.mscatalog.dto.ProductFormDto;
import br.com.compass.mscatalog.service.ProductService;

@RestController
@RequestMapping("/v1/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<ProductDto> save(@RequestBody ProductFormDto productFormDto){
		return new ResponseEntity<ProductDto>(productService.save(productFormDto), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<ProductDto>> findAll(){
		return new ResponseEntity<List<ProductDto>>(productService.findAll(), HttpStatus.OK);
	}
 
}
