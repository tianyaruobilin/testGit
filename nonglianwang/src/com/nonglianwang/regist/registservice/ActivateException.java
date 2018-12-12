package com.nonglianwang.regist.registservice;

public class ActivateException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "你已经激活过了，请不要捣乱，重复激活对身体不好~";
	}
}
