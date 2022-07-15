package br.com.compass.msorder.entity.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Long skuId;
	@NotNull
	private Integer quantity;
}

