package com.rahul.phasezero.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahul.phasezero.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Optional<Product> findByPartNumber(String partNumber);
	
	List<Product> findByPartNameContainingIgnoreCase(String partName);
	
	List<Product> findByCategoryIgnoreCase(String category);

}
