package com.nonglianwang.scanGoods.bean;
/**
 * 持久层，用于分页
 * @author vm510l
 *
 */

import java.util.List;

import com.nonglianwang.addgoods.goodsbean.GoodsBean;

public class ScanGoodsBean {
	private int totalPage;//总页数
	private int currentPage;//当前页；
	private int totalRecord;//总记录数
	private List<GoodsBean> goodslist;//当前页得记录
	private int pageRecord=8;//页面记录条数，每一页可见记录数
	private int startCode;//开始页码
	private int endCode;//结束页码
	private String nextHref;//下一页链接地址
	private String previousHref;//上一页链接地址
	public ScanGoodsBean() {
		// TODO Auto-generated constructor stub
	}
	public ScanGoodsBean(int totalPage, int currentPage, int totalRecord, List<GoodsBean> goodslist, int pageRecord,
			int startCode, int endCode, String nextHref, String previousHref) {
		super();
		this.totalPage = totalPage;
		this.currentPage = currentPage;
		this.totalRecord = totalRecord;
		this.goodslist = goodslist;
		this.pageRecord = pageRecord;
		this.startCode = startCode;
		this.endCode = endCode;
		this.nextHref = nextHref;
		this.previousHref = previousHref;
	}
	public int getTotalPage() {
		return (totalRecord/pageRecord)+1;//总记录数除以页面记录数
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public List<GoodsBean> getGoodslist() {
		return goodslist;
	}
	public void setGoodslist(List<GoodsBean> goodslist) {
		this.goodslist = goodslist;
	}
	public int getPageRecord() {
		return pageRecord;
	}
	public void setPageRecord(int pageRecord) {
		this.pageRecord = pageRecord;
	}
	public int getStartCode() {
		return startCode;
	}
	public void setStartCode(int startCode) {
		this.startCode = startCode;
	}
	public int getEndCode() {
		return endCode;
	}
	public void setEndCode(int endCode) {
		this.endCode = endCode;
	}
	public String getNextHref() {
		return nextHref;
	}
	public void setNextHref(String nextHref) {
		this.nextHref = nextHref;
	}
	public String getPreviousHref() {
		return previousHref;
	}
	public void setPreviousHref(String previousHref) {
		this.previousHref = previousHref;
	}
	@Override
	public String toString() {
		return "ScanGoodsBean [totalPage=" + totalPage + ", currentPage=" + currentPage + ", totalRecord=" + totalRecord
				+ ", goodslist=" + goodslist + ", pageRecord=" + pageRecord + ", startCode=" + startCode + ", endCode="
				+ endCode + ", nextHref=" + nextHref + ", previousHref=" + previousHref + "]";
	}
	
}
