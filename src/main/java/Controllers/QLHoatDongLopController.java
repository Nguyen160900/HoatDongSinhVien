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

import Model.HoatDong;
import Service.QLHoatDongLopService;

/**
 * Servlet implementation class QLHoatDongLopController
 */
@WebServlet("/QLHoatDongLopController")
public class QLHoatDongLopController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	QLHoatDongLopService qlHoatDongLopService = new QLHoatDongLopService();

    public QLHoatDongLopController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		
		try {
	        switch (action) {
			    case "/QuanLyHoatDongLop":
			    	getAll_HoatDong(request, response);
			        break;
			    case "/QuanLyHoatDongLop/LamMoi":
			    	LamMoi(request, response);
			        break;
			}
		} catch (SQLException ex) {
            throw new ServletException(ex);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		String namhoc = request.getParameter("cboNamHoc");
		int hocky = Integer.parseInt(request.getParameter("cboHocKy"));
		
		try {
			List<HoatDong> list_HoatDongs = qlHoatDongLopService.TimKiem(namhoc, hocky);
			if(session != null) {
				request.setAttribute("list_TimKiems", list_HoatDongs);
			}
		} catch (SQLException e) { }
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("QLHoatDongLop.jsp");
		requestDispatcher.forward(request, response);
	}
	
	private void getAll_HoatDong(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		
		try {
			List<HoatDong> list_HoatDongs = qlHoatDongLopService.getAllHoatDong();
			
			if(session != null) {
				request.setAttribute("list_HoatDongs", list_HoatDongs);
			}
		} catch (SQLException e) { }
		
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("QLHoatDongLop.jsp");
		requestDispatcher.forward(request, response);
	}
	
	private void LamMoi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String contextPath = request.getContextPath();
		PrintWriter out = response.getWriter();
		
		out.println("<script type='text/javascript'>");
		out.println("location='" + contextPath + "/QuanLyHoatDongLop';");
		out.println("</script>"); 
	}

}
