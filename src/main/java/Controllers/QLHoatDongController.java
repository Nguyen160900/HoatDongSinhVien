package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
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
import Service.HoatDongService;
import Service.QLHoatDongService;

/**
 * Servlet implementation class QLHoatDongController
 */
@WebServlet("/QLHoatDongController")
public class QLHoatDongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	QLHoatDongService qlHoatDongService = new QLHoatDongService();
	HoatDongService hoatDongService = new HoatDongService();

    public QLHoatDongController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

        switch (action) {
		    case "/QLHoatDong":
		    	QLHoatDong(request, response);
		        break;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

		try {
	        switch (action) {
			    case "/QLHoatDong/insert":
					insert(request, response);
			        break;
			    case "/QLHoatDong/delete":
			        delete(request, response);
			        break;
			    case "/QLHoatDong/update":
			    	update(request, response);
			    	break;
			    case "/QuanLyHoatDong_TimKiem":
			    	tim(request, response);
			    	break;
			    case "/QLHoatDong_LamMoi":
			    	LamMoi(request, response);
			    	break;
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void QLHoatDong(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		
		List<HoatDong> dsHoatDongs = qlHoatDongService.getAllHoatDong();
		
		if(session != null) {
			request.setAttribute("dsHoatDongs", dsHoatDongs);
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("QLHoatDong.jsp");
		requestDispatcher.forward(request, response);
	}
	
	private void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String tenHoatDongString = request.getParameter("txtTenHoatDong");
		Date dateNgayDienRa = Date.valueOf(request.getParameter("dateNgayDienRa"));
		String thoiGianDienRaString = request.getParameter("cboThoiGianDienRa");
		String namHocString = request.getParameter("cboNamHoc");
		int hocKy = Integer.parseInt(request.getParameter("cboHocKy"));
		String capDienRaString = request.getParameter("cboCapDienRa");
		int soLuong = Integer.parseInt(request.getParameter("txtSoLuong"));
		String fileString = request.getParameter("Filedrive");
		String noiDungString = request.getParameter("txtNoiDung");
		
		String khoahoc = "";
		String[] ckbKhoaThamGia = request.getParameterValues("ckbKhoaThamGia");
        for (String value : ckbKhoaThamGia) {
            khoahoc = khoahoc + " - " + value;
        }
        
        String khoaThamGia = "";
        if(khoahoc.contains(" - ")) {
        	khoaThamGia = khoahoc.substring(3);
        }else {
        	khoaThamGia = khoahoc;
        }
		
		int status = qlHoatDongService.insert(tenHoatDongString, dateNgayDienRa, thoiGianDienRaString, noiDungString, namHocString, hocKy, capDienRaString, khoaThamGia, soLuong, fileString);
		
		String contextPath = request.getContextPath();
		
		if(status > 0) {
			out.println("<script type='text/javascript'>");
			out.println("alert('Thêm hoạt động thành công');");
			out.println("location='" + contextPath + "/QLHoatDong';");
			out.println("</script>");
		}else {
			out.println("<script type='text/javascript'>");
			out.println("alert('Thêm hoạt động không thành công');");
			out.println("location='" + contextPath + "/QLHoatDong';");
			out.println("</script>");  
		}
		
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		
		int mahoatdong = Integer.parseInt(request.getParameter("txtMaHoatDong3"));
		
		int status = qlHoatDongService.delete(mahoatdong);
		
		String contextPath = request.getContextPath();
		
		if(status > 0) {
			out.println("<script type='text/javascript'>");
			out.println("alert('Xóa hoạt động thành công');");
			out.println("location='" + contextPath + "/QLHoatDong';");
			out.println("</script>");
		}else {
			out.println("<script type='text/javascript'>");
			out.println("alert('Xóa hoạt động không thành công');");
			out.println("location='" + contextPath + "/QLHoatDong';");
			out.println("</script>");  
		}
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		int mahoatdong = Integer.parseInt(request.getParameter("txtMaHoatDong1"));
		String tenHoatDongString = request.getParameter("txtTenHoatDong1");
		Date dateNgayDienRa = Date.valueOf(request.getParameter("dateNgayDienRa1"));
		String thoiGianDienRaString = request.getParameter("cboThoiGianDienRa1");
		String namHocString = request.getParameter("cboNamHoc1");
		int hocKy = Integer.parseInt(request.getParameter("cboHocKy1"));
		String capDienRaString = request.getParameter("cboCapDienRa1");
		int soLuong = Integer.parseInt(request.getParameter("txtSoLuong1"));
		String fileString = request.getParameter("Filedrive1");
		String noiDungString = request.getParameter("txtNoiDung1");
		
		String khoahoc = "";
		String[] ckbKhoaThamGia = request.getParameterValues("ckbKhoaThamGia1");
        for (String value : ckbKhoaThamGia) {
            khoahoc = khoahoc + " - " + value;
        }
        
        String khoaThamGia = "";
        if(khoahoc.contains(" - ")) {
        	khoaThamGia = khoahoc.substring(3);
        }else {
        	khoaThamGia = khoahoc;
        }
		
		int status = qlHoatDongService.update(mahoatdong, tenHoatDongString, dateNgayDienRa, thoiGianDienRaString, noiDungString, namHocString, hocKy, capDienRaString, khoaThamGia, soLuong, fileString);
		
		String contextPath = request.getContextPath();
		
		if(status > 0) {
			out.println("<script type='text/javascript'>");
			out.println("alert('Cập nhật hoạt động thành công');");
			out.println("location='" + contextPath + "/QLHoatDong';");
			out.println("</script>");
		}else {
			out.println("<script type='text/javascript'>");
			out.println("alert('Cập nhật hoạt động không thành công');");
			out.println("location='" + contextPath + "/QLHoatDong';");
			out.println("</script>");  
		}
	}

	private void tim(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String namhoc = request.getParameter("cboNamHocLoc");
		int hocky = Integer.parseInt(request.getParameter("cboHocKyLoc"));

		List<HoatDong> hoatDongs = qlHoatDongService.TimKiem(namhoc, hocky);
		
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			request.setAttribute("hoatDongs", hoatDongs);
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("QLHoatDong.jsp");
		requestDispatcher.forward(request, response);
	}
	
	private void LamMoi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String contextPath = request.getContextPath();
		PrintWriter out = response.getWriter();
		
		out.println("<script type='text/javascript'>");
		out.println("location='" + contextPath + "/QLHoatDong';");
		out.println("</script>"); 
	}

}
