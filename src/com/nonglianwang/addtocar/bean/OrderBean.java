package com.nonglianwang.addtocar.bean;

import java.math.BigDecimal;

/**
 * ∂©µ•Bean¿‡
 * @author vm510l
 *
 */
public class OrderBean {
	private String ono;
	private int goodsno;
	private String goodsname;
	private BigDecimal goodsprice;
	private String goodsdescript;
	private BigDecimal goodsweight;
	private String buyerno;
	private String state;
	private BigDecimal totalPrice;
	public OrderBean() {
		// TODO Auto-generated constructor stub
	}
	public OrderBean(String ono, int goodsno, String goodsname, BigDecimal goodsprice, String goodsdescript,
			BigDecimal goodsweight, String buyerno, String state, BigDecimal totalPrice) {
		super();
		this.ono = ono;
		this.goodsno = goodsno;
		this.goodsname = goodsname;
		this.goodsprice = goodsprice;
		this.goodsdescript = goodsdescript;
		this.goodsweight = goodsweight;
		this.buyerno = buyerno;
		this.state = state;
		this.totalPrice = totalPrice;
	}
	public String getOno() {
		return ono;
	}
	public void setOno(String ono) {
		this.ono = ono;
	}
	public int getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(int goodsno) {
		this.goodsno = goodsno;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public BigDecimal getGoodsprice() {
		return goodsprice;
	}
	public void setGoodsprice(BigDecimal goodsprice) {
		this.goodsprice = goodsprice;
	}
	public String getGoodsdescript() {
		return goodsdescript;
	}
	public void setGoodsdescript(String goodsdescript) {
		this.goodsdescript = goodsdescript;
	}
	public BigDecimal getGoodsweight() {
		return goodsweight;
	}
	public void setGoodsweight(BigDecimal goodsweight) {
		this.goodsweight = goodsweight;
	}
	public String getBuyerno() {
		return buyerno;
	}
	public void setBuyerno(String buyerno) {
		this.buyerno = buyerno;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	@Override
	public String toString() {
		return "OrderBean [ono=" + ono + ", goodsno=" + goodsno + ", goodsname=" + goodsname + ", goodsprice="
				+ goodsprice + ", goodsdescript=" + goodsdescript + ", goodsweight=" + goodsweight + ", buyerno="
				+ buyerno + ", state=" + state + ", totalPrice=" + totalPrice + "]";
	}
	
	
}

