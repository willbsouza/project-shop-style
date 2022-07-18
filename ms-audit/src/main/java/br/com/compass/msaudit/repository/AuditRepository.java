package br.com.compass.msaudit.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.compass.msaudit.entity.Order;

@Repository
public interface AuditRepository extends MongoRepository<Order, String>{

}
