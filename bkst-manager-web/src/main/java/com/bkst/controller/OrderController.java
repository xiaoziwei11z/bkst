package com.bkst.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bkst.pojo.Order;
import com.bkst.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/showOrder.html")
	public String showOrder(Model model) {
		List<Order> orderList = orderService.showAllOrders();
		model.addAttribute("orderList", orderList);
		
		return "showOrder";
	}
	
	@RequestMapping("/send/{id}.html")
	public String send(Model model,@PathVariable Long id) {
		orderService.send(id);
		
		return "redirect:/showOrder.html";
	}
}
