package com.nonglianwang.businessman.businessmansupplementDao;

public class BusinessManSupplementException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "你已经申请过了，我们正在审核中，请耐性等待~~~";
	}
}
