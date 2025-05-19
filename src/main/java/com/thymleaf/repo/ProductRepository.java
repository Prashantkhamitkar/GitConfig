package com.thymleaf.repo;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thymleaf.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	Page<Product> findAll(Pageable pageable);
}
