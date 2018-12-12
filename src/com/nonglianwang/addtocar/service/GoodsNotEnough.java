package com.nonglianwang.addtocar.service;

public class GoodsNotEnough extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "sorry,该商品我们的库存量不够了~~~";
	}
}
