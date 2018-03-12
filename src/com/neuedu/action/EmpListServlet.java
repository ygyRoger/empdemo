package com.neuedu.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.entity.Emp;
import com.neuedu.service.EmpService;
import com.neuedu.service.impl.EmpServiceImpl;

@WebServlet(urlPatterns={"/emplist.do"})
public class EmpListServlet extends HttpServlet{

	private static final long serialVersionUID = 1700469456516200927L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EmpService empService = new EmpServiceImpl();
		List<Emp> empList = empService.selectEmps();
		req.setAttribute("empList", empList);
		req.getRequestDispatcher("WEB-INF/view/emplist.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
}
