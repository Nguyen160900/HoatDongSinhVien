<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin tài khoản</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resource/style/style-footer.css">
<style>
.jumbotron {
	background-image: linear-gradient(to bottom right, #1E90FF, #FFFFFF);
	display: flex;
}
</style>
</head>
<body>

	<!-- Đầu trang -->
	<div class="jumbotron text-center"
		style="margin-bottom: 0; padding: 20px">

		<%@ include file="../BoCuc/Top.jsp"%>

	</div>

	<!-- /* menu của trang */ -->
	<%@ include file="../BoCuc/Menu.jsp"%>

	<!--  /* thân của trang */ -->
	<div class="container-fluid" style="margin-top: 30px">
		<div class="row">
			<div class="col-sm-1"></div>

			<div class="col-sm-10">
				<%
               		if(username != null)
               		{
               			SinhVien sinhVien = (SinhVien)request.getAttribute("sinhVien");
               		
               	%>
				<div class="container">
					<h3 style="text-align: center">THÔNG TIN TÀI KHOẢN SINH VIÊN</h3>
					<hr>
					<form action="" class="needs-validation" method="POST" novalidate>
						<div class="media">
							<div class="row" style="width: 100%;">
								<div class="col-sm-4" style="display: flex; justify-content: center;">
									<img src="<% if (sinhVien.getGioiTinh().contains("Nữ")){out.print("./resource/image/anh-nu.jpg");} else {out.print("./resource/image/anh-nam.jpg");} %>" style="width: 350px; height: auto;">
								</div>
								<div class="col-sm-8">
									<div class="media-body">
										<h5>
											<%= sinhVien.getHoTen() %>
										</h5>
										<hr>
										<p>
											Ngày sinh: <%= sinhVien.getNgaySinh() %>
										</p>
										<p>
											Giới tính: <%= sinhVien.getGioiTinh() %>
										</p>
										<p>
											Số điện thoại: <%= sinhVien.getSDT() %>
										</p>
										<p>
											Email: <%= sinhVien.getEmail() %>
										</p>
										<p>
											Lớp: <%= sinhVien.getTenLop() %>
										</p>
										<p>
											Chức vụ: <%= sinhVien.getChucVu() %>
										</p>
										<p>
											Tình trạng học: <%= sinhVien.getTinhTrangHoc() %>
										</p>
									</div>
								</div>
							</div>
						</div>
						<br>
					</form>
				</div>
				<% } %>
			</div>

			<div class="col-sm-1"></div>
		</div>
	</div>

	<!-- /* chân trang */ -->
	<div class="jumbotron text-center" style="margin-bottom: 0">

		<%@ include file="../BoCuc/Footer.jsp"%>

	</div>

</body>
</html>