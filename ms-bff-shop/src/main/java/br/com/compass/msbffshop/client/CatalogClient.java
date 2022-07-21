package br.com.compass.msbffshop.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.compass.msbffshop.client.catalog.dto.CategoryDto;
import br.com.compass.msbffshop.client.catalog.dto.ProductDto;

@Component
@FeignClient("catalog")
public interface CatalogClient {

	@GetMapping("/v1/products")
	List<ProductDto> findAllProducts();
	
	@GetMapping("/v1/products/{id}")
	ProductDto findProductById(@PathVariable Long id);
	
	@GetMapping("/v1/categories")
	List<CategoryDto> findAllCategories();
	
	@GetMapping("/v1/categories/{id}/products")
	List<ProductDto> findListProductsByIdCategory(@PathVariable Long id);
}
