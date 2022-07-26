package br.com.compass.mspayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compass.mspayment.entity.Installment;

@Repository
public interface InstallmentRepository extends JpaRepository<Installment, Long>{

	Installment findByPaymentId(Long paymanetId);

}
