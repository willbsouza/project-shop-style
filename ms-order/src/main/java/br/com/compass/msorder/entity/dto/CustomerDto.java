package br.com.compass.msorder.entity.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Long id;
	@NotNull
	private Long addressId;
}
