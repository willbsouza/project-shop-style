package br.com.compass.mscatalog.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compass.mscatalog.dto.ProductDto;
import br.com.compass.mscatalog.dto.ProductFormDto;
import br.com.compass.mscatalog.entity.Category;
import br.com.compass.mscatalog.entity.Product;
import br.com.compass.mscatalog.repository.CategoryRepository;
import br.com.compass.mscatalog.repository.ProductRepository;
import br.com.compass.mscatalog.service.exception.CategoryNotValidException;
import br.com.compass.mscatalog.service.exception.ObjectNotFoundException;

@Service
public class ProductServiceImp implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<ProductDto> findAll() {
		return productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
	}
	
	public ProductDto findById(Long id) {
		return new ProductDto(productRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Product ID: " + id + " not found.")));
	}
	
	public ProductDto save(@Valid ProductFormDto productFormDto) {
		
		Category category = categoryRepository.findById(productFormDto.getCategoryId()).orElseThrow(
				() -> new ObjectNotFoundException("Category ID: " + productFormDto.getCategoryId() + " not found."));
		if(category.getActive() && category.getChildren().isEmpty()) {
			return new ProductDto(productRepository.save(new Product(productFormDto, category)));
		} else {
			throw new CategoryNotValidException("It is not possible to add a product to this category.");
		}
	}
	
	public ProductDto update(Long id, @Valid ProductFormDto productFormDto) {
		
		Product product = productRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("Product ID: " + id + " not found."));
		Category category = categoryRepository.findById(productFormDto.getCategoryId()).orElseThrow(
				() -> new ObjectNotFoundException("Category ID: " + productFormDto.getCategoryId() + " not found."));
		product.setName(productFormDto.getName());
		product.setDescription(productFormDto.getDescription());
		product.setActive(productFormDto.getActive());
		product.setBrand(productFormDto.getBrand());
		product.setMaterial(productFormDto.getMaterial());
		
		if(category.getActive() && category.getChildren().isEmpty()) {
			product.setCategory(category);
			return new ProductDto(productRepository.save(product));
		} else {
			throw new CategoryNotValidException("It is not possible to add a product to this category.");
		}
	}

	public void deleteById(Long id) {
		findById(id);
		productRepository.deleteById(id);
	}
}

