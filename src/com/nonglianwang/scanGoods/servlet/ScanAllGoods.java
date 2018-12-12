package com.nonglianwang.scanGoods.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonglianwang.scanGoods.bean.ScanGoodsBean;
import com.nonglianwang.scanGoods.service.ScanGoodsService;

import net.sf.json.JSONObject;


@WebServlet("/ScanAllGoods")
public class ScanAllGoods extends HttpServlet {
	private ScanGoodsService sgs=new ScanGoodsService();
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String queryString=request.getParameter("catagory");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw=response.getWriter();
		String page_Code=request.getParameter("pagecode");
		//获取了商品查询数据后
		if (queryString!=null) {
/*			List<GoodsBean> goodslist=sgs.scanAllgoods();
			if (request.getSession(false).getAttribute("AllGoods")!=null) {
				request.getSession(false).removeAttribute("AllGoods");
			}
			request.getSession(false).setAttribute("AllGoods", goodslist);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("flag", "success");
			pw.write(jsonObject.toString());
			return;*/
			ScanGoodsBean pagebean=new ScanGoodsBean();
			pagebean.setCurrentPage(1);
			pagebean.setGoodslist(sgs.loadPageBean(pagebean.getCurrentPage(),pagebean.getPageRecord()));
			pagebean.setNextHref(pagebean.getCurrentPage()>=pagebean.getTotalPage()?"":String.valueOf((pagebean.getCurrentPage()+1)));
			pagebean.setPageRecord(pagebean.getPageRecord());
			/*System.out.println("页面记录数为:"+pagebean.getPageRecord());*/
			pagebean.setPreviousHref(pagebean.getCurrentPage()<=1?"":String.valueOf((pagebean.getCurrentPage()-1)));
			pagebean.setTotalRecord(sgs.getAllGoodsCount());
			/*System.out.println("总记录数为:"+pagebean.getTotalRecord());*/
			pagebean.setTotalPage(pagebean.getTotalPage());
			/*System.out.println("总页数为:"+pagebean.getTotalPage());*/
			pagebean.setStartCode(pagebean.getCurrentPage()<=5?1:pagebean.getCurrentPage()-5);
			pagebean.setEndCode(pagebean.getCurrentPage()+5>=pagebean.getTotalPage()?pagebean.getTotalPage():pagebean.getCurrentPage()+5);
			JSONObject jsonObject=JSONObject.fromObject(pagebean);
			pw.write(jsonObject.toString());
			return;
		}
		if(page_Code!=null){
			int pageCode=Integer.valueOf(page_Code);
			ScanGoodsBean pagebean=new ScanGoodsBean();
			pagebean.setCurrentPage(pageCode);
			pagebean.setGoodslist(sgs.loadPageBean(pagebean.getCurrentPage(),pagebean.getPageRecord()));
			pagebean.setNextHref(pagebean.getCurrentPage()>=pagebean.getTotalPage()?"javascript:void(0);":request.getContextPath()+"/ScanAllGoods?"+(pagebean.getCurrentPage()+1));
			pagebean.setPageRecord(pagebean.getPageRecord());
			pagebean.setPreviousHref(pagebean.getCurrentPage()<=1?"javascript:void(0);":request.getContextPath()+"/ScanAllGoods?"+(pagebean.getCurrentPage()-1));
			pagebean.setTotalRecord(sgs.getAllGoodsCount());
			pagebean.setTotalPage(pagebean.getTotalPage());
			pagebean.setStartCode(pagebean.getCurrentPage()<=5?1:pagebean.getCurrentPage()-5);
			pagebean.setEndCode(pagebean.getCurrentPage()+5>=pagebean.getTotalPage()?pagebean.getTotalPage():pagebean.getCurrentPage()+5);
			JSONObject jsonObject=JSONObject.fromObject(pagebean);
			pw.write(jsonObject.toString());
			return;
		}
	}

}
