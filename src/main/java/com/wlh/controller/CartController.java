package com.wlh.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wlh.model.ShoppingCart;
import com.wlh.model.Product;
import com.wlh.model.Users;
import com.wlh.repository.ProductRepository;
import com.wlh.service.ShoppingCartService;
import com.wlh.service.UserService;

@Controller
public class CartController {
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	
    @GetMapping("/product")
    public String product(@RequestParam("id") Long id, Model model, Principal principal){
 
    	Product product = productRepo.findById(id).get();
    	
    	model.addAttribute("product", product);
    	
        return "product";
    }
	
	
	
	@PostMapping("/addProduct")
	public String addProduct(@RequestParam("id") Long id, 
			@RequestParam("quantity") Integer quantity, 
			Model model,Principal principal) {
		
		Product product = productRepo.findById(id).get();
		
		
		shoppingCartService.addProduct(product,quantity);
		
		return "forword:/shopping-cart";
	}
	
	
}
