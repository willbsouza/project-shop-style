package br.com.compass.msbffshop.client.entity;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.compass.msbffshop.client.enums.Sex;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Customer implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String firstName;
	private String lastName;
	private Sex sex;
	private LocalDate birthdate;
	private String email;
	private Boolean active;
}