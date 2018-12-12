package com.nonglianwang.login.loginServlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.nonglianwang.login.loginService.Loginservice;
import com.nonglianwang.regist.registbean.RegistBean;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Loginservice loginservice=new Loginservice();
       
	/**
	 * 登陆servlet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Map<String, String[]>map=request.getParameterMap();
	RegistBean user=new RegistBean();
	try {
		BeanUtils.populate(user, map);
	} catch (Exception e) {
		throw new RuntimeException(e);
	} 
//	获得输入的用户名和密码
	String username=user.getUsername();
	String password=user.getPassword();
	RegistBean SQLuser=loginservice.Login(username);
	if (SQLuser==null) {
		request.setAttribute("errmsg", "没有这个用户名");
		request.getRequestDispatcher("login/login.jsp").forward(request, response);
		return;
	}
	else {
		if (!SQLuser.isState()) {
			request.setAttribute("errmsg", "你的账号还没激活呢，请先去你的邮箱激活哦~~~");
			request.getRequestDispatcher("login/login.jsp").forward(request, response);
			return;
		}
	}
	if (password.equals(SQLuser.getPassword())) {
		//判断用户身份，使管理员还是普通用户，还是商家
		String identityString=loginservice.identity(SQLuser.getUno());
			if (identityString.contains("管理员")) {
				identityString="管理员";
			}
			else{
				if (identityString.contains("商家")) {
					identityString="商家";
				}
				else {
					if(identityString.contains("会员用户")){
						identityString="会员用户";
					}else {
						identityString="普通用户";
					}
				}
			}
			
			
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		String time=sdf.format(date);
		System.out.println(time+"\t" + identityString+":"+SQLuser.getUsername()+"登陆了您的系统");
		
		
		request.getSession(false).setAttribute("identityString", identityString);
		request.getSession(false).setAttribute("user", SQLuser);
		request.setAttribute("msg", SQLuser.getUsername()+"登陆成功，即将跳转到主页");
		request.getRequestDispatcher("login/msg.jsp").forward(request, response);
		return;
	}else {
		request.setAttribute("errmsg", "用户名或密码输入有误，请确认后再重新输入哦~~~");
		request.getRequestDispatcher("login/login.jsp").forward(request, response);
		return;
	}
	}


}
