package com.nonglianwang.showcar.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonglianwang.addtocar.bean.OrderBean;
import com.nonglianwang.showcar.service.ShowCarService;

import net.sf.json.JSONArray;

@WebServlet("/ShowCar")
public class ShowCar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShowCarService showCarService=new ShowCarService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uno=request.getParameter("uno");
		/*ShowCarPageBean showCarPageBean=new ShowCarPageBean();
		showCarPageBean.setCurrentPage(1);
		showCarPageBean.setViewCodeLength(showCarPageBean.getViewCodeLength());
		showCarPageBean.setTotalRecord(showCarService.getTotalRecord(uno));
		showCarPageBean.setPageRecord(showCarPageBean.getPageRecord());
		showCarPageBean.setTotalPage(showCarPageBean.getTotalRecord());
		showCarPageBean.setEnd(showCarPageBean.getCurrentPage()+5>showCarPageBean.getTotalPage()?showCarPageBean.getTotalPage():showCarPageBean.getCurrentPage()+5);
		showCarPageBean.setStart(showCarPageBean.getCurrentPage()-5<0?1:showCarPageBean.getCurrentPage()-5);
		showCarPageBean.setOrders(showCarService.getOrderList(uno,showCarPageBean.getCurrentPage(),showCarPageBean.getPageRecord()));*/
/*		response.setContentType("application/json;charset=UTF-8");
		JSONObject jsonObject=JSONObject.fromObject(showCarPageBean);
		System.out.println(jsonObject.toString());
		response.getWriter().write(jsonObject.toString());*/
		/*request.setAttribute("orders", showCarPageBean);
		request.getRequestDispatcher("index.jsp").forward(request, response);*/
		response.setContentType("application/json;charset=UTF-8");
		List<OrderBean> orderBeans=showCarService.getOrderList(uno);
		JSONArray jsonObject=JSONArray.fromObject(orderBeans);
		response.getWriter().write(jsonObject.toString());
		return;
	}

}
