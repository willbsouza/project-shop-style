package br.com.compass.mscustomer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compass.mscustomer.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
