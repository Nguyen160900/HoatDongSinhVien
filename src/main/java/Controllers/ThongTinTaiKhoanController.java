package Controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.GiangVien;
import Model.SinhVien;
import Service.ThongTinTaiKhoanService;

@WebServlet("/ThongTinTaiKhoanController")
public class ThongTinTaiKhoanController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ThongTinTaiKhoanController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		switch (action) {
			case "/ThongTinTaiKhoan":
				ThongTinUser(request, response);
				break;
			case "/ThongTinTaiKhoan_admin":
				ThongTinAdmin(request, response);
				break;
		}
		
	}

	public void ThongTinUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		
		ThongTinTaiKhoanService thongTinTaiKhoanService = new ThongTinTaiKhoanService();
		
		try {
			SinhVien sinhVien = thongTinTaiKhoanService.taiKhoan_user(username);
			
			if(session != null) {
				request.setAttribute("sinhVien", sinhVien);
			}
		} catch (SQLException e) {}
		
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("ThongTinTK.jsp");
		requestDispatcher.forward(request, response);
	}
	
	public void ThongTinAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		
		ThongTinTaiKhoanService thongTinTaiKhoanService = new ThongTinTaiKhoanService();
		
		try {
			GiangVien giangVien = thongTinTaiKhoanService.taiKhoan_admin(username);
			
			if(session != null) {
				request.setAttribute("giangVien", giangVien);
			}
		} catch (SQLException e) {}
		
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("ThongTinTK_AD.jsp");
		requestDispatcher.forward(request, response);
	}
}
