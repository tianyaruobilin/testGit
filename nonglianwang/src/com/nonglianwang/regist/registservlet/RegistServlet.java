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
	 * 引用业务层
	 */
	private RegistService rs = new RegistService();
    /**
     * 构造函数
     */
    public RegistServlet() {
        super();
    }
    /**
     * doGet()方法
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RegistBean user = new RegistBean();		/*创建一个新的领域对象，用来装下注册的用户信息*/
		/*借用beanUtils的方法，将请求map压入user中*/
		Map<String, String[]> map=request.getParameterMap();
		try {
			BeanUtils.populate(user, map);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
//验证输入的规范
		//获取用户名
		String userName=user.getUsername();
		//获取密码
		String password=user.getPassword();
		//获取出生日期
		String borntime=user.getBorntime();
		//获取e_mail
		String email=user.getE_mail();
		//获取性别
		String sex=user.getSex();
		//获取输入的验证码
		String varifyCode=map.get("varifyCode")[0];
//		创建一个map用来输入错误的提示
		Map<String, String> errors=new HashMap<String, String>();
		if(userName.trim().length()<3||userName.trim().length()>15||userName==null){
			errors.put("username", "用户名必须在3到15个有效字符之间");
		}
		if (password.trim().length()<3||password.trim().length()>15||password==null) {
			errors.put("password", "密码必须在3到15个有效字符之间");
		}
		if (borntime.trim().length()==0||borntime==null||borntime.equals("")) {
			errors.put("borntime", "出生日期不能为空");
		}
		if (email.trim().length()==0||email==null||email.equals("")) {
			errors.put("email", "email不能为空");
		}
		if (sex.trim().length()==0||sex==null||sex.equals("")) {
			errors.put("sex", "性别不能为空");
		}
		if (varifyCode.trim().length()==0||varifyCode==null||varifyCode.equals("")) {
			errors.put("varifyCode", "验证码不能为空");
		}else {
			String vcodeString=(String) request.getSession(false).getAttribute("vCode");
			if (!(varifyCode.trim().equalsIgnoreCase(vcodeString))) {
				errors.put("varifyCode", "验证码错误");
			}
		}
		if (errors.size()>0) {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("regist/regist.jsp").forward(request, response);
			return;
		}
		
		//到这里验证输入规范就完毕了~~~，接下来开始对数据与数据库进行操作了
		
		//加主键
		user.setUno(CreateUUID.getUUID32());
/*		//加状态,因为数据库该属性设置了默认false，所以可以不用加
		user.setState(false);*/
		//创建激活码
		user.setActivationCode(CreateUUID.getUUID32());
		/*到这里表单数据就已经完全封装到bean对象（user）中了*/
		try {
			rs.addUser(user);
			/*保存成功信息*/
			String msg="注册成功，请前往邮箱激活";
			request.setAttribute("msg",msg);
			request.setAttribute("user", user);
			request.getRequestDispatcher("regist/activation.jsp").forward(request, response);
			return;
		} catch (RegistUnameException e) {
			String msg=e.getMessage();
			request.setAttribute("msg", msg);
			//因为尚未激活，所以保存在request域中，激活后再保存到session域中
			request.setAttribute("user", user);
			request.getRequestDispatcher("regist/regist.jsp").forward(request, response);
			return;
		}
	}
}
