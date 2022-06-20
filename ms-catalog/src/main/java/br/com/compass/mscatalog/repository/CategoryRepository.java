package br.com.compass.mscatalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.compass.mscatalog.entity.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, Long>{
	
}
