package com.nonglianwang.addtocar.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.nonglianwang.addgoods.goodsbean.GoodsBean;
import com.nonglianwang.addtocar.bean.OrderBean;

import utils.C3P0Utils;

public class Dao {

	private QueryRunner qr = new QueryRunner();

	/**
	 * 添加订单到订单表
	 * 
	 * @param order
	 */
	public void addOrder(OrderBean order) {
		Connection connection = null;
		try {
			connection = C3P0Utils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		String sql = "insert into tb_order(goodsno,goodsname,goodsprice,goodsdescript,goodsweight,buyerno,state,totalPrice) values(?,?,?,?,?,?,?,?)";
		Object[] params = { order.getGoodsno(), order.getGoodsname(), order.getGoodsprice(), order.getGoodsdescript(),
				order.getGoodsweight(), order.getBuyerno(), order.getState(), order.getTotalPrice() };
		try {
			qr.update(connection, sql, params);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		try {
			C3P0Utils.releaseConnection(connection);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	/**
	 * 通过商品编号获取商品
	 * 
	 * @param goodsno
	 * @return
	 */
	public GoodsBean load(String goodsno) {
		Connection connection = null;
		try {
			connection = C3P0Utils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		String sql = "select goodsno,goodsname,goodsprice,goodsdescript,goodsweight from tb_goods where goodsno=?";
		Object[] params = { goodsno };
		GoodsBean goods = null;
		try {
			goods = qr.query(connection, sql, new BeanHandler<GoodsBean>(GoodsBean.class), params);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		try {
			C3P0Utils.releaseConnection(connection);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		System.out.println(goods);
		return goods;
	}

	/**
	 * 检查是否能加入该订单到订单表 （1）、用户名为uno （2）、商品状态为未未完成购买//犯了一个错误，buyerno=?和state=‘未下单’
	 * 
	 * @param order
	 * @return
	 */
	public OrderBean checkOrderInsertable(OrderBean order) {
		Connection connection = null;
		try {
			connection = C3P0Utils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		String sql = "select ono,goodsno,goodsname,goodsprice,goodsdescript,goodsweight,buyerno,state,totalPrice from tb_order where buyerno=? and state=? and goodsno=?";
		Object[] params = { order.getBuyerno(), order.getState(), order.getGoodsno() };
		OrderBean sqlOrder = null;
		try {
			sqlOrder = qr.query(connection, sql, new BeanHandler<OrderBean>(OrderBean.class), params);
		} catch (SQLException e1) {
			throw new RuntimeException();
		}
		try {
			C3P0Utils.releaseConnection(connection);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		System.out.println("sql order" + sqlOrder);
		return sqlOrder;
	}

	/**
	 * 通过订单编号和需要修改的数量，将订单修改为新的订单
	 * 
	 * @param goodsweight
	 *            //goodsweight
	 * @param i
	 *            //goodsno
	 * @param ono
	 *            //buyerno
	 * @param bigDecimal
	 *            //totalPrice
	 */
	public void updateOrderWeight(BigDecimal goodsweight, int i, String ono, BigDecimal bigDecimal) {
		Connection connection = null;
		try {
			connection = C3P0Utils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		String sql = "update tb_order set goodsweight=?,totalPrice=? where buyerno=? and goodsno=?";
		Object[] params = { goodsweight, bigDecimal, ono, i };
		try {
			qr.update(connection, sql, params);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		try {
			C3P0Utils.releaseConnection(connection);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	/**
	 * 查询tb_goods表中该商品剩余的重量
	 * 
	 * @param order
	 * @return
	 */
	public BigDecimal getSqlGoodsWeight(OrderBean order) {
		// 开启连接
		Connection connection = null;
		try {
			connection = C3P0Utils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		//先根据订单的商品的商品号从商品表中获取该商品
		String sql = "select goodsweight from tb_goods where goodsno=?";
		Object[] params = { order.getGoodsno() };
		BigDecimal bigDecimal = null;
		try {
			bigDecimal = qr.query(connection, sql, new ScalarHandler<BigDecimal>(), params);
		} catch (SQLException e1) {
			throw new RuntimeException();
		}
		// 关闭连接
		try {
			C3P0Utils.releaseConnection(connection);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return bigDecimal;
	}

	/**
	 * 更新商品表的质量
	 * 
	 * @param order
	 */ 
	public void updatetb_GoodsWeight(OrderBean order) {
		Connection connection = null;
		try {
			connection = C3P0Utils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		GoodsBean goods=load(String.valueOf(order.getGoodsno()));
		String sql="update tb_goods set goodsweight=? where goodsno=?";
		Object[] params={goods.getGoodsweight().subtract(order.getGoodsweight()),String.valueOf(order.getGoodsno())};
		try {
			qr.update(connection, sql, params);
		} catch (SQLException e1) {
			throw new RuntimeException();
		}
		// 关闭连接
		try {
			C3P0Utils.releaseConnection(connection);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
}
