package br.com.compass.msgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class MsGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/v1/customers/**").uri("lb://customer"))
				.route(r -> r.path("/v1/addresses/**").uri("lb://customer"))
				.route(r -> r.path("/v1/categories/**").uri("lb://catalog"))
				.route(r -> r.path("/v1/products/**").uri("lb://catalog"))
				.route(r -> r.path("/v1/skus/**").uri("lb://catalog"))
				.route(r -> r.path("/v1/payments/**").uri("lb://payment"))
				.route(r -> r.path("/v1/installments/**").uri("lb://payment"))
				.route(r -> r.path("/v1/orders/**").uri("lb://order"))
				.build();
	}

}
