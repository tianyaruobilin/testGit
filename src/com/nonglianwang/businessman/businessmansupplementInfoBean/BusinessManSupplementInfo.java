package com.nonglianwang.businessman.businessmansupplementInfoBean;

public class BusinessManSupplementInfo {
	private String uno;   //�û�id
	private String BusinessManName;//�̼���ʵ����
	private String BusinessManIdCard;//�̼����֤����
	private String BusinessManTel;//�̼ҵ绰
	private String BusinessManAddr;//�̼ҵ�ַ
	private String BusinessManAddrSupplement;//�̼ҵ�ַ����
	private String IsBusinessMan;//�Ƿ����̼�

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
