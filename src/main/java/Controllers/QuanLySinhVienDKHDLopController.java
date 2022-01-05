package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import Model.SinhVien;
import Service.QuanLySinhVienDKHDLopService;
import Service.UserService;

/**
 * Servlet implementation class QuanLySinhVienDKHDLopController
 */
@WebServlet("/QuanLySinhVienDKHDLopController")
public class QuanLySinhVienDKHDLopController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuanLySinhVienDKHDLopService quanLySinhVienDKHDLopService = new QuanLySinhVienDKHDLopService();
	
    public QuanLySinhVienDKHDLopController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			getAll_SV_HD(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

		try {
	        switch (action) {
			    case "/QuanLySinhVienDKHDLop/insert":
			    	insert_SV_HD(request, response);
			        break;
			    case "/QuanLySinhVienDKHDLop/delete1":
			    	delete1_SV_HD(request, response);
			        break;
			    case "/QuanLySinhVienDKHDLop/delete2":
			    	delete2_SV_HD(request, response);
			        break;   
			    case "/QuanLySinhVienDKHDLop/update":
			    	update_SV_HD(request, response);
			        break;   
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void getAll_SV_HD(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		String malop = request.getParameter("malop");
		int maHoatDong = Integer.parseInt(request.getParameter("mahd"));
		
		QuanLySinhVienDKHDLopService quanLySinhVienDKHDLopService = new QuanLySinhVienDKHDLopService();
		List<DangKyHoatDong> list = quanLySinhVienDKHDLopService.getAll_HoatDong_SV(maHoatDong, malop);
		
		UserService userService = new UserService();
		List<SinhVien> listSinhViens = userService.getAll_SVLop(malop);
		
		if(session != null) {
			request.setAttribute("listSinhViens", listSinhViens);
			request.setAttribute("list_HD_SV", list);
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("ChiTietHDLop.jsp");
		requestDispatcher.forward(request, response);
	}
	
	private void insert_SV_HD(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		int status = 0;
		String malop = request.getParameter("txtMaLop_insert");
		int maHoatDong = Integer.parseInt(request.getParameter("txtMaHoatDong_insert"));
		
		String tinhTrang = "Đăng ký";
		String maSV = "";
		String[] sv = request.getParameterValues("sv");
        for (String value : sv) {
        	maSV = value;
            status = quanLySinhVienDKHDLopService.Them_SV_HoatDong(maHoatDong, maSV, tinhTrang);
        }
        
        String contextPath = request.getContextPath();
		
		if(status > 0) {
			out.println("<script type='text/javascript'>");
			out.println("alert('Thêm thông tin thành công');");
			out.println("location='" + contextPath + "/QuanLySinhVienDKHDLop?mahd=" + maHoatDong + "&malop=" + malop + "';");
			out.println("</script>");
		}else {
			out.println("<script type='text/javascript'>");
			out.println("alert('Thêm thông tin thành công');");
			out.println("location='" + contextPath + "/QuanLySinhVienDKHDLop?mahd=" + maHoatDong + "&malop=" + malop + "';");
			out.println("</script>");  
		}
	}
	
	private void delete1_SV_HD(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String masv = request.getParameter("masv");
		int maHoatDong = Integer.parseInt(request.getParameter("txtMaHoatDong_delete1"));
		String malop = request.getParameter("txtMaLop_delete1");
		
//		System.out.println(masv + " " + maHoatDong + " " + malop);
		
		int status = quanLySinhVienDKHDLopService.Xoa_SV_HoatDong(maHoatDong, masv);
		
		String contextPath = request.getContextPath();
		
		if(status > 0) {
			out.println("<script type='text/javascript'>");
			out.println("alert('Xóa thông tin thành công');");
			out.println("location='" + contextPath + "/QuanLySinhVienDKHDLop?mahd=" + maHoatDong + "&malop=" + malop + "';");
			out.println("</script>");
		}else {
			out.println("<script type='text/javascript'>");
			out.println("alert('Xóa thông tin không thành công');");
			out.println("location='" + contextPath + "/QuanLySinhVienDKHDLop?mahd=" + maHoatDong + "&malop=" + malop + "';");
			out.println("</script>");  
		}
	}
	
	private void delete2_SV_HD(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		int status = 0;
		String malop = request.getParameter("txtMaLop_delete2");
		int maHoatDong = Integer.parseInt(request.getParameter("txtMaHoatDong_delete2"));
		
		String maSV = "";
		String[] sv = request.getParameterValues("mangxoa");
        for (String value : sv) {
        	maSV = value;
//        	System.out.println(maSV + " " + maHoatDong + " " + malop);
            status = quanLySinhVienDKHDLopService.Xoa_SV_HoatDong(maHoatDong, maSV);
        }
        
        String contextPath = request.getContextPath();
		
		if(status > 0) {
			out.println("<script type='text/javascript'>");
			out.println("alert('Xóa thông tin thành công');");
			out.println("location='" + contextPath + "/QuanLySinhVienDKHDLop?mahd=" + maHoatDong + "&malop=" + malop + "';");
			out.println("</script>");
		}else {
			out.println("<script type='text/javascript'>");
			out.println("alert('Xóa thông tin không thành công');");
			out.println("location='" + contextPath + "/QuanLySinhVienDKHDLop?mahd=" + maHoatDong + "&malop=" + malop + "';");
			out.println("</script>");  
		}
		
	}
	
	private void update_SV_HD(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		int status_thamgia = 0;
		int status_khongthamgia = 0;
		
		String malop = request.getParameter("txtMaLop_update");
		int maHoatDong = Integer.parseInt(request.getParameter("txtMaHoatDong_update"));
		
		String tinhTrangThamGia = "Tham gia";
		String tinhTrangKhongThamGia = "Không tham gia";
		
		String[] svThamGia = request.getParameterValues("mangthamgia");
		if(svThamGia != null) {
	        for (String value_ThamGia : svThamGia) {
	        	if(value_ThamGia != null) {
	                status_thamgia = quanLySinhVienDKHDLopService.Sua_SV_HoatDong(maHoatDong, value_ThamGia, tinhTrangThamGia);
	        	}
	        }
		}
        
        String[] svKhongThamGia = request.getParameterValues("mangkhongthamgia");
        if(svKhongThamGia != null) {
	        for (String value_KhongThamGia : svKhongThamGia) {
	        	if(value_KhongThamGia != null) {
	        		status_khongthamgia = quanLySinhVienDKHDLopService.Sua_SV_HoatDong(maHoatDong, value_KhongThamGia, tinhTrangKhongThamGia);
	        	}
	        }
        }
        
        String contextPath = request.getContextPath();
        
        int status = status_khongthamgia = status_thamgia;
		
		if(status > 0) {
			out.println("<script type='text/javascript'>");
			out.println("alert('Cập nhật thông tin thành công');");
			out.println("location='" + contextPath + "/QuanLySinhVienDKHDLop?mahd=" + maHoatDong + "&malop=" + malop + "';");
			out.println("</script>");
		}else {
			out.println("<script type='text/javascript'>");
			out.println("alert('Cập nhật thông tin không thành công');");
			out.println("location='" + contextPath + "/QuanLySinhVienDKHDLop?mahd=" + maHoatDong + "&malop=" + malop + "';");
			out.println("</script>");  
		}
	}

}
