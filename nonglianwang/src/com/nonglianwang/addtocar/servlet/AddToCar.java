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
	 * doGet����
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//��ȡ��Ʒ��������Ʒ���
		BigDecimal goodsWeight=new BigDecimal(request.getParameter("goodsKG"));//��ȡ����
		String goodsno=request.getParameter("goodsno");//��ȡ��Ʒ���
		String uno=request.getParameter("uno");//�û����
		
		GoodsBean goods=service.load(goodsno);//������Ʒ��Ż�ȡ��Ʒ������Ϣ
		
		//�����������󣬲�����ֵ
		OrderBean order=new OrderBean();
		order.setBuyerno(uno);
		order.setGoodsdescript(goods.getGoodsdescript());
		order.setGoodsname(goods.getGoodsname());
		order.setGoodsno(Integer.valueOf(goodsno));
		order.setGoodsprice(goods.getGoodsprice());
		order.setGoodsweight(goodsWeight);
		order.setTotalPrice(goodsWeight.multiply(order.getGoodsprice()));
		order.setState("δ�µ�");
		
		System.out.println(order);
		//��order�����͵�Service��
		try {
			service.addOrder(order);
		} catch (GoodsNotEnough e) {
			//��Ʒ���������Թ���ʱ���׳��쳣
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("<script>alert('"+e.getMessage()+"');history.back();</script>");
			return;
		}
		
		//������Ӧ��Ϣ
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write("<script>alert('��ӳɹ�~');history.back();</script>");
		return;
	}

}
