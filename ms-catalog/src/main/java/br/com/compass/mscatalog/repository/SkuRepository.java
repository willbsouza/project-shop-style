package br.com.compass.mscatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compass.mscatalog.entity.Sku;

@Repository
public interface SkuRepository extends JpaRepository<Sku, Long>{

}
