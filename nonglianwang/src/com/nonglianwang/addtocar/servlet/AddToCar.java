package com.nonglianwang.addtocar.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonglianwang.addgoods.goodsbean.GoodsBean;
import com.nonglianwang.addtocar.bean.OrderBean;
import com.nonglianwang.addtocar.service.GoodsNotEnough;
import com.nonglianwang.addtocar.service.Service;

/**
 * Servlet implementation class AddToCar
 */
@WebServlet("/AddToCar")
public class AddToCar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Service service=new Service();
	
	/**
	 * doGet方法
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//获取商品重量和商品编号
		BigDecimal goodsWeight=new BigDecimal(request.getParameter("goodsKG"));//获取重量
		String goodsno=request.getParameter("goodsno");//获取商品编号
		String uno=request.getParameter("uno");//用户编号
		
		GoodsBean goods=service.load(goodsno);//根据商品编号获取商品其它信息
		
		//创建订单对象，并设置值
		OrderBean order=new OrderBean();
		order.setBuyerno(uno);
		order.setGoodsdescript(goods.getGoodsdescript());
		order.setGoodsname(goods.getGoodsname());
		order.setGoodsno(Integer.valueOf(goodsno));
		order.setGoodsprice(goods.getGoodsprice());
		order.setGoodsweight(goodsWeight);
		order.setTotalPrice(goodsWeight.multiply(order.getGoodsprice()));
		order.setState("未下单");
		
		System.out.println(order);
		//将order对象发送到Service层
		try {
			service.addOrder(order);
		} catch (GoodsNotEnough e) {
			//商品重量不足以购买时，抛出异常
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("<script>alert('"+e.getMessage()+"');history.back();</script>");
			return;
		}
		
		//返回响应信息
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write("<script>alert('添加成功~');history.back();</script>");
		return;
	}

}
