package com.bkst.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bkst.common.pojo.User;
import com.bkst.common.utils.IDUtils;
import com.bkst.pojo.Book;
import com.bkst.pojo.Order;
import com.bkst.service.BookService;
import com.bkst.service.CartService;
import com.bkst.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private BookService bookService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/toAddOrder/{bid}/{count}.html")
	private String toAddOrder(Model model,@PathVariable Integer bid,@PathVariable Integer count) {
		model.addAttribute("book", bookService.getBookByBid(bid));
		return "addOrder"; 
	}
	
	@RequestMapping("/cartToAddOrder/{bid}/{count}/{cartId}.html")
	private String cartToAddOrder(Model model,@PathVariable Integer bid,@PathVariable Integer count,@PathVariable Long cartId) {
		model.addAttribute("book", bookService.getBookByBid(bid));
		return "addOrder"; 
	}
	
	@RequestMapping("/addOrder/{bid}/{count}.html")
	private String addOrder(Model model,@PathVariable Integer bid,@PathVariable Integer count,
			String address,HttpSession session,Long cartId) {
		if(cartId!=null) {
			cartService.delCartItem(cartId);
		}
		Book book = bookService.getBookByBid(bid);
		Order order = new Order();
		order.setId(IDUtils.genLongId());
		order.setBname(book.getBname());
		order.setBid(bid);
		order.setPrice(book.getPrice());
		order.setCount(count);
		order.setTotal(book.getPrice()*count);
		order.setUid(((User)session.getAttribute("session_user")).getUid());
		order.setAddress(address);
		order.setStatus(0);
		
		orderService.addOrder(order);
		
		return showOrder(model, session); 
	}
	
	@RequestMapping("/showOrder.html")
	private String showOrder(Model model,HttpSession session) {
		List<Order> orderList = orderService.showOrder(((User)session.getAttribute("session_user")).getUid());
		model.addAttribute("orderList",orderList);
		
		return "showOrder";
	}
	
	@RequestMapping("/pay/{id}.html")
	private String pay(Model model,HttpSession session,@PathVariable Long id) {
		orderService.pay(id);
		
		return "redirect:/showOrder.html";
	}
	
	@RequestMapping("/receive/{id}.html")
	private String receive(Model model,HttpSession session,@PathVariable Long id) {
		orderService.receive(id);
		
		return "redirect:/showOrder.html";
	}
}
