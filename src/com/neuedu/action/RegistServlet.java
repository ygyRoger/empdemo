package com.neuedu.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.neuedu.entity.User;
import com.neuedu.service.UserService;
import com.neuedu.service.impl.UserServiceImpl;

@WebServlet(urlPatterns={"/regist.do"})
public class RegistServlet extends HttpServlet{

	private static final long serialVersionUID = -61098691446701795L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> param = fileUp(req, resp);
		UserService userService = new UserServiceImpl();
		User user = new User(null, param.get("username"), param.get("realname"), param.get("password"),
				param.get("sex"), param.get("headimg"));
		userService.insertUser(user);
		resp.sendRedirect("loginview.do");
	}
	
	private Map<String, String> fileUp(HttpServletRequest req,HttpServletResponse resp){
		Map<String, String> param = new HashMap<>();
		//为解析类提供配置信息
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//创建解析类对象
		ServletFileUpload sfu = new ServletFileUpload(factory);
		//设置文件最大上传限制
		sfu.setFileSizeMax(1024*1024*1024);
		try {
			@SuppressWarnings("unchecked")
			//使用解析类对象获取表单项集合
			List<FileItem> list = sfu.parseRequest(req);
			//循环遍历
			for (int i = 0; i < list.size(); i++) {
				FileItem fi = list.get(i);
				//isFormField()为true表示这不是上传表单域
				if (!fi.isFormField()) {
					//获得存放文件的物理路径
					//upload下的某个文件夹 
					String path = this.getServletContext().getRealPath("/upload");
					File f = new File(path);
					if (!f.exists()) {
						f.mkdirs();
					}
					//获取文件名
					String filename = fi.getName();
					//以uuid生成文件名
					String newfilename = UUID.randomUUID().toString().replaceAll("-", "")
							+filename.substring(filename.lastIndexOf("."));
					//文件全路径
					String realname = path+"/"+newfilename;
					File file = new File(realname);
					if (!file.exists()) {
						//将图片写出
						fi.write(file);
						//要存到数据库的文件路径
						String datapath = realname.substring(realname.indexOf("upload"));
						param.put(fi.getFieldName(), datapath);
					}
				}else {
					String name = fi.getFieldName();
					String value = new String(fi.get(),"UTF-8");
					param.put(name, value);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return param;
	}
	
}
