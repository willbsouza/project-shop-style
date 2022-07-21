package br.com.compass.msbffshop.client.order.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerFormDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Long id;
	@NotNull
	private Long addressId;
}
