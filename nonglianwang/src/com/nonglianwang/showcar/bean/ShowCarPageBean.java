package com.nonglianwang.showcar.bean;

import java.util.List;

import com.nonglianwang.addtocar.bean.OrderBean;

public class ShowCarPageBean {
	private List<OrderBean> orders;
	private int start;
	private int end;
	private int totalRecord;//总记录数
	private int pageRecord=8;//页面记录数
	private int totalPage=totalRecord/pageRecord;//总页数
	private int currentPage;//当前页码
	private int viewCodeLength=3;//可见页码个数
	public ShowCarPageBean() {
		// TODO Auto-generated constructor stub
	}
	public ShowCarPageBean(List<OrderBean> orders, int start, int end, int totalRecord, int pageRecord, int totalPage,
			int currentPage, int viewCodeLength) {
		super();
		this.orders = orders;
		this.start = start;
		this.end = end;
		this.totalRecord = totalRecord;
		this.pageRecord = pageRecord;
		this.totalPage = totalPage;
		this.currentPage = currentPage;
		this.viewCodeLength = viewCodeLength;
	}
	public List<OrderBean> getOrders() {
		return orders;
	}
	public void setOrders(List<OrderBean> orders) {
		this.orders = orders;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getPageRecord() {
		return pageRecord;
	}
	public void setPageRecord(int pageRecord) {
		this.pageRecord = pageRecord;
	}
	public int getTotalPage() {
		return totalPage;
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
	public int getViewCodeLength() {
		return viewCodeLength;
	}
	public void setViewCodeLength(int viewCodeLength) {
		this.viewCodeLength = viewCodeLength;
	}
	@Override
	public String toString() {
		return "ShowCarPageBean [orders=" + orders + ", start=" + start + ", end=" + end + ", totalRecord="
				+ totalRecord + ", pageRecord=" + pageRecord + ", totalPage=" + totalPage + ", currentPage="
				+ currentPage + ", viewCodeLength=" + viewCodeLength + "]";
	}
	
}
