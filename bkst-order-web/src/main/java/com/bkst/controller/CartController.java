package com.bkst.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bkst.common.pojo.User;
import com.bkst.common.utils.IDUtils;
import com.bkst.pojo.Book;
import com.bkst.pojo.CartItem;
import com.bkst.service.BookService;
import com.bkst.service.CartService;

@Controller
public class CartController {
	@Value("${portal_server}")
	private String PORTAL_SERVER;
	@Autowired
	private BookService bookService;
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/showCart.html")
	public String showCart(Model model,HttpSession session) {
		List<CartItem> cart = cartService.getCart(((User)session.getAttribute("session_user")).getUid());
		model.addAttribute("cart", cart);
		return "showCart";
	}
	
	@RequestMapping("/addToCart/{bid}/{count}.html")
	public String addToCart(Model model,@PathVariable Integer bid,@PathVariable Integer count,HttpSession session) {
		Book book = bookService.getBookByBid(bid);
		CartItem cartItem = new CartItem();
		cartItem.setId(IDUtils.genLongId());
		cartItem.setBname(book.getBname());
		cartItem.setBid(bid);
		cartItem.setPrice(book.getPrice());
		cartItem.setCount(count);
		cartItem.setTotal(book.getPrice()*count);
		cartItem.setUid(((User)session.getAttribute("session_user")).getUid());
		
		cartService.addToCart(cartItem);
		
		return showCart(model,session);
	}
	
	@RequestMapping("/delCartItem/{id}.html")
	public String addToCart(Model model,HttpSession session,@PathVariable Long id) {
		cartService.delCartItem(id);
		
		return showCart(model,session);
	}
}
