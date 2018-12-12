package com.nonglianwang.scanGoods.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.nonglianwang.addgoods.goodsbean.GoodsBean;

import utils.C3P0Utils;

public class ScanGoodsDao {
	private QueryRunner qr=new QueryRunner();

	/**
	 * 获取商品得数量
	 * @return
	 */
	public int getAllGoodsCount() {
		Connection connection=null;
		try {
			connection=C3P0Utils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		String sql="select count(*) from tb_goods";
		Integer count=0;
		try {
			count=qr.query(connection, sql, new ScalarHandler<Integer>());
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
	 * 获取当前页面的商品
	 * @param currentPage
	 * @param pageRecord
	 * @return
	 */
	public List<GoodsBean> loadPageBean(int currentPage, int pageRecord) {
		Connection connection=null;
		try {
			connection=C3P0Utils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		String sql="select goodsno,uno,goodsname,goodscategory,goodsimg as 'goodsimgpath',goodsprice,goodsdescript,addtime from tb_goods  where preferential='f' order by addtime offset ? row fetch next ? row only";
		Object[] params={(currentPage-1)*pageRecord,pageRecord};
		List<GoodsBean> goodslist=null;
		try {
			goodslist=qr.query(connection, sql, new BeanListHandler<GoodsBean>(GoodsBean.class), params);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		try {
			C3P0Utils.releaseConnection(connection);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return goodslist;
	}
	
}
