package br.com.compass.mscustomer.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.compass.mscustomer.entity.enums.Sex;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerFormUpdateDto {

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
	private Boolean active;
}