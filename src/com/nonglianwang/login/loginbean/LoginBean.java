package com.nonglianwang.login.loginbean;

public class LoginBean {
	private String uno;
	private String rname;
	public LoginBean() {
		// TODO Auto-generated constructor stub
	}
	public LoginBean(String uno, String rname) {
		super();
		this.uno = uno;
		this.rname = rname;
	}
	public String getUno() {
		return uno;
	}
	public void setUno(String uno) {
		this.uno = uno;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	@Override
	public String toString() {
		return "LoginBean [uno=" + uno + ", rname=" + rname + "]";
	}
	
}
