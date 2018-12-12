package com.nonglianwang.verifier.veriferservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonglianwang.verifier.verifierService.VeriferService;

/**
 * Servlet implementation class VerifierServlet
 */
@WebServlet("/VerifierServlet")
public class VerifierBusinessman extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private VeriferService veriferService = new VeriferService();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int count = veriferService.CountNeedVeriferBusinessman();
		req.setAttribute("count",count);
		req.getRequestDispatcher("veriferBusiness/veriferBusiness.jsp").forward(req, resp);
		return;
	}
}
