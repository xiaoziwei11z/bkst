package com.bkst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkst.mapper.OrderMapper;
import com.bkst.pojo.Order;

@Service("orderServiceImpl")
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	public void addOrder(Order order) {
		orderMapper.insertOrder(order);
	}

	@Override
	public List<Order> showOrder(Integer uid) {
		return orderMapper.selectOrdersByUid(uid);
	}

	@Override
	public void pay(Long id) {
		orderMapper.updateOrderStatus1ById(id);
	}

	@Override
	public List<Order> showAllOrders() {
		return orderMapper.selectOrders();
	}

	@Override
	public void send(Long id) {
		orderMapper.updateOrderStatus2ById(id);
	}

	@Override
	public void receive(Long id) {
		orderMapper.updateOrderStatus3ById(id);
	}

}
