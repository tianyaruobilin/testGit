package com.nonglianwang.regist.registservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonglianwang.regist.registbean.RegistBean;
import com.nonglianwang.regist.registservice.RegistService;


/**
 * Servlet implementation class AjaxServlet
 */
@WebServlet("/Ajax")
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegistService rs=new RegistService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uname=request.getParameter("username");
		RegistBean user=rs.getUserByUname(uname);
		if (user!=null) {
			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().write("{error:'该用户名已被注册~'}");
		}
		else{
			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().write("{error:''}");
		}
	}
	

}
