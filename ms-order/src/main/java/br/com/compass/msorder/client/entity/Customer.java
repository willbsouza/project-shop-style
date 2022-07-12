package br.com.compass.msorder.client.entity;

import java.time.LocalDate;

import br.com.compass.msorder.client.enums.Sex;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Customer {

	private Long id;
	private String firstName;
	private String lastName;
	private Sex sex;
	private LocalDate birthdate;
	private String email;
	private Boolean active;
}