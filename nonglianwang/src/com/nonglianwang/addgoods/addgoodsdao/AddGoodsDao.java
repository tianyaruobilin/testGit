package com.nonglianwang.addgoods.addgoodsdao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.nonglianwang.addgoods.goodsbean.GoodsBean;

import utils.C3P0Utils;

public class AddGoodsDao {
	private QueryRunner queryRunner = new QueryRunner();
	
	/**
	 * ������Ʒ��Ŀ�����Ŀ�ı��
	 * @param goodsBean
	 * @return
	 */
	public int selectgoodscon(GoodsBean goodsBean){
		Connection conn=null;
		try {
			conn=C3P0Utils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		String sqlString="select cno from tb_goodscategory where categoryname=?";
		Object[] params={goodsBean.getGoodscategory()};
		int cno=0;
		try {
			cno=queryRunner.query(conn, sqlString, new ScalarHandler<Integer>(), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			C3P0Utils.releaseConnection(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return cno;
	}
	
	/**
	 * �����Ʒ����Ʒ����
	 * @param goodsBean
	 */
	public void addGoods(GoodsBean goodsBean) {
		Connection conn=null;
		try {
			conn=C3P0Utils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		int cno=selectgoodscon(goodsBean);
		String sqlString="insert into tb_goods(goodsname,goodscategory,uno,goodsimg,goodsprice,goodsdescript,goodsweight) values(?,?,?,?,?,?,?)";
		Object param[]={goodsBean.getGoodsname(),cno,goodsBean.getUno(),goodsBean.getGoodsimgpath(),goodsBean.getGoodsprice(),goodsBean.getGoodsdescript(),goodsBean.getGoodsweight()};
		try {
			queryRunner.update(conn, sqlString, param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			C3P0Utils.releaseConnection(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
