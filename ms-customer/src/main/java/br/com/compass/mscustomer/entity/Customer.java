package br.com.compass.mscustomer.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.compass.mscustomer.dto.CustomerFormDto;
import br.com.compass.mscustomer.entity.enums.Sex;

@Entity
@Table(name = "customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Length(min = 3)
	private String firstName;
	
	@NotNull
	@Length(min = 3)
	private String lastName;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Sex sex;

	@CPF
	@NotNull
	private String cpf;
	
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthdate;
	
	@Email
	@Column(unique = true)
	private String email;
	
	@NotNull
	@Length(min = 6)
	private String password;
	
	@NotNull
	private Boolean active;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private List<Address> addresses;
	
	public Customer() {}
	
	public Customer(CustomerFormDto customerFormDto) {
		this.firstName = customerFormDto.getFirstName();
		this.lastName = customerFormDto.getLastName();
		this.sex = customerFormDto.getSex();
		this.cpf = customerFormDto.getCpf();
		this.birthdate = customerFormDto.getBirthdate();
		this.email = customerFormDto.getEmail();
		this.password = new BCryptPasswordEncoder().encode(customerFormDto.getPassword());
		this.active = customerFormDto.getActive();
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
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
