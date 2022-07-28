package br.com.compass.msorder.client.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import br.com.compass.msorder.client.enums.Sex;
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
    @JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate birthdate;
	private String email;
	private Boolean active;
}