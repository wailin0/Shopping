package com.wlh.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlh.model.Product;
import com.wlh.repository.ProductRepository;



@Service
public class ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAll(){
		return (List<Product>)productRepository.findAll();
	}

	public Product findOne(Long id) {
		return productRepository.findById(id).orElse(null);
	}
	
	
}