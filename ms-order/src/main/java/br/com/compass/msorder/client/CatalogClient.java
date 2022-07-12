package br.com.compass.msorder.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.compass.msorder.client.entity.Sku;

@Component
@FeignClient("catalog")
public interface CatalogClient {

	@RequestMapping("/v1/skus/{id}")
	Sku getSku(@RequestParam Long id);
}
