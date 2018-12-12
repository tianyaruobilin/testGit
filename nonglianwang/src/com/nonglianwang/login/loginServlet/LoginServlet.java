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
	 * ��½servlet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Map<String, String[]>map=request.getParameterMap();
	RegistBean user=new RegistBean();
	try {
		BeanUtils.populate(user, map);
	} catch (Exception e) {
		throw new RuntimeException(e);
	} 
//	���������û���������
	String username=user.getUsername();
	String password=user.getPassword();
	RegistBean SQLuser=loginservice.Login(username);
	if (SQLuser==null) {
		request.setAttribute("errmsg", "û������û���");
		request.getRequestDispatcher("login/login.jsp").forward(request, response);
		return;
	}
	else {
		if (!SQLuser.isState()) {
			request.setAttribute("errmsg", "����˺Ż�û�����أ�����ȥ������伤��Ŷ~~~");
			request.getRequestDispatcher("login/login.jsp").forward(request, response);
			return;
		}
	}
	if (password.equals(SQLuser.getPassword())) {
		//�ж��û���ݣ�ʹ����Ա������ͨ�û��������̼�
		String identityString=loginservice.identity(SQLuser.getUno());
			if (identityString.contains("����Ա")) {
				identityString="����Ա";
			}
			else{
				if (identityString.contains("�̼�")) {
					identityString="�̼�";
				}
				else {
					if(identityString.contains("��Ա�û�")){
						identityString="��Ա�û�";
					}else {
						identityString="��ͨ�û�";
					}
				}
			}
			
			
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		String time=sdf.format(date);
		System.out.println(time+"\t" + identityString+":"+SQLuser.getUsername()+"��½������ϵͳ");
		
		
		request.getSession(false).setAttribute("identityString", identityString);
		request.getSession(false).setAttribute("user", SQLuser);
		request.setAttribute("msg", SQLuser.getUsername()+"��½�ɹ���������ת����ҳ");
		request.getRequestDispatcher("login/msg.jsp").forward(request, response);
		return;
	}else {
		request.setAttribute("errmsg", "�û�������������������ȷ�Ϻ�����������Ŷ~~~");
		request.getRequestDispatcher("login/login.jsp").forward(request, response);
		return;
	}
	}


}
