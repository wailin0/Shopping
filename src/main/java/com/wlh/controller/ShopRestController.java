package com.wlh.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wlh.model.Product;
import com.wlh.repository.ProductRepository;

@RestController
public class ShopRestController {
	
	@Autowired
	private ProductRepository productRepo;
	
	
	//for getting all products
	@GetMapping("/rest/products")
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}
	
	//for sorting products by category
	@GetMapping("/rest/products/{category}")
	public List<Product> findByCategory(@PathVariable String category) {
		return productRepo.findByCategoryContainingIgnoreCase(category);
	}
	
	//for saving products
	@PostMapping("/rest/products")
	public void saveProducts(@RequestBody Product product){
		 productRepo.save(product);
	}
	
	
	

}
