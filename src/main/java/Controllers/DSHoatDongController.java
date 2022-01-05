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

import Model.DSHoatDong;
import Model.HoatDong;
import Service.DSHoatDongService;
import Service.HoatDongService;

@WebServlet("/DSHoatDongController")
public class DSHoatDongController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DSHoatDongService dsHoatDongService = new DSHoatDongService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/DanhSachHoatDong":
				DSDangKyHoatDong(request, response);
				break;
			case "/DanhSachHoatDong/LamMoi":
				LamMoi(request, response);
				break;
			default:
				break;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * break; } } catch (SQLException ex) { throw new ServletException(ex); }
		 */
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		
		String namhoc = request.getParameter("cboNamHoc");
		int hocky = Integer.parseInt(request.getParameter("cboHocKy"));
		
		try {
			List<DSHoatDong> dsHoatDongs = dsHoatDongService.TimKiem(username, namhoc, hocky);
			if (session != null) {
				request.setAttribute("dsHoatDongs_TimKiem", dsHoatDongs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("DSHoatDong.jsp");
		requestDispatcher.forward(request, response);
	}

	private void DSDangKyHoatDong(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");

		List<DSHoatDong> dsHoatDongs = dsHoatDongService.getAllHDDangky(username);

		if (session != null) {
			request.setAttribute("dsHoatDongs", dsHoatDongs);
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("DSHoatDong.jsp");
		requestDispatcher.forward(request, response);
	}

	private void LamMoi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String contextPath = request.getContextPath();
		PrintWriter out = response.getWriter();

		out.println("<script type='text/javascript'>");
		out.println("location='" + contextPath + "/DanhSachHoatDong';");
		out.println("</script>");
	}

}
