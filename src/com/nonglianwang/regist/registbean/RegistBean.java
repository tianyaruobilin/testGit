package com.nonglianwang.regist.registbean;
/**
 * �־ò�
 * @author vm510l
 *
 */
public class RegistBean {
	private String uno;
	private String username;/*�û���*/
	private String password;/*����*/
	private String borntime;/*��������*/
	private String e_mail;/*����*/
	private String sex;/*�Ա�*/
	private String activationCode;/*������*/
	private boolean state;/*�Ƿ񼤻�*/
	
	public RegistBean() {
		// TODO Auto-generated constructor stub
	}

	public RegistBean(String uno, String username, String password, String borntime, String e_mail, String sex,
			String activationCode, boolean state) {
		super();
		this.uno = uno;
		this.username = username;
		this.password = password;
		this.borntime = borntime;
		this.e_mail = e_mail;
		this.sex = sex;
		this.activationCode = activationCode;
		this.state = state;
	}

	public String getUno() {
		return uno;
	}

	public void setUno(String uno) {
		this.uno = uno;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBorntime() {
		return borntime;
	}

	public void setBorntime(String borntime) {
		this.borntime = borntime;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "RegistBean [uno=" + uno + ", username=" + username + ", password=" + password + ", borntime=" + borntime
				+ ", e_mail=" + e_mail + ", sex=" + sex + ", activationCode=" + activationCode + ", state=" + state
				+ "]";
	}


	
	
	
}
