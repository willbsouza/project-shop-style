package br.com.compass.mscustomer.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.compass.mscustomer.entity.Customer;
import br.com.compass.mscustomer.entity.enums.Sex;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomerDto {
	
	private Long id;
	private String firstName;
	private String lastName;
	private Sex sex;
	private LocalDate birthdate;
	private String email;
	private Boolean active;
	private List<AddressDto> addresses = new ArrayList<>();
	
	public CustomerDto(Customer customer) {
		this.id = customer.getId();
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.sex = customer.getSex();
		this.birthdate = customer.getBirthdate();
		this.email = customer.getEmail();
		this.active = customer.getActive();
		this.addresses = customer.getAddresses().stream().map(AddressDto::new).collect(Collectors.toList());
	}
}
