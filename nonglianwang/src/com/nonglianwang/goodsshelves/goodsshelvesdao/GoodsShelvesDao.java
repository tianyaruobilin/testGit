package com.nonglianwang.goodsshelves.goodsshelvesdao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.nonglianwang.addgoods.goodsbean.GoodsBean;

import utils.C3P0Utils;

public class GoodsShelvesDao {
	private QueryRunner queryRunner=new QueryRunner();
	/**
	 * 获取添加的商品数据
	 * @param uno
	 * @return
	 */
	public List<GoodsBean> selectGoodsShelves(String uno) {
		Connection connection;
		//开启连接
		try {
			connection=C3P0Utils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		String sqlString="select goodsno,uno,goodsname,goodscategory,goodsimg as 'goodsimgpath',goodsprice,goodsdescript,goodsweight,preferential from tb_goods where uno=?";
		Object[] params={uno};
		List<GoodsBean> goodsBeans;
		//查询
		try {
			goodsBeans=queryRunner.query(connection,sqlString, new BeanListHandler<GoodsBean>(GoodsBean.class), params);
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
		}
		//释放连接
		try {
			C3P0Utils.releaseConnection(connection);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return goodsBeans;
	}
	/**
	 * 删除商品，从tb_goods表中，根据goodsno
	 * @param goodsno
	 */
	public void deleteGoods(String goodsno) {
		String[] snolist=getStrArray(goodsno);
		StringBuilder sb=new StringBuilder("delete tb_goods where goodsno in");
		sb.append(" (");
		for (int i = 0; i < snolist.length; i++) {
			if(i==0){
				sb.append("?");
			}
			else {
				sb.append(",?");
			}
		}
		sb.append(")");
		//获得sql语句
		String sql=sb.toString();
		//获取连接
		Connection connection=null;
		try {
			connection=C3P0Utils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		//参数
		Object params[]=snolist.clone();
		try {
			queryRunner.update(connection, sql, params);
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
	 * 分割字符串为数组的方法
	 */
	public String[] getStrArray(String goodsno){
		return goodsno.split("-");
	}
}
