package com.nonglianwang.scanGoods.service;

import java.util.List;


import com.nonglianwang.addgoods.goodsbean.GoodsBean;
import com.nonglianwang.scanGoods.dao.ScanGoodsDao;

public class ScanGoodsService {
	private ScanGoodsDao ds=new ScanGoodsDao();

	/**
	 * 获取商品数量得总数
	 * @return
	 */
	public int getAllGoodsCount() {
		return ds.getAllGoodsCount();
	}

	/**
	 * 获取当前页得商品记录，根据当前查询得页码，和页面记录数
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
