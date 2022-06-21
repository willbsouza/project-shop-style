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
import br.com.compass.mscatalog.service.exception.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private SequenceGeneratorService seqService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<ProductDto> findAll() {
		return productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
	}
	
	public ProductDto save(@Valid ProductFormDto productFormDto) {
		Product product = new Product();
		product.setId(seqService.getSequenceNumber(Product.SEQUENCE_NAME));
		product.setName(productFormDto.getName());
		product.setDescription(productFormDto.getDescription());
		product.setActive(productFormDto.getActive());
		for(Long idCategory : productFormDto.getCategory_ids()) {
			Category findCategory = categoryRepository.findById(idCategory).orElseThrow(
					() -> new ObjectNotFoundException("Category ID: " + idCategory + " not found."));
			if(findCategory.getActive()) {
				findCategory.addProducts(product);
				categoryRepository.save(findCategory);
			}
		}
		return new ProductDto(productRepository.save(product));
	}

	public ProductDto findById(Long id) {
		return new ProductDto( 
				productRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("Product ID: " + id + " not found."))
			);
	}

	public void deleteById(Long id) {
		findById(id);
		productRepository.deleteById(id);
	}

	public ProductDto update(Long id, @Valid ProductFormDto productFormDto) {
		Product product = productRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("Product ID: " + id + " not found."));
		product.setName(productFormDto.getName());
		product.setDescription(productFormDto.getDescription());
		product.setActive(productFormDto.getActive());
		
		for(Long idCategory : productFormDto.getCategory_ids()) {
			Category findCategory = categoryRepository.findById(idCategory).orElseThrow(
					() -> new ObjectNotFoundException("Category ID: " + idCategory + " not found."));
			if(findCategory.getActive()) {			
				findCategory.addProducts(product);
				categoryRepository.save(findCategory);
			}
		}
		return new ProductDto(productRepository.save(product));
	}
}
