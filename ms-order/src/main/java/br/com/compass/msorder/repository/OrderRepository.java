package br.com.compass.msorder.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.compass.msorder.entity.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>{

}
