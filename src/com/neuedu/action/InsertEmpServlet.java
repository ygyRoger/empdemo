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

@WebServlet(urlPatterns={"/insertemp.do"})
public class InsertEmpServlet extends HttpServlet{

	private static final long serialVersionUID = -1673639400783413228L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		//1.��ȡ������
		String name = req.getParameter("name");
		Integer salary = Integer.parseInt(req.getParameter("salary"));
		String job = req.getParameter("job");
		//2.����ҵ��㽫����������ݿ�
		EmpService empService = new EmpServiceImpl();
		//id�Զ�����,����null
		empService.insertEmp(new Emp(null, name, salary, job));
		//3.�ض�����б�ҳ
		resp.sendRedirect("emplist.do");
	}
	
}
