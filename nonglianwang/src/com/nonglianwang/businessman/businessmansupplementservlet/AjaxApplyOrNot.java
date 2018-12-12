package com.nonglianwang.businessman.businessmansupplementservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonglianwang.businessman.businessmansupplementService.BusinessManSupplementService;

/**
 * Servlet implementation class AjaxApplyOrNot
 */
@WebServlet("/AjaxApplyOrNotServlet")
public class AjaxApplyOrNot extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BusinessManSupplementService businessManSupplementService=new BusinessManSupplementService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String nameString=request.getParameter("name");
		boolean flagString=businessManSupplementService.applyOrNot(nameString);
		if (!flagString) {
			response.setContentType("text/plain;charset=UTF-8");
			response.getWriter().write("你已经申请过了，请耐心等待审核哦~~~");
			return;
		}
		else{
			response.setContentType("text/plain;charset=UTF-8");
			response.getWriter().write("");
			return;
		}

	}
}
