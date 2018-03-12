package com.neuedu.action;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@WebServlet(urlPatterns={"/code.do"})
public class CreateCodeServlet extends HttpServlet{
	
	private static final long serialVersionUID = -3936361219249655047L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//������Ӧ��ʽΪjpg
		resp.setContentType("image/jpeg");
		//����ͼƬ
		BufferedImage image = new BufferedImage(60, 20, BufferedImage.TYPE_INT_RGB);
		//��û���
		Graphics g = image.getGraphics();
		//���ñ�����ɫ
		//���������
		Random r = new Random();
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		//��䱳����ɫ
		g.fillRect(0, 0, 60, 20);
		//����ǰ����ɫ
		g.setColor(new Color(0,0,0));
		//���������
		int i = r.nextInt(99999);
		
		String number = String.valueOf(i);
		System.out.println(number);
		Cookie cookie = new Cookie("code", number);
		resp.addCookie(cookie);
		//����ͼƬ��
		g.drawString(number, 5, 15);
		//ѹ�������
		//����ֽ������,��ΪҪ�������ͼ��ѹ��֮��
		//���ֽ�����,����,������ PrintWriter��
		OutputStream os = resp.getOutputStream();
		//��ͼƬѹ�������
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
		encoder.encode(image);
	}
	
}
