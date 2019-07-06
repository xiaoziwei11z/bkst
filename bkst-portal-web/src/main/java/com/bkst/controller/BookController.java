package com.bkst.controller;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bkst.common.utils.RedisUtils;
import com.bkst.pojo.Book;
import com.bkst.pojo.Category;
import com.bkst.pojo.SearchBook;
import com.bkst.service.BookService;
import com.bkst.service.CategoryService;
import com.bkst.service.SearchBookService;

@Controller
public class BookController {
	@Autowired
	BookService bookService;
	@Autowired
	SearchBookService searchBookService;
	@Autowired
	private CategoryService categoryService;
	@Value("${account_server}")
	private String ACCOUNT_SERVER;
	
	@RequestMapping("/index.html")
	public String homepage(Model model,Integer cid) {
		List<Category> categoryList = categoryService.showCategory();
		List<Book> bookList = null;
		if(cid == null) {
			bookList = bookService.showBook(cid);
		}else {
			bookList = bookService.showBookByCid(cid);
		}
		model.addAttribute("bookList", bookList);
		model.addAttribute("categoryList", categoryList);
		
		return "homepage";
	}
	
	@RequestMapping("/bookDetail/{bid}.html")
	public String bookDetail(Model model,@PathVariable Integer bid) {
		Book book = bookService.getBookByBid(bid);
		model.addAttribute("book", book);
		return "bookDetail";
	}
	
	@RequestMapping("/searchBook.html")
	public String searchBook(Model model,String key) {
		List<Category> categoryList = categoryService.showCategory();
		List<SearchBook> bookList = searchBookService.searchBook(key);
		model.addAttribute("bookList", bookList);
		model.addAttribute("categoryList", categoryList);
		return "homepage";
	}
	
	@RequestMapping("/exitLogin.html")
	public String exitLogin(Model model,HttpServletRequest request,HttpServletResponse response) {
		Object object = request.getSession().getAttribute("session_user");
		if(object != null) {
			//删除session
			request.getSession().removeAttribute("session_user");
			String token = null;
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("TOKEN")) {
					token = cookie.getValue();
				}
			}
			//删除cookie
			Cookie cookie = new Cookie("TOKEN",token);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			//删除redis
			RedisUtils.removeUser(token);
		}
		return homepage(model, null);
	}
}
