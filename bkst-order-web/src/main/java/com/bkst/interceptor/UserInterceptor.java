package com.bkst.interceptor;

import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bkst.common.pojo.User;
import com.bkst.common.utils.RedisUtils;

public class UserInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Properties properties = new Properties();
		properties.load(this.getClass().getResourceAsStream("/conf/serverURL.properties"));
		
		HttpSession session = request.getSession();
		User sessionUser = (User) session.getAttribute("session_user");
		
		//从redis查询
		if(sessionUser == null) {
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for (Cookie cookie : cookies) {
					if(cookie.getName().equals("TOKEN")) {
						User user = RedisUtils.getUser(cookie.getValue());
						session.setAttribute("session_user",user);
					}
				}
			}
		}
		sessionUser = (User) session.getAttribute("session_user");
		
		if(sessionUser != null && sessionUser.getType() == 0) {
			return true;
		}else {
			response.sendRedirect(properties.getProperty("account_server"));
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
