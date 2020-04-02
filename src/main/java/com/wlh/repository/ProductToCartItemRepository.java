package com.wlh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wlh.model.CartItem;
import com.wlh.model.ProductToCartItem;

@Repository
public interface ProductToCartItemRepository extends JpaRepository<ProductToCartItem, Long> {

	static void deleteByCartItem(CartItem findById) {
		// TODO Auto-generated method stub
		
	}

}
