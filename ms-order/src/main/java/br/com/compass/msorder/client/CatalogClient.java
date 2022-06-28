package br.com.compass.msorder.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.compass.msorder.client.dto.CatalogDto;

@Component
@FeignClient("catalog")
public interface CatalogClient {

	@RequestMapping("/v1/skus/{id}")
	CatalogDto getCatalog(@PathVariable Long id);
}
