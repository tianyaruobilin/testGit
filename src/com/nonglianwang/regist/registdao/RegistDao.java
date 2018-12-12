package com.nonglianwang.regist.registdao;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.nonglianwang.regist.registbean.RegistBean;

import utils.C3P0Utils;

public class RegistDao {

	// 不支持对事务的操作，因为不能保证获得的连接是同一个
	private QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());

	/**
	 * 加载用户
	 * 
	 * @param <T>
	 * 
	 * @param string
	 * @return
	 */
	public RegistBean loadUser(String string) {
		Connection connection = null;
		try {
			connection = C3P0Utils.getConnection();
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
		}
		RegistBean userBean = null;
		String sql = "select * from tb_user where uno=?";
		Object params[] = { string };
		try {
			userBean = qr.query(connection, sql, new BeanHandler<RegistBean>(RegistBean.class), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			C3P0Utils.releaseConnection(connection);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return userBean;
	}

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @throws RegistException
	 * @throws RegistUnameException
	 */
	public void addUser(RegistBean user) throws RegistUnameException {
		Connection connection = null;
		try {
			connection = C3P0Utils.getConnection();
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
		}
		if (getUserByUname(user.getUsername()) != null) {
			throw new RegistUnameException();
		}
		String date = user.getBorntime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = null;
		try {
			dt = df.parse(date);
		} catch (ParseException e1) {
			throw new RuntimeException(e1);
		}

		java.sql.Date ds = new java.sql.Date(dt.getTime());
		String sql = "insert into tb_user(uno,username,password,borntime,e_mail,sex,activationCode) values (?,?,?,?,?,?,?)";
		Object param[] = { user.getUno(), user.getUsername(), user.getPassword(), ds, user.getE_mail(), user.getSex(),
				user.getActivationCode() };
		try {
			qr.update(connection, sql, param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			C3P0Utils.releaseConnection(connection);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据激活码获得用户
	 * 
	 * @param activationCode
	 * @return
	 */
	public RegistBean getUserByActivationCode(String activationCode) {
		Connection connection = null;
		try {
			connection = C3P0Utils.getConnection();
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
		}
		RegistBean user = null;
		String sql = "select * from tb_user where activationCode=?";
		Object param[] = { activationCode };
		try {
			user = qr.query(connection, sql, new BeanHandler<RegistBean>(RegistBean.class), param);
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
	 * 激活用户
	 * 
	 * @param user
	 */
	public void activation(String code) {
		Connection connection = null;
		try {
			connection = C3P0Utils.getConnection();
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
		}
		String sql = "update tb_user set state='true' where activationCode=?";
		Object param[] = { code };
		try {
			qr.update(connection, sql, param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			C3P0Utils.releaseConnection(connection);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void addUser_Role(String code) {
		Connection connection = null;
		try {
			connection = C3P0Utils.getConnection();
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
		}
		RegistBean user = getUserByActivationCode(code);
		Object param[]={user.getUno()};
		String sql="insert into tb_user_role values(?,'1')";
		try {
			qr.update(connection, sql, param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			C3P0Utils.releaseConnection(connection);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据用户名获得用户
	 * 
	 * @param uname
	 * @return
	 */
	public RegistBean getUserByUname(String uname) {
		Connection connection = null;
		try {
			connection = C3P0Utils.getConnection();
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
		}
		String sql = "select * from tb_user where username=?";
		Object param[] = { uname };
		RegistBean user;
		try {
			user = qr.query(connection, sql, new BeanHandler<RegistBean>(RegistBean.class), param);
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
}
