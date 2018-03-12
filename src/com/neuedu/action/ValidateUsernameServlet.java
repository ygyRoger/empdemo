package com.neuedu.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.entity.User;
import com.neuedu.service.UserService;
import com.neuedu.service.impl.UserServiceImpl;

@WebServlet(urlPatterns={"/validate.do"})
public class ValidateUsernameServlet extends HttpServlet{

	private static final long serialVersionUID = 1972821508242406685L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		UserService userService = new UserServiceImpl();
		User user = userService.selectUserByUsername(username);
		PrintWriter out = resp.getWriter();
		if (user != null) {
			//回复true代表用户名存在
			out.println("xx");
		}else {
			out.println("ok");
		}
	}
	
}
