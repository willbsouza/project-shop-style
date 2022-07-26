package br.com.compass.msaudit.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class Payment {
	
	private Long id;	
	private String type;
	private Boolean active;
	private Boolean installments;
}