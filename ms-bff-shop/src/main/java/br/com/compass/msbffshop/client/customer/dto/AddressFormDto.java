package br.com.compass.msbffshop.client.customer.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.compass.msbffshop.client.enums.State;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddressFormDto {
	
	@NotNull
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
