package br.com.compass.mscatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compass.mscatalog.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
