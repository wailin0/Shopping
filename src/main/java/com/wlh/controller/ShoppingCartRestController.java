package com.wlh.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wlh.model.CartItem;
import com.wlh.model.ShoppingCart;
import com.wlh.model.Users;
import com.wlh.service.CartItemService;
import com.wlh.service.ShoppingCartService;
import com.wlh.service.UserService;

@RestController
public class ShoppingCartRestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	//get user's shopping cart details
	@GetMapping("/rest/cart/{email}")
	public List<CartItem> shoppingCart(@PathVariable("email") String email) {
		Users user = userService.findByEmail(email);
		ShoppingCart shoppingCart = user.getShoppingCart();
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
	
		return cartItemList;
	}

	//remove item from shopping cart 
	@DeleteMapping("/rest/cart/{id}")
	public void removeItem(@PathVariable("id") Long id) {
		cartItemService.removeCartItem(id);
	}
	
}
