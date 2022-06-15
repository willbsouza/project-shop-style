package br.com.compass.mscustomer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compass.mscustomer.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);
}
