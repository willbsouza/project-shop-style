package br.com.compass.mscustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableSwagger2
public class MsCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCustomerApplication.class, args);
	}
}
