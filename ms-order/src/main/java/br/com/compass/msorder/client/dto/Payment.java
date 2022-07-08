package br.com.compass.msorder.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Payment {

	private Long id;	
	private String type;
	private Boolean active;
	private Boolean installments;
}
