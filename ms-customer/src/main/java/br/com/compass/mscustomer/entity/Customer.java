package br.com.compass.mscustomer.entity;

import java.time.LocalDate;
import java.util.ArrayList;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Setter
@Getter
@NoArgsConstructor
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
	@JsonFormat(pattern = "dd-MM-yyyy")
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
	private List<Address> addresses = new ArrayList<>();
	
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
}
