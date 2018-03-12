package com.neuedu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.entity.Emp;
import com.neuedu.service.EmpService;
import com.neuedu.service.impl.EmpServiceImpl;

@WebServlet(urlPatterns={"/updateemp.do"})
public class UpdateEmpServlet extends HttpServlet{

	private static final long serialVersionUID = 8595484743561923902L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Integer id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		Integer salary = Integer.parseInt(req.getParameter("salary"));
		String job = req.getParameter("job");
		//2.调用业务层将数组修改数据库
		EmpService empService = new EmpServiceImpl();
		empService.updateEmp(new Emp(id, name, salary, job));
		//3.重定向回列表页
		resp.sendRedirect("emplist.do");
	}
	
}
