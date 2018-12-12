package com.nonglianwang.businessman.businessmansupplementservlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonglianwang.businessman.businessmansupplementDao.BusinessManSupplementException;
import com.nonglianwang.businessman.businessmansupplementInfoBean.BusinessManSupplementInfo;
import com.nonglianwang.businessman.businessmansupplementService.BusinessManSupplementService;

@WebServlet("/BusinessManSupplement")
public class BusinessManSupplementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BusinessManSupplementService businessManSupplementService = new BusinessManSupplementService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Map<String, String[]> map = request.getParameterMap();
		BusinessManSupplementInfo businessman = new BusinessManSupplementInfo();
		businessman.setBusinessManName(map.get("BusinessManName")[0]);
		businessman.setBusinessManTel(map.get("BusinessManTel")[0]);
		businessman.setBusinessManIdCard(map.get("BusinessManIdCard")[0]);
		businessman.setBusinessManAddrSupplement(map.get("BusinessManAddrSupplement")[0]);
		String province = map.get("province")[0];
		String city = map.get("city")[0];
		businessman.setUno(map.get("uno")[0]);

		/* 开始对数据进行校验 */
		Map<String, String> errorsMap = new HashMap<String, String>();
		if (map.get("BusinessManName")[0].trim().length() < 1 || map.get("BusinessManName")[0].trim().length() > 40) {
			errorsMap.put("BusinessManName", "请输入正确的姓名哦(3-40长度)");
		}
		String string = "/(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)/";
		if (!map.get("BusinessManIdCard")[0].trim().matches(string)
				|| map.get("BusinessManIdCard")[0].trim().length() != 18) {
			errorsMap.put("BusinessManIdCard", "请检查你的身份证号是否输入有误哦");
		}
		if (map.get("BusinessManTel")[0].trim().length() != 11) {
			errorsMap.put("BusinessManTel", "手机号码格式好像不正确呢");
		}
		if (province.equals("---请选择省---")) {
			errorsMap.put("province", "请选择省");
		}
		if (city.equals("")) {
			errorsMap.put("city", "请选择市");
		}
		if (map.get("BusinessManAddrSupplement")[0].trim().length() == 0
				|| map.get("BusinessManAddrSupplement")[0].trim().length() > 50) {
			errorsMap.put("BusinessManAddrSupplement", "请添加详细地址哦，不超过50个字");
		}
		if (errorsMap.size() > 0) {
			request.setAttribute("errors", errorsMap);
			request.getRequestDispatcher("businessmanlogin/businessmanLogin.jsp").forward(request, response);
			return;
		}
		String BusinessManAddr = province + city;
		businessman.setBusinessManAddr(BusinessManAddr);
		businessman.setIsBusinessMan("false");
		try {
			businessManSupplementService.addBusinessMan(businessman);
		} catch (BusinessManSupplementException e) {
			String exceptionString = e.getMessage();
			request.setAttribute("exceptionString", exceptionString);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("<script>alert('hello');</script>");
			request.getRequestDispatcher("businessmanlogin/businessmanLogin.jsp").forward(request, response);
			return;
		}
		request.setAttribute("msg", "您的商家入驻申请已经提交，我们将会在24小时之内审核完成");
		request.getRequestDispatcher("businessmanlogin/msg.jsp").forward(request, response);
		return;
	}
}
