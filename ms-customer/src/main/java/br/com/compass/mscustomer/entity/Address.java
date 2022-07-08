package br.com.compass.mscustomer.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.compass.mscustomer.dto.AddressFormDto;
import br.com.compass.mscustomer.entity.enums.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "address")
@Setter
@Getter
@NoArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private State state;
	
	@NotNull @NotEmpty
	private String city;
	
	@NotNull @NotEmpty
	private String district;
	
	@NotNull @NotEmpty
	private String street;
	
	@NotNull @NotEmpty
	private String number;
	
	private String complement;
	
	@NotNull @NotEmpty
	private String cep;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonIgnore
	private Customer customer;
	
	public Address(AddressFormDto addressFormDto, Customer customer) {
		this.street = addressFormDto.getStreet();
		this.number = addressFormDto.getNumber();
		this.complement = addressFormDto.getComplement();
		this.district = addressFormDto.getDistrict();
		this.city = addressFormDto.getCity();
		this.state = addressFormDto.getState();
		this.cep = addressFormDto.getCep();
		this.customer = customer;
	}
}
