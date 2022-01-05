package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.GopY;
import Service.QLHomThuGopYService;

/**
 * Servlet implementation class QLHomThuGopYController
 */
@WebServlet("/QLHomThuGopYController")
public class QLHomThuGopYController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	QLHomThuGopYService qlHomThuGopYService = new QLHomThuGopYService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");

		/*
		 * try { List<GopY> gopYs = qlHomThuGopYService.getAll_GopYs(); if (session !=
		 * null) { request.setAttribute("gopYs", gopYs); } } catch (SQLException e) {}
		 */

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("QLHomThuGopY.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		int id = Integer.parseInt(request.getParameter("txtMaGopY"));

		
		try {
			int status = qlHomThuGopYService.delete(id);
			String contextPath = request.getContextPath();

			if (status > 0) {
				out.println("<script type='text/javascript'>");
				out.println("alert('Xóa thông tin thành công');");
				out.println("location='" + contextPath + "/QLHomThuGopY';");
				out.println("</script>");
			} else {
				out.println("<script type='text/javascript'>");
				out.println("alert('Xóa thông tin không thành công);");
				out.println("location='" + contextPath + "/QLHomThuGopY';");
				out.println("</script>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
