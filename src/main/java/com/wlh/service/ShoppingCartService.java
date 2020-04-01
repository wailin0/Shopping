package com.wlh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlh.model.Product;
import com.wlh.model.ShoppingCart;
import com.wlh.repository.ShoppingCartRepository;

@Service
public class ShoppingCartService {

	@Autowired
	private ShoppingCartRepository shoppingCartRepo;

	public void addProduct(Product product, int quantity) {
		
		ShoppingCart shoppingcart = new ShoppingCart();
		
		shoppingcart.setQuantity(1);
		shoppingcart.setProduct(product);
		
		shoppingCartRepo.save(shoppingcart);
		
	}
	
	
	
	
	
}
