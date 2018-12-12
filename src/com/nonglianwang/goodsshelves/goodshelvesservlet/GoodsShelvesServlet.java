package com.nonglianwang.goodsshelves.goodshelvesservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonglianwang.addgoods.goodsbean.GoodsBean;
import com.nonglianwang.goodsshelves.goodshelvesservice.GoodsShelvesService;

import net.sf.json.JSONArray;

@WebServlet("/GoodsShelves")
public class GoodsShelvesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GoodsShelvesService goodshelvesservice = new GoodsShelvesService();

	/**
	 * 需要获得uno=XXX的用户的商品，从tb_goods中查找，并保存到request中
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uno = request.getParameter("uno");
		String goodsno = request.getParameter("goodsno");
		if (goodsno == null) {
			List<GoodsBean> goodslist = goodshelvesservice.selectGoodsShelves(uno);
			JSONArray jsonArray = new JSONArray();
			for (GoodsBean goodsBean : goodslist) {
				jsonArray.add(goodsBean.toJSON());
			}
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonArray.toString());
			return;
		}
		if(uno==null){
			goodshelvesservice.deleteGoods(goodsno);
			response.getWriter().write("success");
			return;
		}
	}
}
