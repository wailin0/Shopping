package com.wlh.service;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlh.model.CartItem;
import com.wlh.model.Order;
import com.wlh.model.Product;
import com.wlh.model.ShoppingCart;
import com.wlh.model.Users;
import com.wlh.repository.CartItemRepository;




@Service
public class CartItemService {
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	
	public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
		return cartItemRepository.findByShoppingCart(shoppingCart);
	}
	
	public CartItem updateCartItem(CartItem cartItem) {
		BigDecimal bigDecimal = new BigDecimal(cartItem.getProduct().getPrice());
		
		bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
		cartItem.setSubTotal(bigDecimal);
		
		cartItemRepository.save(cartItem);
		
		return cartItem;
	}
	
	public CartItem addProductToCartItem(Product product, Users user) {
		List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());
		
		for (CartItem cartItem: cartItemList) {
			if (product.getId() == cartItem.getProduct().getId()) {
				cartItem.setSubTotal(new BigDecimal(product.getPrice()));
				cartItemRepository.save(cartItem);
				return cartItem;
			}
		}
		
		CartItem cartItem = new CartItem();
		cartItem.setShoppingCart(user.getShoppingCart());
		cartItem.setProduct(product);
		cartItem.setSubTotal(new BigDecimal(product.getPrice()));
		
		cartItem = cartItemRepository.save(cartItem);
		
	
		return cartItem;
		
	}
	
	public CartItem findById(Long id) {
		return cartItemRepository.findById(id).orElse(null);
	}

	public void removeCartItem(Long id) {
		cartItemRepository.deleteById(id);
	}
	
	public CartItem save(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}
	
	public List<CartItem> findByOrder(Order order) {
		return cartItemRepository.findByOrder(order);
	}

}
