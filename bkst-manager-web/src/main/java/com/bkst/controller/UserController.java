package com.bkst.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bkst.common.utils.RedisUtils;

@Controller
public class UserController {
	@Value("${account_server}")
	private String ACCOUNT_SERVER;
	
	@RequestMapping("/exitLogin.html")
	public String exitLogin(HttpServletRequest request,HttpServletResponse response) {
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
		return "redirect:" + ACCOUNT_SERVER;
	}
}
