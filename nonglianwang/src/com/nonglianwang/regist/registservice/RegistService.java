package com.nonglianwang.regist.registservice;


import java.sql.SQLException;

import com.nonglianwang.regist.registbean.RegistBean;
import com.nonglianwang.regist.registdao.RegistDao;
import com.nonglianwang.regist.registdao.RegistUnameException;

import utils.C3P0Utils;

public class RegistService {

	private RegistDao rd = new RegistDao();
	/**
	 * ����û�
	 * @param user
	 * @throws RegistException
	 * @throws RegistUnameException 
	 */
	public void addUser(RegistBean user) throws RegistUnameException {
	rd.addUser(user);
	}
	
	/**
	 * �����û�,����uno��ȡ�û�
	 * @throws ActivateException 
	 * @throws NotRegistException 
	 */
	public RegistBean getUser(String uno) throws ActivateException, NotRegistException {
		RegistBean user=rd.loadUser(uno);
		if (user==null) {
			throw new NotRegistException();
		}else{
		if(user.isState()){
			//����û�����״̬Ϊ��
			throw new ActivateException();
		}
		}
		return user;
	}
	/**
	 * �����û�
	 * ���������ڼ����ͬʱ����tb_usre_role���������
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
	 * �����û��������û�
	 * @param uname
	 * @return
	 */
	public RegistBean getUserByUname(String uname){
		RegistBean user=rd.getUserByUname(uname);
		return user;		
	}
}
