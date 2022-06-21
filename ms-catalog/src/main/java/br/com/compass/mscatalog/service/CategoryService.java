package br.com.compass.mscatalog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compass.mscatalog.dto.CategoryDto;
import br.com.compass.mscatalog.dto.CategoryFormDto;
import br.com.compass.mscatalog.dto.ProductDto;
import br.com.compass.mscatalog.entity.Category;
import br.com.compass.mscatalog.repository.CategoryRepository;
import br.com.compass.mscatalog.service.exception.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private SequenceGeneratorService seqService;

	public CategoryDto save(CategoryFormDto categoryFormDto) {
		Category category = new Category();
		category.setId(seqService.getSequenceNumber(Category.SEQUENCE_NAME));
		category.setName(categoryFormDto.getName());
		category.setActive(categoryFormDto.getActive());
		return new CategoryDto(categoryRepository.save(category));
	}

	public List<CategoryDto> findAll() {
		return categoryRepository.findAll().stream().map(CategoryDto::new).collect(Collectors.toList());
	}

	public List<ProductDto> findListProductsById(Long id) {
		Category category = categoryRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("Category ID : " + id + " not found."));	
		return category.getProducts().stream().map(ProductDto::new).collect(Collectors.toList());
	}

	public CategoryDto update(Long id, CategoryFormDto categoryFormDto) {
		Category category = categoryRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("Category ID : " + id + " not found."));
		category.setName(categoryFormDto.getName());
		category.setActive(categoryFormDto.getActive());
		return new CategoryDto(categoryRepository.save(category));
	}

	public void deleteById(Long id) {
		categoryRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("Category ID : " + id + " not found."));
		categoryRepository.deleteById(id);	
	}
}
