package com.bkst.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.bkst.pojo.CartItem;

public interface CartMapper {
	@Insert("INSERT INTO tb_cart_item VALUES("
			+ "#{id},#{bname},#{bid},#{price}"
			+ ",#{count},#{total},#{uid})")
	void insertCartItem(CartItem cartItem);

	@Select("SELECT * FROM tb_cart_item WHERE uid=#{uid}")
	List<CartItem> selectCartItemsByUid(Integer uid);

	@Delete("DELETE FROM tb_cart_item WHERE id=#{id}")
	void deleteCartItemById(Long id);

}
