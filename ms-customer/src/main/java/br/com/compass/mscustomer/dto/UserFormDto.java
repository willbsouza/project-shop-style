package br.com.compass.mscustomer.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.compass.mscustomer.entity.enums.Sex;

public class UserFormDto {

	@NotNull
	@Length(min = 3)
	private String firstName;
	
	@NotNull
	@Length(min = 3)
	private String lastName;

	@Enumerated(EnumType.STRING)
	private Sex sex;

	@CPF
	private String cpf;
	
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthdate;
	
	@Email
	private String email;
	
	@NotNull
	@Length(min = 8)
	private String password;
	
	@NotNull
	private Boolean active;

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Sex getSex() {
		return sex;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Boolean getActive() {
		return active;
	}
}
