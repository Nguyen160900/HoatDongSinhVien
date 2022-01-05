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

import Service.AdminService;
import Service.UserService;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rDispatcher = request.getRequestDispatcher("index.jsp");
		rDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

		try {
	        switch (action) {
			    case "/LoginAdmin":
					LoginAdmin(request, response);
			        break;
			    case "/LoginUser":
			        LoginUser(request, response);
			        break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		String username = request.getParameter("Username");
//		String password = request.getParameter("Password");
//		
//		UserService service = new UserService();
//		
//		boolean dangnhap = service.checkLogin(username, password);
//		
//		if(dangnhap)
//		{
//			HttpSession session = request.getSession();
//			session.setAttribute("username", username);
//			response.sendRedirect("DKHoatDong");
//		}
//		else {
//			response.sendRedirect("Login?err=1");
//		}
	}
	
	private void LoginUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("Username");
		String password = request.getParameter("Password");
		
		UserService service = new UserService();
		
		boolean dangnhap = service.checkLogin(username, password);
		
		if(dangnhap)
		{
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			response.sendRedirect("DKHoatDong");
		}
		else {
			response.sendRedirect("LoginUser?err=1");
			 
		}
	}
	
	private void LoginAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("UsernameAD");
		String password = request.getParameter("PasswordAD");
		
		AdminService service = new AdminService();	
		
		boolean dangnhap = service.checkLogin(username, password);
		
		if(dangnhap)
		{
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			response.sendRedirect("QLHoatDong");
		}
		else {
			response.sendRedirect("LoginAdmin?err2=1");
		}
	}

}
