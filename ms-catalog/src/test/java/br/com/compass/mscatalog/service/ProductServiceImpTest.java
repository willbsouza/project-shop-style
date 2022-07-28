package br.com.compass.mscatalog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.compass.mscatalog.dto.ProductDto;
import br.com.compass.mscatalog.dto.ProductFormDto;
import br.com.compass.mscatalog.entity.Category;
import br.com.compass.mscatalog.entity.Product;
import br.com.compass.mscatalog.repository.CategoryRepository;
import br.com.compass.mscatalog.repository.ProductRepository;
import br.com.compass.mscatalog.service.exception.CategoryNotValidException;
import br.com.compass.mscatalog.service.exception.ObjectNotFoundException;

class ProductServiceImpTest {
	
	private static final long ID = 1L;
	
	@InjectMocks
	private ProductServiceImp productService;
	
	@Mock
	private ProductRepository productRepository;
	
	@Mock
	private CategoryRepository categoryRepository;
	
	private Product product;
	
	private Optional<Product> optProduct;
	
	private ProductFormDto productFormDto;
	
	private List<Product> productList;
	
	private Category categoryWithoutChildren;
	
	private Category categoryWithChildren;
	
	private Category categoryNotActive;
	
	private Optional<Category> optCategoryWithoutChildren;
	
	private Optional<Category> optCategoryNotActive;
	
	private Optional<Category> optCategoryWithChildren;

	@BeforeEach
	void setUp() {
		openMocks(this);
		startProduct();
	}

	@Test
	void whenFindAllThenReturnListOfProducts() {
		when(productRepository.findAll()).thenReturn(productList);
		
		List<ProductDto> response = productService.findAll();
		
		assertNotNull(response);
		assertEquals(ProductDto.class, response.get(0).getClass());
		assertEquals(true, response.get(0).getActive());
		assertEquals(ID, response.get(0).getId());
		assertEquals("Product", response.get(0).getName());
		assertEquals("Description", response.get(0).getDescription());
		assertEquals("Brand", response.get(0).getBrand());
		assertEquals("Material", response.get(0).getMaterial());
	}
	
	@Test
	void whenFindByIdThenReturnAProductDto() {
		when(productRepository.findById(anyLong())).thenReturn(optProduct);
		
		ProductDto response = productService.findById(ID);
		
		assertNotNull(response);
		assertEquals(ProductDto.class, response.getClass());
		assertEquals(true, response.getActive());
		assertEquals(ID, response.getId());
		assertEquals("Product", response.getName());
		assertEquals("Description", response.getDescription());
		assertEquals("Brand", response.getBrand());
		assertEquals("Material", response.getMaterial());
	}
	
	@Test
	void whenFindByIdThenReturnObjectNotFoundException() {
		when(productRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Product ID: " + ID + " not found."));
		
		try {
			productService.findById(ID);			
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Product ID: " + ID + " not found.", e.getMessage());
		}
	}
	
	@Test
	void whenUpdateThenReturnAProductDto() {
		when(productRepository.findById(anyLong())).thenReturn(optProduct);
		when(categoryRepository.findById(anyLong())).thenReturn(optCategoryWithoutChildren);
		when(productRepository.save(any())).thenReturn(product);
		
		ProductDto response = productService.update(ID, productFormDto);
		
		assertNotNull(response);
		assertEquals(ProductDto.class, response.getClass());
		assertEquals(true, response.getActive());
		assertEquals(ID, response.getId());
		assertEquals("Product", response.getName());
		assertEquals("Description", response.getDescription());
		assertEquals("Brand", response.getBrand());
		assertEquals("Material", response.getMaterial());
	}
	
	@Test
	void whenUpdateProductWithCategoryNotActiveThenReturnCategoryNotValidException() {
		when(productRepository.findById(anyLong())).thenReturn(optProduct);
		when(categoryRepository.findById(anyLong())).thenReturn(optCategoryNotActive);
		when(productRepository.save(any())).thenReturn(product);
		
		try {
			productService.update(ID, productFormDto);
		} catch (Exception e) {
			assertEquals(CategoryNotValidException.class, e.getClass());
			assertEquals("It is not possible to add a product to this category.", e.getMessage());
		}
	}
	
	@Test
	void whenUpdateProductWithCategoryWithChildrenThenReturnCategoryNotValidException() {
		when(productRepository.findById(anyLong())).thenReturn(optProduct);
		when(categoryRepository.findById(anyLong())).thenReturn(optCategoryWithChildren);
		when(productRepository.save(any())).thenReturn(product);
		
		try {
			productService.update(ID, productFormDto);
		} catch (Exception e) {
			assertEquals(CategoryNotValidException.class, e.getClass());
			assertEquals("It is not possible to add a product to this category.", e.getMessage());
		}
	}
	
	@Test
	void whenUpdateCategoryNotFoundThenReturnObjectNotFoundException() {
		when(productRepository.findById(anyLong())).thenReturn(optProduct);
		when(categoryRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Category ID: " + ID + " not found."));
		
		try {
			productService.update(ID, productFormDto);			
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Category ID: " + ID + " not found.", e.getMessage());
		}
	}
	
	@Test
	void whenUpdateProductNotFoundThenReturnObjectNotFoundException() {
		when(categoryRepository.findById(anyLong())).thenReturn(optCategoryWithoutChildren);
		when(productRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Product ID: " + ID + " not found."));
		
		try {
			productService.update(ID, productFormDto);			
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Product ID: " + ID + " not found.", e.getMessage());
		}
	}
	
	@Test
	void whenDeleteByIdThenDeleteWithSuccess() {
		when(productRepository.findById(anyLong())).thenReturn(optProduct);
		doNothing().when(productRepository).deleteById(anyLong());
		productService.deleteById(ID);
		
		verify(productRepository, times(1)).deleteById(anyLong());
	}
	
	@Test
	void whenDeleteByIdThenReturnObjectNotFoundException() {
		when(categoryRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Product ID: " + ID + " not found."));
		
		try {
			productService.deleteById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Product ID: " + ID + " not found.", e.getMessage());
		}
	}
	
	private void startProduct() {
		
		categoryWithoutChildren = new Category();
		categoryWithoutChildren.setId(ID);
		categoryWithoutChildren.setActive(true);
		categoryWithoutChildren.setName("Category");
		
		optCategoryWithoutChildren = Optional.of(categoryWithoutChildren);
		
		categoryNotActive = new Category();
		categoryNotActive.setId(ID);
		categoryNotActive.setActive(false);
		categoryNotActive.setName("Category");
		
		optCategoryNotActive = Optional.of(categoryNotActive);
		
		categoryWithChildren = new Category();
		categoryWithChildren.setId(ID);
		categoryWithChildren.setActive(true);
		categoryWithChildren.setName("Category");
		categoryWithChildren.setChildren(List.of(categoryWithoutChildren));
		
		optCategoryWithChildren = Optional.of(categoryWithChildren);
		
		productFormDto = new ProductFormDto();
		productFormDto.setActive(true);
		productFormDto.setName("Product");
		productFormDto.setDescription("Description");
		productFormDto.setBrand("Brand");
		productFormDto.setMaterial("Material");
		productFormDto.setCategoryId(ID);
		
		product = new Product(productFormDto, categoryWithoutChildren);
		product.setId(ID);
		
		optProduct = Optional.of(product);
		
		productList = List.of(product);
	}
}