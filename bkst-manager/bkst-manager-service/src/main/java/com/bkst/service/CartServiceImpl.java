package com.bkst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkst.mapper.CartMapper;
import com.bkst.pojo.CartItem;

@Service("cartServiceImpl")
public class CartServiceImpl implements CartService {
	@Autowired
	private CartMapper cartMapper;

	@Override
	public void addToCart(CartItem cartItem) {
		cartMapper.insertCartItem(cartItem);
	}

	@Override
	public List<CartItem> getCart(Integer uid) {
		return cartMapper.selectCartItemsByUid(uid);
	}

	@Override
	public void delCartItem(Long id) {
		cartMapper.deleteCartItemById(id);
	}
	
}
