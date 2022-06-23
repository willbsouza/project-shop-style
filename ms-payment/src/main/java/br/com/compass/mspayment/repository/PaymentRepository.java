package br.com.compass.mspayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compass.mspayment.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
