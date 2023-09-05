package com.nit.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.nit.entity.Product;

@CrossOrigin("http://localhost:4200")
public interface IProductRepository extends JpaRepository<Product, Long> {
	
	
	Page<Product> findByCategoryId(@Param("id")Long id,Pageable pageable);
	
	Page<Product> findByNameContaining(@Param("keyword")String keyword,Pageable pageable);

}
