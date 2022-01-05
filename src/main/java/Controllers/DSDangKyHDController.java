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
import javax.websocket.Session;

import Model.DangKyHoatDong;
import Model.HoatDong;
import Model.Lop;
import Service.DSDangKyHDService;
import Service.HoatDongService;
import Service.LopService;

@WebServlet("/DSDangKyHDController")
public class DSDangKyHDController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    HoatDongService hoatDongService = new HoatDongService();
    DSDangKyHDService dsDangKyHDService = new DSDangKyHDService();
    LopService lopService =  new LopService();
	
    public DSDangKyHDController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

		try {
	        switch (action) {
			    case "/DSDangKyHD":
					DSDangKyHD(request, response);
			        break;
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		try {
	        switch (action) {
	        case "/DSDangKyHD_LamMoi":
				LamMoi(request, response);
		    	break; 
		    case "/DSDangKyHD/delete":
		    	delete(request, response);
		    	break;
		    case "/DSDangKyHD/update":
		    	update(request, response);
		    	break;
		    case "/DSDangKyHD_thamgia":
		    	CapNhat_ThamGia(request, response);
		    	break;
		    case "/DSDangKyHD_khongthamgia":
		    	 CapNhat_KhongThamGia(request, response);
		    	break;
		    case "/DSDangKyHD_xoa":
		    	Xoa_SV(request, response);
		    	break;
			case "/DSDangKyHD_Loc":
		    	LocDuLieu(request, response);
		    	break;
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void DSDangKyHD(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		
		int maHoatDong = Integer.parseInt(request.getParameter("maHoatDong"));
		
		HoatDong hoatDong = hoatDongService.getHoatDong(maHoatDong);
		
		List<DangKyHoatDong> dsDangKyHoatDongs = dsDangKyHDService.getAll_HoatDong_SV(maHoatDong);
		
		int soLuong = dsDangKyHDService.getAll_SoLuong(maHoatDong);
		
		String contextPath = request.getContextPath();
		
		if(session != null) {
			request.setAttribute("hoatDong", hoatDong);
			request.setAttribute("dsDangKyHoatDongs", dsDangKyHoatDongs);
			request.setAttribute("soLuong", soLuong);
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("DSDangKyHD.jsp");
		requestDispatcher.forward(request, response);		
	}
	
	private void LocDuLieu(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		
		int maHoatDong = Integer.parseInt(request.getParameter("txtMaHDloc"));
		
		String maLop = request.getParameter("cboLop");
		String maKhoaHoc = request.getParameter("cboKhoaHoc");
		String tinhTrang = request.getParameter("cboTinhTrang_loc");
		
		HoatDong hoatDong = hoatDongService.getHoatDong(maHoatDong);
		
		List<DangKyHoatDong> dsDangKyHoatDongs_loc = dsDangKyHDService.LocDuLieu(maLop, maKhoaHoc, tinhTrang, maHoatDong);
		
		int soLuong_loc = dsDangKyHDService.LoadSoLuong(maLop, maKhoaHoc, tinhTrang, maHoatDong);
		
		if(session != null) {
			request.setAttribute("hoatDong", hoatDong);
			request.setAttribute("dsDangKyHoatDongs_loc", dsDangKyHoatDongs_loc);
			request.setAttribute("soLuong_loc", soLuong_loc);
		}
		
		PrintWriter out = response.getWriter();
		
		String contextPath = request.getContextPath();
		
		out.println("<script type='text/javascript'>");
		out.println("location='" + contextPath + "/DSDangKyHD?maHoatDong="+ maHoatDong +" ';");
		out.println("</script>");
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("DSDangKyHD.jsp?maHoatDong="+ maHoatDong +"");
		requestDispatcher.forward(request, response);	
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		
		int maHoatDong = Integer.parseInt(request.getParameter("txtMaHD_Xoa"));
		String maSinhVien = request.getParameter("txtMasv");
		
		int status = dsDangKyHDService.delete(maHoatDong, maSinhVien);
		
		String contextPath = request.getContextPath();
		
		if(status > 0) {
			out.println("<script type='text/javascript'>");
			out.println("alert('Xóa thành công');");
			out.println("location='" + contextPath + "/DSDangKyHD?maHoatDong="+ maHoatDong +" ';");
			out.println("</script>");
		}else {
			out.println("<script type='text/javascript'>");
			out.println("alert('Lỗi! Xóa không thành công');");
			out.println("location='" + contextPath + "/DSDangKyHD?maHoatDong="+ maHoatDong +" ';");
			out.println("</script>");  
		}
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		int maHoatDong = Integer.parseInt(request.getParameter("txtMaHD_Capnhat"));
		String maSinhVien = request.getParameter("txtMaSinhVien");
		String tinhTrang = request.getParameter("cboTinhTrang");
		
		int status = dsDangKyHDService.update(maHoatDong, maSinhVien, tinhTrang);
		
		String contextPath = request.getContextPath();
		
		if(status > 0) {
			out.println("<script type='text/javascript'>");
			out.println("alert('Cập nhật thành công');");
			out.println("location='" + contextPath + "/DSDangKyHD?maHoatDong="+ maHoatDong +" ';");
			out.println("</script>");
		}else {
			out.println("<script type='text/javascript'>");
			out.println("alert('Lỗi! Cập nhật không thành công');");
			out.println("location='" + contextPath + "/DSDangKyHD?maHoatDong="+ maHoatDong +" ';");
			out.println("</script>");  
		}
	}
	
	private void LamMoi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String contextPath = request.getContextPath();
		PrintWriter out = response.getWriter();
		
		int maHoatDong = Integer.parseInt(request.getParameter("txtMaHD"));
		out.println("<script type='text/javascript'>");
		out.println("location='" + contextPath + "/DSDangKyHD?maHoatDong="+ maHoatDong +" ';");
		out.println("</script>"); 
	}
	
	private void CapNhat_ThamGia(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int status = 0;
		
		PrintWriter out = response.getWriter();
		
		
		  int maHoatDong = Integer.parseInt(request.getParameter("txtMaHD1")); 
		  String maSinhVien = ""; String[] mangthamgia = request.getParameterValues("mangthamgia"); 
		  for(String value:mangthamgia) {
			  maSinhVien = value; status = dsDangKyHDService.update_thamgia(maHoatDong, maSinhVien); 
		  }
			 
		
		String contextPath = request.getContextPath();
		
		if(status > 0) {
			out.println("<script type='text/javascript'>");
			out.println("alert('Cập nhật thành công');");
			out.println("location='" + contextPath + "/DSDangKyHD?maHoatDong="+ maHoatDong +" ';");
			out.println("</script>");
		}else {
			out.println("<script type='text/javascript'>");
			out.println("alert('Lỗi! Cập nhật không thành công');");
			out.println("location='" + contextPath + "/DSDangKyHD?maHoatDong="+ maHoatDong +" ';");
			out.println("</script>");  
		}
	}
	
	private void CapNhat_KhongThamGia(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int status = 0;
		
		PrintWriter out = response.getWriter();
		
		
		  int maHoatDong = Integer.parseInt(request.getParameter("txtMaHD1")); 
		  String maSinhVien = ""; 
		  String[] mangthamgia = request.getParameterValues("mangthamgia"); 
		  for(String value:mangthamgia) {
			  maSinhVien = value; 
			  status = dsDangKyHDService.update_khongthamgia(maHoatDong, maSinhVien); 
		  }
			 
		String contextPath = request.getContextPath();
		
		if(status > 0) {
			out.println("<script type='text/javascript'>");
			out.println("alert('Cập nhật không thành công');");
			out.println("location='" + contextPath + "/DSDangKyHD?maHoatDong="+ maHoatDong +" ';");
			out.println("</script>");
		}else {
			out.println("<script type='text/javascript'>");
			out.println("alert('Lỗi! Cập nhật không thành công');");
			out.println("location='" + contextPath + "/DSDangKyHD?maHoatDong="+ maHoatDong +" ';");
			out.println("</script>");  
		}
	}
		
		private void Xoa_SV(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			int status = 0;
			
			PrintWriter out = response.getWriter();
			
			
			  int maHoatDong = Integer.parseInt(request.getParameter("txtMaHD1")); 
			  String maSinhVien = ""; 
			  String[] mangthamgia = request.getParameterValues("mangthamgia"); 
			  for(String value:mangthamgia) {
				  maSinhVien = value; 
				  status = dsDangKyHDService.delete(maHoatDong, maSinhVien); 
			  }
				 
			String contextPath = request.getContextPath();
			
			if(status > 0) {
				out.println("<script type='text/javascript'>");
				out.println("alert('Xóa thành công');");
				out.println("location='" + contextPath + "/DSDangKyHD?maHoatDong="+ maHoatDong +" ';");
				out.println("</script>");
			}else {
				out.println("<script type='text/javascript'>");
				out.println("alert('Lỗi! Xóa không thành công');");
				out.println("location='" + contextPath + "/DSDangKyHD?maHoatDong="+ maHoatDong +" ';");
				out.println("</script>");  
			}
	}
	
}
