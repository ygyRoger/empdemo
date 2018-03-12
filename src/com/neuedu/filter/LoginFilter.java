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
		
		//��ʱ��
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		request.getServletContext().setAttribute("date", f.format(date));
		
		String uri = req.getRequestURI();
		if (uri.endsWith("loginview.do") || uri.endsWith("login.do") || uri.endsWith(".css")
				|| uri.endsWith(".js") || uri.endsWith(".gif") || uri.endsWith("registview.do") 
				|| uri.endsWith("regist.do") || uri.endsWith("validate.do")) {
			//���Ҫ����ת��¼ҳ������͵�¼���󶼵�����������
			chain.doFilter(request, response);
		}else {
			//�������л��cookie����
			Cookie[] cookies = req.getCookies();
			//�����Լ���װ�ķ�����cookie����ת����map����
			Map<String, Cookie> cm = cookieToMap(cookies);
			//��map������ȡ����Ҫ���ĸ�cookie
			Cookie cookie = cm.get("username");
			//�ж����cookie��Ϊ��,˵�������½���û�
			if (cookie != null) {
				//ȡ���û���
				String username = cookie.getValue();
				//�����û�����ѯ���û�����
				UserService userService = new UserServiceImpl();
				User user = userService.selectUserByUsername(username);
				//���û�����󶨵�session��
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
	 * ��cookie����ת����map����,�������
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
