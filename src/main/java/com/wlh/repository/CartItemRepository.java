package com.wlh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wlh.model.CartItem;
import com.wlh.model.Order;
import com.wlh.model.ShoppingCart;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	List<CartItem> findByOrder(Order order);

	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

}
