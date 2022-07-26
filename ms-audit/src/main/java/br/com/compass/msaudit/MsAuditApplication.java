package br.com.compass.msaudit;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableRabbit
public class MsAuditApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAuditApplication.class, args);
	}

}
