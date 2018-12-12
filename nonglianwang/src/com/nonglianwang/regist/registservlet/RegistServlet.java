package com.nonglianwang.regist.registservlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.nonglianwang.regist.registbean.RegistBean;
import com.nonglianwang.regist.registdao.RegistUnameException;
import com.nonglianwang.regist.registservice.RegistService;

import utils.CreateUUID;

@WebServlet("/Regist")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * ����ҵ���
	 */
	private RegistService rs = new RegistService();
    /**
     * ���캯��
     */
    public RegistServlet() {
        super();
    }
    /**
     * doGet()����
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RegistBean user = new RegistBean();		/*����һ���µ������������װ��ע����û���Ϣ*/
		/*����beanUtils�ķ�����������mapѹ��user��*/
		Map<String, String[]> map=request.getParameterMap();
		try {
			BeanUtils.populate(user, map);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
//��֤����Ĺ淶
		//��ȡ�û���
		String userName=user.getUsername();
		//��ȡ����
		String password=user.getPassword();
		//��ȡ��������
		String borntime=user.getBorntime();
		//��ȡe_mail
		String email=user.getE_mail();
		//��ȡ�Ա�
		String sex=user.getSex();
		//��ȡ�������֤��
		String varifyCode=map.get("varifyCode")[0];
//		����һ��map��������������ʾ
		Map<String, String> errors=new HashMap<String, String>();
		if(userName.trim().length()<3||userName.trim().length()>15||userName==null){
			errors.put("username", "�û���������3��15����Ч�ַ�֮��");
		}
		if (password.trim().length()<3||password.trim().length()>15||password==null) {
			errors.put("password", "���������3��15����Ч�ַ�֮��");
		}
		if (borntime.trim().length()==0||borntime==null||borntime.equals("")) {
			errors.put("borntime", "�������ڲ���Ϊ��");
		}
		if (email.trim().length()==0||email==null||email.equals("")) {
			errors.put("email", "email����Ϊ��");
		}
		if (sex.trim().length()==0||sex==null||sex.equals("")) {
			errors.put("sex", "�Ա���Ϊ��");
		}
		if (varifyCode.trim().length()==0||varifyCode==null||varifyCode.equals("")) {
			errors.put("varifyCode", "��֤�벻��Ϊ��");
		}else {
			String vcodeString=(String) request.getSession(false).getAttribute("vCode");
			if (!(varifyCode.trim().equalsIgnoreCase(vcodeString))) {
				errors.put("varifyCode", "��֤�����");
			}
		}
		if (errors.size()>0) {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("regist/regist.jsp").forward(request, response);
			return;
		}
		
		//��������֤����淶�������~~~����������ʼ�����������ݿ���в�����
		
		//������
		user.setUno(CreateUUID.getUUID32());
/*		//��״̬,��Ϊ���ݿ������������Ĭ��false�����Կ��Բ��ü�
		user.setState(false);*/
		//����������
		user.setActivationCode(CreateUUID.getUUID32());
		/*����������ݾ��Ѿ���ȫ��װ��bean����user������*/
		try {
			rs.addUser(user);
			/*����ɹ���Ϣ*/
			String msg="ע��ɹ�����ǰ�����伤��";
			request.setAttribute("msg",msg);
			request.setAttribute("user", user);
			request.getRequestDispatcher("regist/activation.jsp").forward(request, response);
			return;
		} catch (RegistUnameException e) {
			String msg=e.getMessage();
			request.setAttribute("msg", msg);
			//��Ϊ��δ������Ա�����request���У�������ٱ��浽session����
			request.setAttribute("user", user);
			request.getRequestDispatcher("regist/regist.jsp").forward(request, response);
			return;
		}
	}
}
