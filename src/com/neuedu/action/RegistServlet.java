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
		//Ϊ�������ṩ������Ϣ
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//�������������
		ServletFileUpload sfu = new ServletFileUpload(factory);
		//�����ļ�����ϴ�����
		sfu.setFileSizeMax(1024*1024*1024);
		try {
			@SuppressWarnings("unchecked")
			//ʹ�ý���������ȡ�����
			List<FileItem> list = sfu.parseRequest(req);
			//ѭ������
			for (int i = 0; i < list.size(); i++) {
				FileItem fi = list.get(i);
				//isFormField()Ϊtrue��ʾ�ⲻ���ϴ�����
				if (!fi.isFormField()) {
					//��ô���ļ�������·��
					//upload�µ�ĳ���ļ��� 
					String path = this.getServletContext().getRealPath("/upload");
					File f = new File(path);
					if (!f.exists()) {
						f.mkdirs();
					}
					//��ȡ�ļ���
					String filename = fi.getName();
					//��uuid�����ļ���
					String newfilename = UUID.randomUUID().toString().replaceAll("-", "")
							+filename.substring(filename.lastIndexOf("."));
					//�ļ�ȫ·��
					String realname = path+"/"+newfilename;
					File file = new File(realname);
					if (!file.exists()) {
						//��ͼƬд��
						fi.write(file);
						//Ҫ�浽���ݿ���ļ�·��
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
