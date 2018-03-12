package com.neuedu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.service.EmpService;
import com.neuedu.service.impl.EmpServiceImpl;

@WebServlet(urlPatterns={"/deleteemp.do"})
public class DeleteEmpServlet extends HttpServlet{

	private static final long serialVersionUID = 6916296183055896900L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		//调用业务层去删除数据
		EmpService empService = new EmpServiceImpl();
		empService.deleteEmpById(id);
		resp.sendRedirect("emplist.do");
	}
	
}
