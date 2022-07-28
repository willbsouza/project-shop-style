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

import br.com.compass.mscatalog.dto.SkuDto;
import br.com.compass.mscatalog.dto.SkuFormDto;
import br.com.compass.mscatalog.entity.Category;
import br.com.compass.mscatalog.entity.Media;
import br.com.compass.mscatalog.entity.Product;
import br.com.compass.mscatalog.entity.Sku;
import br.com.compass.mscatalog.repository.MediaRepository;
import br.com.compass.mscatalog.repository.ProductRepository;
import br.com.compass.mscatalog.repository.SkuRepository;
import br.com.compass.mscatalog.service.exception.ObjectNotFoundException;

class SkuServiceImpTest {
	
	private static final long ID = 1L;
	private static final int QUANTITY = 1;
	
	@InjectMocks
	private SkuServiceImp skuService;
	
	@Mock
	private SkuRepository skuRepository;
	
	@Mock
	private ProductRepository productRepository;
	
	@Mock
	private MediaRepository mediaRepository;
	
	private SkuFormDto skuFormDto;
	
	private Optional<Sku> optSku;
	
	private Sku sku;
	
	private List<Sku> skuList;
	
	private Product product;
	
	private Optional<Product> optProduct;
	
	private Category category;
	
	private Media media;

	@BeforeEach
	void setUp() {
		openMocks(this); startSku();
	}

	@Test
	void whenFindAllThenReturnSkuList() {
		when(skuRepository.findAll()).thenReturn(skuList);
		
		List<SkuDto> response = skuService.findAll();
		
		assertNotNull(response);
		assertEquals(SkuDto.class, response.get(0).getClass());
		assertEquals(1, response.size());
		assertEquals(ID, response.get(0).getId());
		assertEquals("Color", response.get(0).getColor());
		assertEquals("Size", response.get(0).getSize());
		assertEquals(1, response.get(0).getQuantity());
		assertEquals(1, response.get(0).getHeight());
		assertEquals(1, response.get(0).getWidth());
		assertEquals(1.0, response.get(0).getPrice());
	}
	
	@Test
	void whenFindByIdThenReturnASkuDto() {
		when(skuRepository.findById(anyLong())).thenReturn(optSku);
		
		SkuDto response = skuService.findById(ID);
		
		assertNotNull(response);
		assertEquals(SkuDto.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals("Color", response.getColor());
		assertEquals("Size", response.getSize());
		assertEquals(1, response.getQuantity());
		assertEquals(1, response.getHeight());
		assertEquals(1, response.getWidth());
		assertEquals(1.0, response.getPrice());
	}
	
	@Test
	void whenFindByIdThenReturnObjectNotFoundException() {
		when(skuRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Sku ID : "+ ID + " not found."));
		
		try {
			skuService.findById(ID);			
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Sku ID : "+ ID + " not found.", e.getMessage());
		}
	}
	
	@Test
	void whenUpdateThenReturnASkuDto() {
		when(productRepository.findById(anyLong())).thenReturn(optProduct);
		when(skuRepository.findById(anyLong())).thenReturn(optSku);
		when(skuRepository.save(any())).thenReturn(sku);
		
		SkuDto response = skuService.update(ID, skuFormDto);
		
		assertNotNull(response);
		assertEquals(SkuDto.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals("Color", response.getColor());
		assertEquals("Size", response.getSize());
		assertEquals(1, response.getQuantity());
		assertEquals(1, response.getHeight());
		assertEquals(1, response.getWidth());
		assertEquals(1.0, response.getPrice());
	}
	
	@Test
	void whenSaveThenReturnASkuDto() {
		when(productRepository.findById(anyLong())).thenReturn(optProduct);
		when(mediaRepository.save(any())).thenReturn(media);
		when(skuRepository.save(any())).thenReturn(sku);
		
		SkuDto response = skuService.save(skuFormDto);
		
		assertNotNull(response);
		assertEquals(SkuDto.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals("Color", response.getColor());
		assertEquals("Size", response.getSize());
		assertEquals(1, response.getQuantity());
		assertEquals(1, response.getHeight());
		assertEquals(1, response.getWidth());
		assertEquals(1.0, response.getPrice());
		
	}
	
	@Test
	void whenSaveThenReturnObjectNotFoundException() {
		when(productRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Product ID : " + ID + " not found."));
		
		try {
			skuService.save(skuFormDto);			
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Product ID : " + ID + " not found.", e.getMessage());
		}
	}
	
	@Test
	void whenUpdateProductNotFoundThenReturnObjectNotFoundException() {
		when(productRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Product ID : " + ID + " not found."));
		when(skuRepository.findById(anyLong())).thenReturn(optSku);
		
		try {
			skuService.update(ID, skuFormDto);			
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Product ID : " + ID + " not found.", e.getMessage());
		}
	}
	
	@Test
	void whenUpdateSkuNotFoundThenReturnObjectNotFoundException() {
		when(productRepository.findById(anyLong())).thenReturn(optProduct);
		when(skuRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Sku ID : "+ ID + " not found."));
		
		try {
			skuService.update(ID, skuFormDto);			
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Sku ID : "+ ID + " not found.", e.getMessage());
		}
	}
	
	
	
	@Test
	void whenUpdateOrderSkuThenReturnASkuDto() {
		when(skuRepository.findById(anyLong())).thenReturn(optSku);
		when(skuRepository.save(any())).thenReturn(sku);
		
		SkuDto response = skuService.updateOrderSku(ID, QUANTITY);
		
		assertNotNull(response);
		assertEquals(SkuDto.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals("Color", response.getColor());
		assertEquals("Size", response.getSize());
		assertEquals(0, response.getQuantity());
		assertEquals(1, response.getHeight());
		assertEquals(1, response.getWidth());
		assertEquals(1.0, response.getPrice());
	}
	
	@Test
	void whenUpdateOrderSkuThenReturnObjectNotFoundException() {
		when(skuRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Sku ID : "+ ID + " not found."));
		
		try {
			skuService.updateOrderSku(ID, QUANTITY);			
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Sku ID : "+ ID + " not found.", e.getMessage());
		}
	}
	
	@Test
	void whenDeleteByIdThenDeleteWithSuccess() {
		when(skuRepository.findById(anyLong())).thenReturn(optSku);
		doNothing().when(skuRepository).deleteById(anyLong());
		skuRepository.deleteById(ID);
		
		verify(skuRepository, times(1)).deleteById(anyLong());
	}
	
	@Test
	void whenDeleteByIdThenReturnObjectNotFoundException() {
		when(skuRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Sku ID : "+ ID + " not found."));
		
		try {
			skuService.deleteById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Sku ID : "+ ID + " not found.", e.getMessage());
		}
	}

	private void startSku() {
		
		category = new Category();
		category.setActive(true);
		category.setName("Category");
		category.setId(ID);
		
		product = new Product();
		product.setActive(true);
		product.setName("Product");
		product.setDescription("Description");
		product.setBrand("Brand");
		product.setMaterial("Material");
		product.setId(ID);
		product.setCategory(category);
		
		optProduct = Optional.of(product);
		
		skuFormDto = new SkuFormDto();
		skuFormDto.setPrice(1.0);
		skuFormDto.setQuantity(1);
		skuFormDto.setHeight(1);
		skuFormDto.setWidth(1);
		skuFormDto.setSize("Size");
		skuFormDto.setColor("Color");
		skuFormDto.setImages(List.of("http://example.com/image-1.png"));
		skuFormDto.setProductId(ID);
		
		sku = new Sku(skuFormDto, product);
		sku.setId(ID);
		
		optSku = Optional.of(sku);
		
		skuList = List.of(sku);
		
		media = new Media();
		media.setId(ID);
		media.setImagemUrl("http://example.com/image-1.png");
		media.setSku(sku);
	}
}