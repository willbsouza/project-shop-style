package br.com.compass.msorder.entity.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long skuId;
	private Integer quantity;
}

