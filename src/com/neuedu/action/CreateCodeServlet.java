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
		//设置响应格式为jpg
		resp.setContentType("image/jpeg");
		//创建图片
		BufferedImage image = new BufferedImage(60, 20, BufferedImage.TYPE_INT_RGB);
		//获得画笔
		Graphics g = image.getGraphics();
		//设置背景颜色
		//生成随机数
		Random r = new Random();
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		//填充背景颜色
		g.fillRect(0, 0, 60, 20);
		//设置前景颜色
		g.setColor(new Color(0,0,0));
		//生成随机数
		int i = r.nextInt(99999);
		
		String number = String.valueOf(i);
		System.out.println(number);
		Cookie cookie = new Cookie("code", number);
		resp.addCookie(cookie);
		//画到图片上
		g.drawString(number, 5, 15);
		//压缩并输出
		//获得字节输出流,因为要输出的是图像压缩之后
		//的字节数组,所以,不能用 PrintWriter。
		OutputStream os = resp.getOutputStream();
		//将图片压缩，输出
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
		encoder.encode(image);
	}
	
}
