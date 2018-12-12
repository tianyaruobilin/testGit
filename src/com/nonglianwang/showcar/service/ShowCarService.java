package com.nonglianwang.showcar.service;

import java.util.List;

import com.nonglianwang.addtocar.bean.OrderBean;
import com.nonglianwang.showcar.dao.ShowCarDao;

public class ShowCarService {

	ShowCarDao showCarDao=new ShowCarDao();

	/**
	 * 获取订单表记录总数
	 * @return
	 */
	public int getTotalRecord(String uno) {
		return showCarDao.getTotalRecord(uno);
	}

	/**
	 * 获取所有订单对象
	 * @param uno
	 * @param j 
	 * @param i 
	 * @return
	 */
	public List<OrderBean> getOrderList(String uno) {
		return showCarDao.getOrderList(uno);
	}
}
