package com.nonglianwang.verifier.veriferservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonglianwang.businessman.businessmansupplementInfoBean.BusinessManSupplementInfo;
import com.nonglianwang.verifier.verifierService.VeriferService;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class GetVeriferBusiness
 */
@WebServlet("/GetVeriferBusiness")
public class GetVeriferBusiness extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private VeriferService verifiService= new VeriferService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		int page=Integer.valueOf(req.getParameter("page"));
		int count=Integer.valueOf(req.getParameter("count"));
		@SuppressWarnings("unchecked")
		List<BusinessManSupplementInfo> list=(List<BusinessManSupplementInfo>) verifiService.findAllNeedVeriferBusinessman(page,count);
		JSONArray jsonArray=JSONArray.fromObject(list);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out=resp.getWriter();
		out.write(jsonArray.toString());
		out.flush();
		out.close();
		return;
	}
}
