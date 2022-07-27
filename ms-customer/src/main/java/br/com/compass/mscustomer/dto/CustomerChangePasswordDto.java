package br.com.compass.mscustomer.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerChangePasswordDto {

	@NotNull @Email
	private String email;
	
	@CPF @NotNull
	private String cpf;
	
	@NotNull @NotEmpty
	private String oldPassword;
	
	@NotNull @Length(min = 6)
	private String newPassword;
	
	@NotNull @Length(min = 6)
	private String newPasswordConfirmation;
}
