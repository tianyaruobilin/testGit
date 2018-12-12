package com.nonglianwang.businessman.businessmansupplementservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import utils.ParseJson;

@WebServlet("/ProvinceServlet")
public class AjaxProvinceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * doGet()·½·¨
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String province = request.getParameter("province");
		if (province.equals("")) {
			response.setContentType("application/json;charset=UTF-8");
			ParseJson parseJson = new ParseJson();
			List<String> provinces = parseJson.findprovinces();
			JSONObject provinceObject = new JSONObject();
			provinceObject.put("province", provinces);
			PrintWriter writer = response.getWriter();
			writer.write(provinceObject.toString());
			writer.flush();
			return;
		}
		else {
			response.setContentType("application/json;charset=UTF-8");
			ParseJson parseJson = new ParseJson();
			List<String> citys = parseJson.selectByProvince(province);
			JSONObject provinceObject = new JSONObject();
			provinceObject.put("citys", citys);
			PrintWriter writer = response.getWriter();
			writer.write(provinceObject.toString());
			writer.flush();
			return;
		}
	}

}
