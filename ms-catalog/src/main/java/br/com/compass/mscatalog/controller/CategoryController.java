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

import br.com.compass.mscatalog.dto.CategoryDto;
import br.com.compass.mscatalog.dto.CategoryFormDto;
import br.com.compass.mscatalog.service.CategoryService;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<CategoryDto> save(@RequestBody CategoryFormDto categoryFormDto){
		return new ResponseEntity<CategoryDto>(categoryService.save(categoryFormDto), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoryDto>> findAll(){
		return new ResponseEntity<List<CategoryDto>>(categoryService.findAll(), HttpStatus.OK);
	}
}
