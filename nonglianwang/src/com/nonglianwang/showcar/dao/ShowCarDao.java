package com.nonglianwang.showcar.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.nonglianwang.addtocar.bean.OrderBean;

import utils.C3P0Utils;

public class ShowCarDao {

	private QueryRunner qr=new QueryRunner();

	/**
	 * ��ȡ��������buyernoΪuno�Ķ���������
	 * @return
	 */
	public int getTotalRecord(String uno) {
		Connection connection=null;
		try {
			connection=C3P0Utils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		String sql="select Count(*) from tb_order where buyerno=?";
		Object[] params={uno};
		int count=0;
		try {
			count=qr.query(connection, sql, new ScalarHandler<Integer>(), params);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		try {
			C3P0Utils.releaseConnection(connection);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return count;
	}

	/**
	 * ��ȡ��������buyernoΪuno�Ķ�������
	 * @param uno
	 * @param j //��ǰҳ��
	 * @param i //ÿҳ��¼��
	 * @return
	 */
	public List<OrderBean> getOrderList(String uno) {
		Connection connection=null;
		try {
			connection=C3P0Utils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		String sql="select * from tb_order where buyerno=? order by addtime";
		Object[] params={uno};
		List<OrderBean> orderBeans=null;
		try {
			orderBeans=qr.query(connection, sql, new BeanListHandler<OrderBean>(OrderBean.class),params);
		} catch (SQLException e1) {
			throw new RuntimeException();
		}
		try {
			C3P0Utils.releaseConnection(connection);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return orderBeans;
	}
}
