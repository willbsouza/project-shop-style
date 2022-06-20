package br.com.compass.mscatalog.service;

import java.util.List;
import java.util.stream.Collectors;

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
	
	public ProductDto save(ProductFormDto productFormDto) {
		Product product = new Product();
		product.setId(seqService.getSequenceNumber(Product.SEQUENCE_NAME));
		product.setName(productFormDto.getName());
		product.setDescription(productFormDto.getDescription());
		product.setActive(productFormDto.getActive());
		for(Long idCategory : productFormDto.getCategory_ids()) {
			Category findCategory = categoryRepository.findById(idCategory).orElseThrow(
					() -> new ObjectNotFoundException("Category ID: " + idCategory + " not found."));
			if(findCategory.getActive()) {
				product.addCategory(findCategory);
			}
		}
		return new ProductDto(productRepository.save(product));
	}
	
}
