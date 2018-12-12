package com.nonglianwang.verifier.veriferservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonglianwang.verifier.verifierService.VeriferService;

/**
 * Servlet implementation class PassOrNotServlet
 */
@WebServlet("/PassOrNotServlet")
public class PassOrNotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VeriferService veriferservice= new VeriferService();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String uno=req.getParameter("uno");
		String action=req.getParameter("action");
		if (action.equals("pass")) {
			boolean flag=veriferservice.pass(uno);
			if (flag) {
				resp.setContentType("text/html;charset=UTF-8");
				resp.getWriter().write("<script>alert('通过成功');window.history.back();window.location.reload();</script>");
				return;
			}else {
				resp.setContentType("text/html;charset=UTF-8");
				resp.getWriter().write("<script>alert('出错了');window.history.back();window.location.reload();</script>");
				return;
			}
		}
		if (action.equals("delete")) {
			boolean flag1=veriferservice.delete(uno);
			if (flag1) {
				resp.setContentType("text/html;charset=UTF-8");
				resp.getWriter().write("<script>alert('删除成功');window.history.back();window.location.reload();</script>");
				return;
			}else {
				resp.setContentType("text/html;charset=UTF-8");
				resp.getWriter().write("<script>alert('出错了');window.history.back();window.location.reload();</script>");
				return;
			}
			
		}
		if (!action.equals("delete")||!action.equals("pass")) {
			resp.setContentType("text/html;charset=UTF-8");
			resp.getWriter().write("<script>alert('出错了');window.history.back();window.location.reload();</script>");
			return;
		}
	}

}
