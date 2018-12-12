package com.nonglianwang.goodsshelves.goodshelvesservice;

import java.util.List;

import com.nonglianwang.addgoods.goodsbean.GoodsBean;
import com.nonglianwang.goodsshelves.goodsshelvesdao.GoodsShelvesDao;

public class GoodsShelvesService {
	private GoodsShelvesDao goodsShelvesDao=new GoodsShelvesDao();

	public List<GoodsBean> selectGoodsShelves(String uno) {
		return goodsShelvesDao.selectGoodsShelves(uno);
	}

	public void deleteGoods(String goodsno) {
		goodsShelvesDao.deleteGoods(goodsno);
		
	}
}
