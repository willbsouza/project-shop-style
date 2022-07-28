package br.com.compass.mscatalog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.compass.mscatalog.dto.CategoryDto;
import br.com.compass.mscatalog.dto.CategoryFormDto;
import br.com.compass.mscatalog.dto.ProductDto;
import br.com.compass.mscatalog.entity.Category;
import br.com.compass.mscatalog.entity.Product;
import br.com.compass.mscatalog.repository.CategoryRepository;
import br.com.compass.mscatalog.service.exception.ObjectNotFoundException;

class CategoryServiceImpTest {
	
	private static final long ID = 1L;

	@InjectMocks
	private CategoryServiceImp categoryService;
	
	@Mock
	private CategoryRepository categoryRepository;
	
	private Category category;
	
	private Category categoryWithParentIdNotNull;
	
	private List<Category> categoryList;
	
	private CategoryFormDto categoryFormDto;
	
	private CategoryFormDto categoryFormDtoWithParentIdNotNull;
	
	private Optional<Category> optCategory;
	
	private Optional<Category> optCategoryWithParentIdNotNull;
	
	private Product product;
	
	@BeforeEach
	void setUp() {
		openMocks(this);
		startCategory();
	}
	
	@Test
	void whenSaveWithParentNullThenReturnACategoryDto() {
		when(categoryRepository.save(any())).thenReturn(category);
		
		CategoryDto response = categoryService.save(categoryFormDto);
		
		assertNotNull(response);
		assertEquals(CategoryDto.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(true, response.getActive());
		assertEquals("Category", response.getName());
	}
	
	@Test
	void whenSaveWithParentNotNullThenReturnACategoryDto() {
		when(categoryRepository.findById(anyLong())).thenReturn(optCategoryWithParentIdNotNull);
		when(categoryRepository.save(any())).thenReturn(category);
		
		CategoryDto response = categoryService.save(categoryFormDtoWithParentIdNotNull);
		
		assertNotNull(response);
		assertEquals(CategoryDto.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(true, response.getActive());
		assertEquals("Category", response.getName());
	}

	@Test
	void whenFindAllThenReturnListOfCategory() {
		when(categoryRepository.findAll()).thenReturn(categoryList);
		
		List<CategoryDto> response = categoryService.findAll();
		
		assertNotNull(response);
		assertEquals(CategoryDto.class, response.get(0).getClass());
		assertEquals(1, response.size());
		assertEquals(ID, response.get(0).getId());
		assertEquals(true, response.get(0).getActive());
		assertEquals("Category", response.get(0).getName());
	}
	
	@Test
	void whenfindListProductsByIdThenReturnListOfProductDto() {
		when(categoryRepository.findById(anyLong())).thenReturn(optCategory);
		
		List<ProductDto> response = categoryService.findListProductsById(ID);
		
		assertNotNull(response);
		assertEquals(ProductDto.class, response.get(0).getClass());
		assertEquals(1, response.size());
		assertEquals(ID, response.get(0).getId());
		assertEquals(true, response.get(0).getActive());
		assertEquals("Product", response.get(0).getName());
		assertEquals("Description", response.get(0).getDescription());
		assertEquals("Material", response.get(0).getMaterial());
		assertEquals("Brand", response.get(0).getBrand());
	}
	
	@Test
	void whenfindListProductsByIdThenReturnObjectNotFoundException() {
		when(categoryRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Category ID : " + ID + " not found."));
		
		try {
			categoryService.findListProductsById(ID);
		}catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Category ID : " + ID + " not found.", e.getMessage());
		}
	}
	
	@Test
	void whenUpdateByIdThenReturnObjectNotFoundException() {
		when(categoryRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Category ID : " + ID + " not found."));
		
		try {
			categoryService.update(ID, categoryFormDto);
		}catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Category ID : " + ID + " not found.", e.getMessage());
		}
	}
	
	@Test
	void whenUpdateByIdThenReturnCategoryDto() {
		when(categoryRepository.findById(anyLong())).thenReturn(optCategory);
		when(categoryRepository.save(any())).thenReturn(category);
		
		CategoryDto response = categoryService.update(ID, categoryFormDto);
		
		assertNotNull(response);
		assertEquals(CategoryDto.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(true, response.getActive());
		assertEquals("Category", response.getName());
	}

	@Test
	void whenDeleteByIdThenDeleteWithSuccess() {
		when(categoryRepository.findById(anyLong())).thenReturn(optCategory);
		doNothing().when(categoryRepository).deleteById(anyLong());
		categoryService.deleteById(ID);
		
		verify(categoryRepository, times(1)).deleteById(anyLong());
	}
	
	@Test
	void whenDeleteByIdThenReturnObjectNotFoundException() {
		when(categoryRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Category ID : " + ID + " not found."));
		
		try {
			categoryService.deleteById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Category ID : " + ID + " not found.", e.getMessage());
		}
	}
	
	private void startCategory() {
		
		product = new Product();
		product.setId(ID);
		product.setActive(true);
		product.setName("Product");
		product.setDescription("Description");
		product.setBrand("Brand");
		product.setMaterial("Material");
		product.setCategory(category);
		
		categoryFormDto = new CategoryFormDto();
		categoryFormDto.setActive(true);
		categoryFormDto.setName("Category");
		
		category = new Category(categoryFormDto);
		category.setId(ID);
		category.setProducts(List.of(product));
		
		optCategory = Optional.of(category);
		
		categoryList = List.of(category);
		
		categoryFormDtoWithParentIdNotNull = new CategoryFormDto();
		categoryFormDtoWithParentIdNotNull.setActive(true);
		categoryFormDtoWithParentIdNotNull.setName("Category");
		categoryFormDtoWithParentIdNotNull.setParentId(ID);
		
		categoryWithParentIdNotNull = new Category(categoryFormDtoWithParentIdNotNull);
		categoryWithParentIdNotNull.setId(ID);
		categoryWithParentIdNotNull.setProducts(List.of(product));
		
		optCategoryWithParentIdNotNull = Optional.of(categoryWithParentIdNotNull);
	}
}