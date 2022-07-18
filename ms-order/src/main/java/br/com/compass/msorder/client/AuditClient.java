package br.com.compass.msorder.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.compass.msorder.entity.Order;

@Component
@FeignClient("audit")
public interface AuditClient {

	@PostMapping("/v1/audit")
	void saveOrderAudit(@RequestBody Order order);
}
