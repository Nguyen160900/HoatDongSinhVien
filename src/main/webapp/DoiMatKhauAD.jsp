<%@page import="Model.HoatDong"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đổi mật khẩu</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/style/style-DangNhap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/style/style-DoiMK.css">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/style/style-footer.css">
</head>
<style>
  	.jumbotron{
	    background-image: linear-gradient(to bottom right, #1E90FF, #FFFFFF); 
	    display: flex;
	}
 </style>
<body>
	<!-- Đầu trang -->
    <div class="jumbotron text-center" style="margin-bottom:0; padding: 20px">

        <%@ include file="../BoCuc/Top.jsp"%> 

    </div>

    <!-- /* menu của trang */ -->
	<%@ include file="../BoCuc/MenuAD.jsp"%>
	
	 <!--  /* thân của trang */ -->
	 <div class="container-fluid" style="margin-top:30px">
	 	<div class="row">
	 		<div class="col-sm-1"></div>
	 		<div class="col-sm-10">
					<div class="formDMK container">
					<%
                		if(username != null)
                		{
                	%>
						<h3>ĐỔI MẬT KHẨU</h3>
						<form action="<%=request.getContextPath()%>/DoiMatKhau_admin_update" method="POST">
							<div class="form-group text-ip">
			                    <input type="text" class="form-control" style="pointer-events: none;" placeholder="Tên đăng nhập" name="txtTenDangNhap" required value="<%=username%>">
			                    <div class="valid-feedback">Hợp lệ.</div>
			                    <div class="invalid-feedback">Vui lòng không được bỏ trống thông tin!</div>
			                </div>
							<div class="form-group text-ip">
								<input type="password" class="form-control" name="txtNhapMKCu" id="NhapMKCu" placeholder="Nhập mật khẩu cũ" required></input>
								<div class="valid-feedback">Hợp lệ.</div>
								<div class="invalid-feedback">Vui lòng không được bỏ trống thông tin!</div>
							</div>
							<div class="form-group text-ip">
								<input type="password" class="form-control" name="txtNhapMKMoi" id="NhapMKMoi" placeholder="Nhập mật khẩu mới" required></input>
								<div class="valid-feedback">Hợp lệ.</div>
								<div class="invalid-feedback">Vui lòng không được bỏ trống thông tin!</div>
							</div>
							<div class="form-group text-ip">
								<input type="password" class="form-control" name="txtNhapLaiMK" id="NhapLaiMK" placeholder="Nhập lại mật khẩu" required></input>
								<div class="valid-feedback">Hợp lệ.</div>
								<div class="invalid-feedback">Vui lòng không được bỏ trống thông tin!</div>
							</div>
							
							<div class="btn-grp">
								<button type="submit" name="btn_DoiMatKhau" class="btn btn-primary">
									<b>ĐỔI MẬT KHẨU</b>
								</button>
							</div>
							<br>
							
						</form>
						
						<% } %>
						
					</div>
	 		</div>
	 		<div class="col-sm-1"></div>
	 	</div>
	 </div>
	<!-- /* chân trang */ -->
     <div class="jumbotron text-center" style="margin-bottom:0">

        <%@ include file="../BoCuc/Footer.jsp"%> 

    </div>
</body>
</html>