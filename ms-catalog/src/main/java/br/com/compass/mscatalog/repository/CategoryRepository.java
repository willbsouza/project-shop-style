package br.com.compass.mscatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compass.mscatalog.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
