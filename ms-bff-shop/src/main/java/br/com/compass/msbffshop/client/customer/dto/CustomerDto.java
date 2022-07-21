package br.com.compass.msbffshop.client.customer.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.compass.msbffshop.client.enums.Sex;
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
}
