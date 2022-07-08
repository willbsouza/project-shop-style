package br.com.compass.mscatalog.rabbitmq.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long skuId;
	private Integer quantity;
}

