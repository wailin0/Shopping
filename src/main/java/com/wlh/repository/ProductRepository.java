package com.wlh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wlh.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	//ingore case equal to "like &search&" in sql
	List<Product> findByCategoryContainingIgnoreCase(String category);

	List<Product> findByPrice(Double price);
	

}
