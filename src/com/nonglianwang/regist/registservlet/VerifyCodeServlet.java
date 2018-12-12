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
		BufferedImage image = vc.getImage();//��ȡһ������֤��ͼƬ
		// �÷���������getImage()����֮��������
//		System.out.println(vc.getText());//��ȡͼƬ�ϵ��ı�
		VerifyCode.output(image, resp.getOutputStream());//��ͼƬд��ָ������
		
		// ���ı����浽session�У�ΪLoginServlet��֤��׼��
		req.getSession().setAttribute("vCode", vc.getText());
	}
}
