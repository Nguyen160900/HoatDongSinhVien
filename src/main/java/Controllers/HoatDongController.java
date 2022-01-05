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

import Model.DangKyHoatDong;
import Model.HoatDong;
import Service.DKHoatDongService;
import Service.HoatDongService;

@WebServlet("/HoatDongController")
public class HoatDongController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HoatDongController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

        try {
            switch (action) {
                case "/DKHoatDong/DangKyHoatDong_1":
                	DangKyHoatDong_1(request, response);
                    break;
                case "/DKHoatDong/DangKyHoatDong_2":
                	DangKyHoatDong_2(request, response);
                    break;
                default:
                    DanhSachHoatDong(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void DanhSachHoatDong(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		try {
			HttpSession session = request.getSession(false);
			String username = (String) session.getAttribute("username");
			
			HoatDongService hoatDongService = new HoatDongService();
			
			long millis = System.currentTimeMillis();  
			java.sql.Date ngayHienTai = new java.sql.Date(millis);
			
			List<HoatDong> hoatDongs = hoatDongService.getHoatDong_LocHoatDong(ngayHienTai);
			
			if(session != null)
			{
				request.setAttribute("hoatDongs", hoatDongs);
			}			
		} catch (Exception e) {}
		
		RequestDispatcher rd = request.getRequestDispatcher("DKHoatDong.jsp");
		rd.forward(request, response);
	}
	
	private void DangKyHoatDong_1(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		
		int maHoatDong_1 = Integer.parseInt(request.getParameter("maHoatDong_1"));
		
		String tinhTrang = "Đăng ký";
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int status = HoatDongService.DangKy(username, maHoatDong_1, tinhTrang);
		
		String contextPath = request.getContextPath();
			
		if(status > 0)
		{
			out.println("<script type='text/javascript'>");
			out.println("alert('Đăng ký hoạt động thành công');");
			out.println("location='" + contextPath + "/DKHoatDong';");
			out.println("</script>"); 

		}else {
			out.println("<script type='text/javascript'>");
			out.println("alert('Đăng ký hoạt động không thành công');");
			out.println("location='" + contextPath + "/DKHoatDong';");
			out.println("</script>");  
		}
	}
	
	private void DangKyHoatDong_2(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		
		int maHoatDong_2 = Integer.parseInt(request.getParameter("maHoatDong_2"));
		
		String tinhTrang = "Đăng ký";
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int status = HoatDongService.DangKy(username, maHoatDong_2, tinhTrang);
			
		String contextPath = request.getContextPath();
		
		if(status > 0)
		{
			out.println("<script type='text/javascript'>");
			out.println("alert('Đăng ký hoạt động thành công');");
			out.println("location='" + contextPath + "/DKHoatDong';");
			out.println("</script>");  
		}else {
			out.println("<script type='text/javascript'>");
			out.println("alert('Đăng ký hoạt động không thành công');");
			out.println("location='" + contextPath + "/DKHoatDong';");
			out.println("</script>");
		}
	}
}
