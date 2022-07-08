package br.com.compass.mscatalog.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Media {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull @NotEmpty
	private String imagemUrl;
	
	@ManyToOne
	@JsonIgnore
	private Sku sku;
	
	public Media(@NotNull @NotEmpty String imagemUrl, Sku sku) {
		this.imagemUrl = imagemUrl;
		this.sku = sku;
	}
}