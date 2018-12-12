package com.nonglianwang.addtocar.service;



import java.math.BigDecimal;
import java.sql.SQLException;


import com.nonglianwang.addgoods.goodsbean.GoodsBean;
import com.nonglianwang.addtocar.bean.OrderBean;
import com.nonglianwang.addtocar.dao.Dao;

import utils.C3P0Utils;

public class Service {
	Dao dao=new Dao();
	
	/**
	 * 根据商品编号获取商品
	 * @param goodsno
	 * @return
	 */
	public GoodsBean load(String goodsno) {
		return dao.load(goodsno);
	}

	/**
	 * 添加订单
	 * 0、先判断商品库存是否足够
	 * 1、查询商品表中是否存在满足一下两个条件的商品，如果满足，则更新重量就行了，不需要插入数据
	 * 				（1）、用户名为uno
	 * 				（2）、商品状态为未未完成购买
	 * 2、如果不存在上述情况，则可以直接加入数据库
	 * @param order
	 * @throws GoodsNotEnough 
	 */
	public void addOrder(OrderBean order) throws GoodsNotEnough {
		//判断商品是否可以购买（质量是否够）
		BigDecimal sqlGoodsWeight=dao.getSqlGoodsWeight(order);
		System.out.println("该商品的库存剩余"+sqlGoodsWeight);
		if (sqlGoodsWeight.compareTo(order.getGoodsweight())==-1) {//如果数据库中重量小于该笔订单中的质量
			throw new GoodsNotEnough();//抛出商品不足异常
		}else {
			
			//到这里说明商品质量足够，可以下单
			OrderBean sqlOrder=dao.checkOrderInsertable(order);
			if (sqlOrder!=null) {//如果查出了订单，就说明已经存在该名称的商品，在订单表中，则只需要更新重量
				order.setGoodsweight(sqlOrder.getGoodsweight().add(order.getGoodsweight()));
				order.setTotalPrice(sqlOrder.getTotalPrice().add(order.getTotalPrice()));
				System.out.println(order);
				try {
					C3P0Utils.beginTransaction();
					dao.updateOrderWeight(order.getGoodsweight(),order.getGoodsno(),order.getBuyerno(),order.getTotalPrice());
					dao.updatetb_GoodsWeight(order);
					C3P0Utils.commitTransaction();
				} catch (SQLException e) {
					try {
						C3P0Utils.rollbackTransaction();
					} catch (SQLException e1) {
						throw new RuntimeException();
					}
				}
			}else {//否则直接添加
				try {
					C3P0Utils.beginTransaction();
					dao.addOrder(order);
					dao.updatetb_GoodsWeight(order);
					C3P0Utils.commitTransaction();
				} catch (SQLException e) {
					try {
						C3P0Utils.rollbackTransaction();
					} catch (SQLException e1) {
						throw new RuntimeException();
					}
				}
			}
		}
	}
}
