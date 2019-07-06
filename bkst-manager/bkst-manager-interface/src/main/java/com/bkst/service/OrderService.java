package com.bkst.service;

import java.util.List;

import com.bkst.pojo.Order;

public interface OrderService {

	void addOrder(Order order);

	List<Order> showOrder(Integer uid);

	void pay(Long id);

	List<Order> showAllOrders();

	void send(Long id);

	void receive(Long id);

}
