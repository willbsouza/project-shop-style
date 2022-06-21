package br.com.compass.mscustomer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compass.mscustomer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	Customer findByEmail(String email);
}
