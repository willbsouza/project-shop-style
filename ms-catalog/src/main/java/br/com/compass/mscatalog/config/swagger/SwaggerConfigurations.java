package br.com.compass.mscatalog.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurations {

	@Bean
	public Docket stateApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.compass.mscatalog"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.apiInfo(apiInfo());	
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
		.title("Projeto Shop Style")
		.description("Documentação da API - Shop Style - MS Catalog")
		.version("1.0")
		.build();
		}
}
