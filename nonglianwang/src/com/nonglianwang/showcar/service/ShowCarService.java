package com.nonglianwang.showcar.service;

import java.util.List;

import com.nonglianwang.addtocar.bean.OrderBean;
import com.nonglianwang.showcar.dao.ShowCarDao;

public class ShowCarService {

	ShowCarDao showCarDao=new ShowCarDao();

	/**
	 * ��ȡ�������¼����
	 * @return
	 */
	public int getTotalRecord(String uno) {
		return showCarDao.getTotalRecord(uno);
	}

	/**
	 * ��ȡ���ж�������
	 * @param uno
	 * @param j 
	 * @param i 
	 * @return
	 */
	public List<OrderBean> getOrderList(String uno) {
		return showCarDao.getOrderList(uno);
	}
}
