package com.neuedu.filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.User;
import com.neuedu.service.UserService;
import com.neuedu.service.impl.UserServiceImpl;

@WebFilter(urlPatterns={"/*"})
public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		//绑定时间
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		request.getServletContext().setAttribute("date", f.format(date));
		
		String uri = req.getRequestURI();
		if (uri.endsWith("loginview.do") || uri.endsWith("login.do") || uri.endsWith(".css")
				|| uri.endsWith(".js") || uri.endsWith(".gif") || uri.endsWith("registview.do") 
				|| uri.endsWith("regist.do") || uri.endsWith("validate.do")) {
			//如果要是跳转登录页的请求和登录请求都得无条件放行
			chain.doFilter(request, response);
		}else {
			//从请求中获得cookie数组
			Cookie[] cookies = req.getCookies();
			//调用自己封装的方法将cookie数组转化成map集合
			Map<String, Cookie> cm = cookieToMap(cookies);
			//从map集合中取出想要的哪个cookie
			Cookie cookie = cm.get("username");
			//判断如果cookie不为空,说明有免登陆的用户
			if (cookie != null) {
				//取出用户名
				String username = cookie.getValue();
				//根据用户名查询出用户对象
				UserService userService = new UserServiceImpl();
				User user = userService.selectUserByUsername(username);
				//将用户对象绑定到session中
				req.getSession().setAttribute("user", user);
				chain.doFilter(request, response);
			} else {
				HttpSession session = req.getSession();
				User user = (User)session.getAttribute("user");
				if (user != null) {
					chain.doFilter(request, response);
				}else {
					resp.sendRedirect("loginview.do");
				}
			}
		}
	}

	/**
	 * 将cookie数组转化成map集合,方便操作
	 * @param cookies
	 * @return
	 */
	private Map<String, Cookie> cookieToMap(Cookie[] cookies){
		Map<String, Cookie> cm = new HashMap<>();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cm.put(cookie.getName(), cookie);
			}
		}
		return cm;
	}
	
}
