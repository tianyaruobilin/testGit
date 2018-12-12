package com.nonglianwang.businessman.businessmansupplementInfoBean;

public class BusinessManSupplementInfo {
	private String uno;   //用户id
	private String BusinessManName;//商家真实名字
	private String BusinessManIdCard;//商家身份证号码
	private String BusinessManTel;//商家电话
	private String BusinessManAddr;//商家地址
	private String BusinessManAddrSupplement;//商家地址补充
	private String IsBusinessMan;//是否是商家

	public BusinessManSupplementInfo() {
		// TODO Auto-generated constructor stub
	}

	public BusinessManSupplementInfo(String uno, String businessManName, String businessManIdCard,
			String businessManTel, String businessManAddr, String businessManAddrSupplement, String isBusinessMan) {
		super();
		this.uno = uno;
		BusinessManName = businessManName;
		BusinessManIdCard = businessManIdCard;
		BusinessManTel = businessManTel;
		BusinessManAddr = businessManAddr;
		BusinessManAddrSupplement = businessManAddrSupplement;
		IsBusinessMan = isBusinessMan;
	}

	public String getUno() {
		return uno;
	}

	public void setUno(String uno) {
		this.uno = uno;
	}

	public String getBusinessManName() {
		return BusinessManName;
	}

	public void setBusinessManName(String businessManName) {
		BusinessManName = businessManName;
	}

	public String getBusinessManIdCard() {
		return BusinessManIdCard;
	}

	public void setBusinessManIdCard(String businessManIdCard) {
		BusinessManIdCard = businessManIdCard;
	}

	public String getBusinessManTel() {
		return BusinessManTel;
	}

	public void setBusinessManTel(String businessManTel) {
		BusinessManTel = businessManTel;
	}

	public String getBusinessManAddr() {
		return BusinessManAddr;
	}

	public void setBusinessManAddr(String businessManAddr) {
		BusinessManAddr = businessManAddr;
	}

	public String getBusinessManAddrSupplement() {
		return BusinessManAddrSupplement;
	}

	public void setBusinessManAddrSupplement(String businessManAddrSupplement) {
		BusinessManAddrSupplement = businessManAddrSupplement;
	}

	public String getIsBusinessMan() {
		return IsBusinessMan;
	}

	public void setIsBusinessMan(String isBusinessMan) {
		IsBusinessMan = isBusinessMan;
	}

	@Override
	public String toString() {
		return "BusinessManSupplementInfo [uno=" + uno + ", BusinessManName=" + BusinessManName + ", BusinessManIdCard="
				+ BusinessManIdCard + ", BusinessManTel=" + BusinessManTel + ", BusinessManAddr=" + BusinessManAddr
				+ ", BusinessManAddrSupplement=" + BusinessManAddrSupplement + ", IsBusinessMan=" + IsBusinessMan + "]";
	}
	
}
