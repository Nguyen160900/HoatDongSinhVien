package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.formula.functions.T;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import Model.GopY;
import Model.HoatDong;
import Model.SinhVien;


import Service.GopYService;
import Service.UserService;


@WebServlet("/GopYController")
public class GopYController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	GopYService gopYService = new GopYService();
	UserService userService = new UserService();
  
    public GopYController() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
				
		try {
			List<GopY> gopYs = gopYService.getAll_GopYs(username);
			SinhVien sinhVien = userService.ThongTinDangNhap(username);
			
			if(session != null) {
				request.setAttribute("gopYs", gopYs);
				request.setAttribute("sinhVien", sinhVien);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("HomThuGopY.jsp");
		requestDispatcher.forward(request, response);		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			    case "/HomThuGopY_insert":
			    	insert(request, response);
			        break;
		 }
		}catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}


	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		/*
		 * LocalTime current = LocalTime.now(); DateTimeFormatter formatter =
		 * DateTimeFormatter.ofPattern("HH:mm:ss"); String m =
		 * current.format(formatter);
		 */
		
		LocalDateTime current = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String d = current.format(formatter);
		

		
		String maSinhVien = request.getParameter("txtMaSV");
		String tieuDe = request.getParameter("txtTieuDe");
		String noiDung = request.getParameter("txtNoiDung");
		Date ngayGopY= Date.valueOf(d.substring(0, 10)) ;
		Time gioGopY = Time.valueOf(d.substring(11, 19));
		/* Time gioGopY = Time.valueOf(m); */
		
		int status = gopYService.insert(maSinhVien, ngayGopY, gioGopY, tieuDe, noiDung);
		
		String contextPath = request.getContextPath();
		
		if(status > 0) {
			out.println("<script type='text/javascript'>");
			out.println("alert('Gửi thành công');");
			out.println("location='" + contextPath + "/HomThuGopY';");
			out.println("</script>");
		}else {
			out.println("<script type='text/javascript'>");
			out.println("alert('Lỗi! Gửi không thành công');");
			out.println("location='" + contextPath + "/HomThuGopY';");
			out.println("</script>");  
		}
		
	}
	
}
