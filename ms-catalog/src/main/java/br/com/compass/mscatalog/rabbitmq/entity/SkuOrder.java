package br.com.compass.mscatalog.rabbitmq.entity;

import java.io.Serializable;
import java.util.List;

import br.com.compass.mscatalog.entity.Sku;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SkuOrder implements Serializable{
	private static final long serialVersionUID = 1L;

	private String orderId;
	private List<Sku> skus;
}

