<%@page import="Model.SinhVien"%>
<%@page import="Service.UserService"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-xl bg-primary navbar-dark sticky-top">
	<button class="navbar-toggler justify-content-end" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="collapsibleNavbar" style="justify-content: center;">
		<ul class="navbar-nav justify-content-center">
			<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/DKHoatDong">Đăng ký hoạt động</a></li>
			<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/DanhSachHoatDong">Danh sách hoạt động</a></li>
			
			<%
				String username = (String) session.getAttribute("username");
				int quyen = new UserService().QuyenDangNhap(username);
				UserService thongTin = new UserService();
				SinhVien sv = thongTin.ThongTinDangNhap(username);
				String hienThiDangNhap = sv.getHoTen() + " [" + sv.getMaSinhVien() + "]";
				
				if(quyen == 0) {
					out.print("<li class='nav-item active'><a class='nav-link' href='./QuanLyHoatDongLop'>Quản lý hoạt động lớp</a></li>");
				}
			%>
			
			<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/HomThuGopY">Hòm thư góp ý</a></li>
			
			<% if(username != null) { %>
	       	<li class="nav-item dropdown active">
				<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown"><% out.print(hienThiDangNhap); %></a>
				<div class="dropdown-menu bg-light">
					<a class="dropdown-item" href="${pageContext.request.contextPath}/ThongTinTaiKhoan">Thông tin đăng nhập</a> 
					<a class="dropdown-item" href="${pageContext.request.contextPath}/DoiMatKhau">Đổi mật khẩu</a> 
					<a class="dropdown-item" href="${pageContext.request.contextPath}/Logout">Đăng xuất</a>
				</div>
			</li>
	    	<% } else{ %>
	       	<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/Login">Đăng nhập</a></li> 
			<% } %>  
		</ul>
	</div>
</nav>