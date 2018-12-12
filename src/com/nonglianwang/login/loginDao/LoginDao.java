package com.nonglianwang.login.loginDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.nonglianwang.login.loginbean.LoginBean;
import com.nonglianwang.regist.registbean.RegistBean;

import utils.C3P0Utils;

public class LoginDao {
	private QueryRunner qr = new QueryRunner();

	/**
	 * 根据用户名字获得用户
	 * 
	 * @param username
	 * @return
	 */
	public RegistBean getUserByUsername(String username) {
		Connection connection = null;
		try {
			connection = C3P0Utils.getConnection();
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
		}
		String sqlString = "select * from tb_user where username=?";
		Object param[] = { username };
		RegistBean user = null;
		try {
			user = qr.query(connection, sqlString, new BeanHandler<RegistBean>(RegistBean.class), param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			C3P0Utils.releaseConnection(connection);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return user;
	}
	/**
	 * 根据uno获取用户所有身份
	 * @param uno
	 * @return
	 */
	public List<?> identity(String uno) {
		String sqlString = "select tb_user.uno,a.rname from "
				+ "(select uno,tb_role.rno,rname from tb_user_role inner join tb_role on tb_role.rno=tb_user_role.rno) a "
				+ "inner join tb_user on a.uno=tb_user.uno and tb_user.uno=?";
		Connection connection;
		try {
			connection = C3P0Utils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		Object param[] = { uno };
		List<?> list = null;
		try {
			list = qr.query(connection, sqlString, new BeanListHandler<LoginBean>(LoginBean.class), param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			C3P0Utils.releaseConnection(connection);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
}
