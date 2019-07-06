package com.bkst.service;

import java.util.List;

import com.bkst.pojo.CartItem;

public interface CartService {

	void addToCart(CartItem cartItem);

	List<CartItem> getCart(Integer uid);

	void delCartItem(Long id);

}
