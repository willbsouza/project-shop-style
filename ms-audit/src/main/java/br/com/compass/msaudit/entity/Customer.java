package br.com.compass.msaudit.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import br.com.compass.msaudit.enums.Sex;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Customer {

	private Long id;
	private String firstName;
	private String lastName;
	private Sex sex;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate birthdate;
	private String email;
	private Boolean active;
}