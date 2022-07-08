package br.com.compass.msorder.entity.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long addressId;
}
