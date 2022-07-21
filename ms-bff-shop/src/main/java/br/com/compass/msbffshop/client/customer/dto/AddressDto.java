package br.com.compass.msbffshop.client.customer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.compass.msbffshop.client.enums.State;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddressDto {

	private Long id;
	private State state;
	private String city;
	private String district;
	private String street;
	private String number;
	private String complement;
	private String cep;
}
