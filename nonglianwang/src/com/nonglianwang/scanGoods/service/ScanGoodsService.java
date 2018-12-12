package com.nonglianwang.scanGoods.service;

import java.util.List;


import com.nonglianwang.addgoods.goodsbean.GoodsBean;
import com.nonglianwang.scanGoods.dao.ScanGoodsDao;

public class ScanGoodsService {
	private ScanGoodsDao ds=new ScanGoodsDao();

	/**
	 * ��ȡ��Ʒ����������
	 * @return
	 */
	public int getAllGoodsCount() {
		return ds.getAllGoodsCount();
	}

	/**
	 * ��ȡ��ǰҳ����Ʒ��¼�����ݵ�ǰ��ѯ��ҳ�룬��ҳ���¼��
	 * @param currentPage
	 * @param pageRecord
	 * @return
	 */
	public List<GoodsBean> loadPageBean(int currentPage, int pageRecord) {
		List<GoodsBean> goodslist=null;
		goodslist=ds.loadPageBean(currentPage,pageRecord);
		return goodslist;
	}
}
