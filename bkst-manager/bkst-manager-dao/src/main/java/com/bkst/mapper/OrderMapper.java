package com.bkst.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bkst.pojo.Order;

public interface OrderMapper {
	@Insert("INSERT INTO tb_order VALUES("
			+ "#{id},#{bname},#{bid},#{price},#{count},#{total},"
			+ "#{uid},#{address},#{status})")
	void insertOrder(Order order);

	@Select("SELECT * FROM tb_order WHERE uid=#{uid}")
	List<Order> selectOrdersByUid(Integer uid);


	@Select("SELECT * FROM tb_order")
	List<Order> selectOrders();

	@Update("UPDATE tb_order SET status=1 WHERE id=#{id}")
	void updateOrderStatus1ById(Long id);

	@Update("UPDATE tb_order SET status=2 WHERE id=#{id}")
	void updateOrderStatus2ById(Long id);

	@Update("UPDATE tb_order SET status=3 WHERE id=#{id}")
	void updateOrderStatus3ById(Long id);

}
