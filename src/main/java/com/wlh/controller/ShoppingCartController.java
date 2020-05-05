package com.wlh.controller;



import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.wlh.model.CartItem;
import com.wlh.model.Product;
import com.wlh.model.ShoppingCart;
import com.wlh.model.Users;
import com.wlh.service.CartItemService;
import com.wlh.service.ProductService;
import com.wlh.service.ShoppingCartService;
import com.wlh.service.UserService;


@Controller
public class ShoppingCartController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private ProductService productService;

	
	@RequestMapping("/cart")
	public String shoppingCart() {
		return "shoppingCart";
	}
	
	
	
	@GetMapping("/product")
	public String productDetail(
			@PathParam("id") Long id, Model model, Principal principal
			) {
		if (principal != null) {
			String email = principal.getName();
			Users user = userService.findByEmail(email);
			model.addAttribute("user", user);
		}
		
		Product product = productService.findOne(id);
		model.addAttribute("product", product);
		
		
		return "product";
		
	}
	
	//add selected item to the shopping cart
	@RequestMapping("/addItem")
	public String addItem(
			@ModelAttribute("product") Product product, Model model, Principal principal
			) {
		Users user = userService.findByEmail(principal.getName());
		
		//if user not login redirect to login page
		if(user == null) {
			return "redirect:/login";
		}
		
		product = productService.findOne(product.getId());
		

		cartItemService.addProductToCartItem(product, user);
		model.addAttribute("addCdSuccess", true);
		
		return "redirect:/cart";
		
	}
	

	@RequestMapping("/updateCartItem")
	public String updateShoppingCart(@ModelAttribute("id") Long cartItemId) {
		CartItem cartItem = cartItemService.findById(cartItemId);
		cartItemService.updateCartItem(cartItem);
		
		return "forward:/shoppingCart/cart";
	}
	
	@RequestMapping("/removeItem")
	public String removeItem(
			@RequestParam("id") Long id
			) {
			cartItemService.removeCartItem(id);
			
			return "forward:/shoppingCart/cart";
	}
}
