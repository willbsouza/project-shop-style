package br.com.compass.mscustomer.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.compass.mscustomer.entity.Address;
import br.com.compass.mscustomer.entity.Customer;
import br.com.compass.mscustomer.entity.enums.Sex;

public class CustomerDto {
	
	private Long id;
	private String firstName;
	private String lastName;
	private Sex sex;
	private LocalDate birthdate;
	private String email;
	private Boolean active;
	private List<Address> addresses;
	
	public CustomerDto() {}
	
	public CustomerDto(Customer customer) {
		this.id = customer.getId();
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.sex = customer.getSex();
		this.birthdate = customer.getBirthdate();
		this.email = customer.getEmail();
		this.active = customer.getActive();
		this.addresses = customer.getAddresses();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public List<Address> getAddresses() {
		return addresses;
	}	
}
