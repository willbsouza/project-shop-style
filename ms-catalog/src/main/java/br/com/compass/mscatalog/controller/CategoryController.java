package br.com.compass.mscatalog.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compass.mscatalog.dto.CategoryDto;
import br.com.compass.mscatalog.dto.CategoryFormDto;
import br.com.compass.mscatalog.dto.ProductDto;
import br.com.compass.mscatalog.service.CategoryService;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<CategoryDto>> findAll(){
		return new ResponseEntity<List<CategoryDto>>(categoryService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/products")
	public ResponseEntity<List<ProductDto>> findListProductsById(@PathVariable Long id){
		return new ResponseEntity<List<ProductDto>>(categoryService.findListProductsById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<CategoryDto> save(@RequestBody CategoryFormDto categoryFormDto){
		return new ResponseEntity<CategoryDto>(categoryService.save(categoryFormDto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> update(@PathVariable Long id, @RequestBody CategoryFormDto categoryFormDto){
		return new ResponseEntity<CategoryDto>(categoryService.update(id, categoryFormDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		categoryService.deleteById(id); 
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
