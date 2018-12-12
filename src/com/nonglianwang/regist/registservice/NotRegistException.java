package com.nonglianwang.regist.registservice;

public class NotRegistException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "你还没注册呢，你四不四傻！";
	}
}
