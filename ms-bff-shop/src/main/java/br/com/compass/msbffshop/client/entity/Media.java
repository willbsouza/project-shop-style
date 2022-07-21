package br.com.compass.msbffshop.client.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Media {

	private Long id;
	private String imagemUrl;
	private Sku sku;
}