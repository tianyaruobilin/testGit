package com.nonglianwang.login.loginService;

import java.util.Iterator;
import java.util.List;

import com.nonglianwang.login.loginDao.LoginDao;
import com.nonglianwang.login.loginbean.LoginBean;
import com.nonglianwang.regist.registbean.RegistBean;

public class Loginservice {
	private LoginDao logindao=new LoginDao();

	public RegistBean Login(String username) {
		RegistBean user=logindao.getUserByUsername(username);
		return user;
	}
	/**
	 * 根据uno获取用户所有身份
	 * @param uno
	 * @return
	 */
	public String identity(String uno) {
			@SuppressWarnings("unchecked")
			List<LoginBean> list = (List<LoginBean>) logindao.identity(uno);
			StringBuilder sBuilder=new StringBuilder("");
			Iterator<LoginBean> it = list.iterator();
			while(it.hasNext()) {
			  sBuilder.append("_"+it.next());
			}
			return sBuilder.toString();
	}
}
