package com.neuedu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.entity.User;
import com.neuedu.service.UserService;
import com.neuedu.service.impl.UserServiceImpl;

@WebServlet(urlPatterns={"/login.do"})
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = -6290255066918177431L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		//�����û���������
		UserService userService = new UserServiceImpl();
		User user = userService.selectUserByUsername(username);
		//�ж������Ƿ����
		if (user != null && user.getPassword().equals(password)) {
			//��¼�ɹ�����Ϣ�浽session
			req.getSession().setAttribute("user", user);
			//��¼�ɹ����û����洢��cookie��
			Cookie cookie = new Cookie("username", user.getUsername());
			cookie.setMaxAge(60 * 60 * 24 * 7);
			resp.addCookie(cookie);
			//��ת�б�ҳ
			resp.sendRedirect("emplist.do");
		}else {
			resp.sendRedirect("loginview.do");
		}
	}
	
}
