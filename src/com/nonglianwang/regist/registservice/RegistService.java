package com.nonglianwang.regist.registservice;


import java.sql.SQLException;

import com.nonglianwang.regist.registbean.RegistBean;
import com.nonglianwang.regist.registdao.RegistDao;
import com.nonglianwang.regist.registdao.RegistUnameException;

import utils.C3P0Utils;

public class RegistService {

	private RegistDao rd = new RegistDao();
	/**
	 * 添加用户
	 * @param user
	 * @throws RegistException
	 * @throws RegistUnameException 
	 */
	public void addUser(RegistBean user) throws RegistUnameException {
	rd.addUser(user);
	}
	
	/**
	 * 加载用户,根据uno获取用户
	 * @throws ActivateException 
	 * @throws NotRegistException 
	 */
	public RegistBean getUser(String uno) throws ActivateException, NotRegistException {
		RegistBean user=rd.loadUser(uno);
		if (user==null) {
			throw new NotRegistException();
		}else{
		if(user.isState()){
			//如果用户激活状态为真
			throw new ActivateException();
		}
		}
		return user;
	}
	/**
	 * 激活用户
	 * 开启事务，在激活的同时，向tb_usre_role里添加数据
	 * @param code
	 * @return
	 */
	public void activation(String code) {
		try {
			C3P0Utils.beginTransaction();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		rd.activation(code);
		rd.addUser_Role(code);
		try {
			C3P0Utils.commitTransaction();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 根据用户名加载用户
	 * @param uname
	 * @return
	 */
	public RegistBean getUserByUname(String uname){
		RegistBean user=rd.getUserByUname(uname);
		return user;		
	}
}
