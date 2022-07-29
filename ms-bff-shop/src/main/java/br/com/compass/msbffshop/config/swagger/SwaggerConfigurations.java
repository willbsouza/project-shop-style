package br.com.compass.msbffshop.config.swagger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurations {

	@Bean
	public Docket stateApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.compass.msbffshop"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()))
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
		.title("Projeto Shop Style")
		.description("Documentação da API - Shop Style - MS BFF-Shop")
		.version("1.0")
		.contact(new Contact("Willams Souza", "https://www.linkedin.com/in/willams-bernardo-de-lima-souza-b6561b68/", "willamsbernardo@hotmail.com"))
		.build();
	}
	
	
	public ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }
	
	private SecurityContext securityContext(){
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .operationSelector(operationContext -> true)
                .build();
    }
	
	private List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope = new AuthorizationScope(
                "global", "accessEverything");
        AuthorizationScope[] scopes = new AuthorizationScope[1];
        scopes[0] = authorizationScope;
        SecurityReference reference = new SecurityReference("JWT", scopes);
        List<SecurityReference> auths = new ArrayList<>();
        auths.add(reference);
        return auths;
	}
}

