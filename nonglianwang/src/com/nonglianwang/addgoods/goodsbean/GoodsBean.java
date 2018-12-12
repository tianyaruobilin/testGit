package com.nonglianwang.addgoods.goodsbean;

import java.math.BigDecimal;

import net.sf.json.JSONObject;

public class GoodsBean {
	private int goodsno;
	private String uno;
	private String goodsname;
	private String goodscategory;
	private String goodsimgpath;
	private BigDecimal goodsprice;
	private String goodsdescript;
	private BigDecimal goodsweight;
	private String preferential;//是否是优惠商品
	public GoodsBean() {
		// TODO Auto-generated constructor stub
	}
	public GoodsBean(int goodsno, String uno, String goodsname, String goodscategory, String goodsimgpath,
			BigDecimal goodsprice, String goodsdescript, BigDecimal goodsweight, String preferential) {
		super();
		this.goodsno = goodsno;
		this.uno = uno;
		this.goodsname = goodsname;
		this.goodscategory = goodscategory;
		this.goodsimgpath = goodsimgpath;
		this.goodsprice = goodsprice;
		this.goodsdescript = goodsdescript;
		this.goodsweight = goodsweight;
		this.preferential = preferential;
	}

	public int getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(int goodsno) {
		this.goodsno = goodsno;
	}
	public String getUno() {
		return uno;
	}
	public void setUno(String uno) {
		this.uno = uno;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public String getGoodscategory() {
		return goodscategory;
	}
	public void setGoodscategory(String goodscategory) {
		this.goodscategory = goodscategory;
	}
	public String getGoodsimgpath() {
		return goodsimgpath;
	}
	public void setGoodsimgpath(String goodsimgpath) {
		this.goodsimgpath = goodsimgpath;
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
	public String getPreferential() {
		return preferential;
	}
	public void setPreferential(String preferential) {
		this.preferential = preferential;
	}
	
	@Override
	public String toString() {
		return "GoodsBean [goodsno=" + goodsno + ", uno=" + uno + ", goodsname=" + goodsname + ", goodscategory="
				+ goodscategory + ", goodsimgpath=" + goodsimgpath + ", goodsprice=" + goodsprice + ", goodsdescript="
				+ goodsdescript + ", goodsweight=" + goodsweight + ", preferential=" + preferential + "]";
	}
	public String toJSON(){
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("goodsno", goodsno);
		jsonObject.put("uno", uno);
		jsonObject.put("goodsname", goodsname==null?"":goodsname);
		jsonObject.put("goodscategory", goodscategory==null?"":goodscategory);
		jsonObject.put("goodsimgpath", goodsimgpath==null?"":goodsimgpath);
		jsonObject.put("goodsprice",goodsprice==null?"":goodsprice);
		jsonObject.put("goodsdescript", goodsdescript==null?"":goodsdescript);
		jsonObject.put("preferential", preferential==null?"":preferential);
		jsonObject.put("goodsweight", goodsweight==null?"":goodsweight);
		return jsonObject.toString();
	}
	
	
}
