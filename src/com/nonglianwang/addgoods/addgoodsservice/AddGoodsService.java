package com.nonglianwang.addgoods.addgoodsservice;

import com.nonglianwang.addgoods.addgoodsdao.AddGoodsDao;
import com.nonglianwang.addgoods.goodsbean.GoodsBean;

public class AddGoodsService {
	private AddGoodsDao addGoodsDao=new AddGoodsDao();
	
	public void addGoods(GoodsBean goods) {
		addGoodsDao.addGoods(goods);	
	}

}
