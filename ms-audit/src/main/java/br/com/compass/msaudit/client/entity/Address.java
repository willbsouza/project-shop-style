package br.com.compass.msaudit.client.entity;

import br.com.compass.msaudit.client.enums.State;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Address {

	private Long id;
	private State state;
	private String city;
	private String district;
	private String street;
	private String number;
	private String complement;
	private String cep;
}
