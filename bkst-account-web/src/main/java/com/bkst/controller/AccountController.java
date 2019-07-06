package com.bkst.controller;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bkst.common.pojo.User;
import com.bkst.service.AccountService;

@Controller
public class AccountController {
	@Autowired
	private AccountService accountService;
	@Value("${portal_server}")
	private String PORTAL_SERVER;
	@Value("${manager_server}")
	private String MANAGER_SERVER;
	
	@RequestMapping("/login.html")
	public String login(Model model,User form,HttpServletRequest request,HttpServletResponse response) {
		User user = accountService.findUser(form);
		if(user == null) {
			model.addAttribute("username", form.getUsername());
			model.addAttribute("msg", "用户名或密码错误");
			return "login";
		}else {
			request.getSession().setAttribute("session_user", user);
			//存入cookie
			String token = UUID.randomUUID().toString().replaceAll("-", "");
			Cookie cookie = new Cookie("TOKEN",token);
			cookie.setMaxAge(60*30);
			response.addCookie(cookie);
			
			//存入redis
			accountService.setUser(token,user);
			
			if(user.getType() == 1) {
				return "redirect:"+MANAGER_SERVER;
			}else {
				return "redirect:"+PORTAL_SERVER;
			}
		}
	}
	
	@RequestMapping("/register.html")
	public String register(Model model,User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		if(username.contains(" ")) {
			model.addAttribute("username", username);
			model.addAttribute("userMsg","用户名不能含有空格");
			return "register";
		}
		if(password.contains(" ")) {
			model.addAttribute("username", username);
			model.addAttribute("pswdMsg","密码不能含有空格");
			return "register";
		}
		if(username.length()<4 || username.length() >10) {
			model.addAttribute("username", username);
			model.addAttribute("userMsg","用户名长度必须在4位以上10位以下");
			return "register";
		}
		if(password.length()<8 || username.length() >16) {
			model.addAttribute("username", username);
			model.addAttribute("pswdMsg","密码长度必须在8位以上16位以下");
			return "register";
		}
		
		if(accountService.register(user)) {
			return "success";
		}else {
			model.addAttribute("username", username);
			model.addAttribute("userMsg","用户名已存在");
			return "register";
		}
	}
}
