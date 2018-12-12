package com.nonglianwang.regist.registservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonglianwang.regist.registservice.RegistService;

/**
 * Servlet implementation class RegistActivation
 */
@WebServlet("/activation")
public class RegistActivation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RegistService rs = new RegistService();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/* 获取链接后面的参数 */
		String code=req.getQueryString();
		//得到activationCode ={0}中的{0}，也就是激活码
		int from=code.indexOf("=");
		int end=code.length();
		code=code.substring(from+1, end);
		rs.activation(code);
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter pw=resp.getWriter();
		pw.write("恭喜你，账户激活成功，可以去登陆了呢~~~");
		
		

	}
}
