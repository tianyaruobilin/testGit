package com.nonglianwang.regist.registservlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.VerifyCode;

@SuppressWarnings("serial")
@WebServlet("/varifyCode")
public class VerifyCodeServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		VerifyCode vc = new VerifyCode();
		BufferedImage image = vc.getImage();//获取一次性验证码图片
		// 该方法必须在getImage()方法之后来调用
//		System.out.println(vc.getText());//获取图片上的文本
		VerifyCode.output(image, resp.getOutputStream());//把图片写到指定流中
		
		// 把文本保存到session中，为LoginServlet验证做准备
		req.getSession().setAttribute("vCode", vc.getText());
	}
}
