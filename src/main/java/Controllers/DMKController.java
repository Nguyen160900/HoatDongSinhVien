package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.DMKService;

@WebServlet("/DMKController")
public class DMKController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DMKController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		switch (action) {
			case "/DoiMatKhau":
				RequestDispatcher rDispatcher = request.getRequestDispatcher("DoiMatKhau.jsp");
				rDispatcher.forward(request, response);
				break;
			case "/DoiMatKhau_admin":
				RequestDispatcher rDispatcher_admin = request.getRequestDispatcher("DoiMatKhauAD.jsp");
				rDispatcher_admin.forward(request, response);
				break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		switch (action) {
			case "/DoiMatKhau_update":
				DMK_User(request, response);
				break;
			case "/DoiMatKhau_admin_update":
				DMK_Admin(request, response);
				break;
		}
	}
	
	private void DMK_User(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("txtTenDangNhap");
		String password = request.getParameter("txtNhapMKCu");
		String newpassword = request.getParameter("txtNhapMKMoi");
		String repassword = request.getParameter("txtNhapLaiMK");
		
		DMKService service = new DMKService();
		
		boolean ktra = service.CheckMK(username, password) ;
	
		String contextPath = request.getContextPath();
		
		if(ktra) {
			if(newpassword.compareTo(repassword)!=0) {
				out.println("<script type='text/javascript'>");
				out.println("alert('Nhập lại mật khẩu và mật khẩu mới không khớp');");
				out.println("location='" + contextPath + "/DoiMatKhau';");
				out.println("</script>"); 
				
			}
			else {
				int status = 0;
				status = service.DoiMatKhau(username, newpassword);
				if(status > 0) {
					out.println("<script type='text/javascript'>");
					out.println("alert('Đổi mật khẩu thành công');");
					out.println("location='" + contextPath + "/DoiMatKhau';");
					out.println("</script>"); 
				}
				else {
					out.println("<script type='text/javascript'>");
					out.println("alert('Đổi mật khẩu không thành công');");
					out.println("location='" + contextPath + "/DoiMatKhau';");
					out.println("</script>"); 
				}
			}
		}
		else {
			out.println("<script type='text/javascript'>");
			out.println("alert('Sai mật khẩu cũ');");
			out.println("location='" + contextPath + "/DoiMatKhau';");
			out.println("</script>"); 
		}
	}
	
	private void DMK_Admin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("txtTenDangNhap");
		String password = request.getParameter("txtNhapMKCu");
		String newpassword = request.getParameter("txtNhapMKMoi");
		String repassword = request.getParameter("txtNhapLaiMK");
		
		DMKService service = new DMKService();
		
		boolean ktra = service.CheckMK_Admin(username, password) ;
	
		String contextPath = request.getContextPath();
		
		if(ktra) {
			if(newpassword.compareTo(repassword)!=0) {
				out.println("<script type='text/javascript'>");
				out.println("alert('Nhập lại mật khẩu và mật khẩu mới không khớp');");
				out.println("location='" + contextPath + "/DoiMatKhau_admin';");
				out.println("</script>"); 
				
			}
			else {
				int status = 0;
				status = service.DoiMatKhau_AD(username, newpassword);
				if(status > 0) {
					out.println("<script type='text/javascript'>");
					out.println("alert('Đổi mật khẩu thành công');");
					out.println("location='" + contextPath + "/DoiMatKhau_admin';");
					out.println("</script>"); 
				}
				else {
					out.println("<script type='text/javascript'>");
					out.println("alert('Đổi mật khẩu không thành công');");
					out.println("location='" + contextPath + "/DoiMatKhau_admin';");
					out.println("</script>"); 
				}
			}
		}
		else {
			out.println("<script type='text/javascript'>");
			out.println("alert('Sai mật khẩu cũ');");
			out.println("location='" + contextPath + "/DoiMatKhau_admin';");
			out.println("</script>"); 
		}
	}

}
