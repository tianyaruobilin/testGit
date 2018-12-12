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
	 * ������Ʒ��Ż�ȡ��Ʒ
	 * @param goodsno
	 * @return
	 */
	public GoodsBean load(String goodsno) {
		return dao.load(goodsno);
	}

	/**
	 * ��Ӷ���
	 * 0�����ж���Ʒ����Ƿ��㹻
	 * 1����ѯ��Ʒ�����Ƿ��������һ��������������Ʒ��������㣬��������������ˣ�����Ҫ��������
	 * 				��1�����û���Ϊuno
	 * 				��2������Ʒ״̬Ϊδδ��ɹ���
	 * 2�������������������������ֱ�Ӽ������ݿ�
	 * @param order
	 * @throws GoodsNotEnough 
	 */
	public void addOrder(OrderBean order) throws GoodsNotEnough {
		//�ж���Ʒ�Ƿ���Թ��������Ƿ񹻣�
		BigDecimal sqlGoodsWeight=dao.getSqlGoodsWeight(order);
		System.out.println("����Ʒ�Ŀ��ʣ��"+sqlGoodsWeight);
		if (sqlGoodsWeight.compareTo(order.getGoodsweight())==-1) {//������ݿ�������С�ڸñʶ����е�����
			throw new GoodsNotEnough();//�׳���Ʒ�����쳣
		}else {
			
			//������˵����Ʒ�����㹻�������µ�
			OrderBean sqlOrder=dao.checkOrderInsertable(order);
			if (sqlOrder!=null) {//�������˶�������˵���Ѿ����ڸ����Ƶ���Ʒ���ڶ������У���ֻ��Ҫ��������
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
			}else {//����ֱ�����
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
