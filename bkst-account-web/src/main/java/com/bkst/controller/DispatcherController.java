package com.bkst.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DispatcherController {
	@RequestMapping("/index.html")
	public String showIndex() {
		return "login";
	}
	
	@RequestMapping("/toRegister.html")
	public String toRegister() {
		return "register";
	}
}
