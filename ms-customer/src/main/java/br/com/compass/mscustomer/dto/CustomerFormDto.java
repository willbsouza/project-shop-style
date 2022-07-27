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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerFormDto {

	@NotNull
	@Length(min = 3)
	private String firstName;
	
	@NotNull
	@Length(min = 3)
	private String lastName;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Sex sex;

	@NotNull
	@CPF
	private String cpf;
	
	@NotNull
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate birthdate;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	@Length(min = 6)
	private String password;
	
	@NotNull
	private Boolean active;


}
