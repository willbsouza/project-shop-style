package br.com.compass.mscatalog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compass.mscatalog.dto.CategoryDto;
import br.com.compass.mscatalog.dto.CategoryFormDto;
import br.com.compass.mscatalog.entity.Category;
import br.com.compass.mscatalog.repository.CategoryRepository;

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
}
