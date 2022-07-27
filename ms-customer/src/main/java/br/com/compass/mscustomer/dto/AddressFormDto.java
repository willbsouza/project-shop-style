package br.com.compass.mscustomer.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.compass.mscustomer.entity.enums.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressFormDto {
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private State state;
	
	@NotNull @NotEmpty
	private String city;
	
	@NotNull @NotEmpty
	private String district;
	
	@NotNull @NotEmpty
	private String street;
	
	@NotNull @NotEmpty
	private String number;
	
	private String complement;
	
	@NotNull @NotEmpty
	private String cep;
	
	@NotNull
	private Long customerId;
}
