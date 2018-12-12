package com.nonglianwang.regist.registdao;

public class RegistUnameException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "该用户名已被注册~";
	}
}
