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

@WebServlet(urlPatterns={"/updateempview.do"})
public class UpdateEmpViewServlet extends HttpServlet{

	private static final long serialVersionUID = -1641536170852629264L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		//根据id查询员工
		EmpService empService = new EmpServiceImpl();
		Emp emp = empService.selectEmpById(id);
		req.setAttribute("emp", emp);
		req.getRequestDispatcher("WEB-INF/view/updateEmp.jsp").forward(req, resp);
	}
	
}
