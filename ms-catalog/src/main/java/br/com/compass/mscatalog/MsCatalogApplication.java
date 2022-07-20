package br.com.compass.mscatalog;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableRabbit
public class MsCatalogApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MsCatalogApplication.class, args);
	}
}
