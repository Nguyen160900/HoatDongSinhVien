<%@page import="Model.HoatDong"%>
<%@page import="java.util.List"%>
<%@page import="Service.QLHoatDongLopService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý đăng ký hoạt động lớp</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/22403d42e6.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/style/style-footer.css">
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

			<div class="col-sm-12">
				<%
				if (username != null) {
				%>

				<h2 style="text-align: center">ĐĂNG KÝ HOẠT ĐỘNG SINH VIÊN</h2>
				<h4 style="text-align: center"> Lớp: <%=sv.getTenLop()%></h4>
				<hr>
				<form action="<%=request.getContextPath()%>/QuanLyHoatDongLop" method="POST">
				<div class="row">
					<div class="col-sm-2" style="justify-content: center; display: flex; margin: auto">
					</div>
					<div class="col-sm-2" style="justify-content: center; display: flex;">
						<select name="cboNamHoc" class="form-control" id="cboNamHoc" style="margin: 5px 0;">
							<option value="">--Chọn năm học--</option>
							<%
							QLHoatDongLopService qlHoatDongLopService = new QLHoatDongLopService();
							SinhVien sinhVien = new SinhVien();

							sinhVien = qlHoatDongLopService.get_CboNamHoc(sv.getMaSinhVien());

							String nam1 = sinhVien.getNamBatDau() + "-" + (sinhVien.getNamBatDau() + 1);
							String nam2 = (sinhVien.getNamBatDau() + 1) + "-" + (sinhVien.getNamBatDau() + 2);
							String nam3 = (sinhVien.getNamBatDau() + 2) + "-" + (sinhVien.getNamBatDau() + 3);
							String nam4 = (sinhVien.getNamBatDau() + 3) + "-" + (sinhVien.getNamBatDau() + 4);
							String nam5 = (sinhVien.getNamBatDau() + 4) + "-" + (sinhVien.getNamBatDau() + 5);

							out.print("<option value='" + nam1 + "'>" + nam1 + "</option>");
							out.print("<option value='" + nam2 + "'>" + nam2 + "</option>");
							out.print("<option value='" + nam3 + "'>" + nam3 + "</option>");
							out.print("<option value='" + nam4 + "'>" + nam4 + "</option>");
							out.print("<option value='" + nam5 + "'>" + nam5 + "</option>");
							%>
						</select>
					</div>
					<div class="col-sm-2" style="justify-content: center; display: flex;">
						<select name="cboHocKy" class="form-control" id="cboHocKy" style="margin: 5px 0;">
							<option value="0">--Chọn học kỳ--</option>
							<option value="1">Học kỳ 1</option>
							<option value="2">Học kỳ 2</option>
						</select>
					</div>
					<div class="col-sm-2" style="justify-content: center; display: flex;">
						<button class="btn btn-primary btn-block" type="submit" name="btnLoc" value="btnLoc" style="margin: 5px 0;">Lọc dữ liệu</button>
					</div>
					<div class="col-sm-2" style="justify-content: center; display: flex;">
						<a href="${pageContext.request.contextPath}/QuanLyHoatDongLop/LamMoi" class="btn btn-info btn-block" name="btnLamMoi" value="btnLamMoi" style="margin: 5px 0;">Làm mới</a>
					</div>
					<div class="col-sm-2" style="justify-content: center; display: flex;"></div>
				</div>
				</form>
				<br>

				<div class="row">
					<%
						List<HoatDong> list_HoatDongs = null; 
					
						if(request.getAttribute("list_HoatDongs") != null){
							list_HoatDongs = (List<HoatDong>)request.getAttribute("list_HoatDongs");
						} else{
							list_HoatDongs = (List<HoatDong>)request.getAttribute("list_TimKiems");
						}
						
						for(HoatDong hoatDong: list_HoatDongs){
							
					%>
					<div class="col-lg-3" style="margin-bottom: 15px">
						<div class="card" style="width: 350px">
							<img class="card-img-top" src="${pageContext.request.contextPath}/resource/image/anh3.jpg" alt="Card image" style="width: 100%">
							<div class="card-body">
								<h5 class="card-title" style="text-align: center"><%= hoatDong.getTenHoatDong() %></h5>
								<hr>
								<p class="card-text">Ngày diễn ra: <%= hoatDong.getNgayDienRa() %></p>
								<p>Năm học: <%=hoatDong.getNamHoc() %> <-> Học kỳ:<%=hoatDong.getHocKy() %></p>
								<p>Đối tượng: <%=hoatDong.getKhoaThamGia() %> </p>

								<div class="row">
									<div class="col-lg-12">
										<a href="<%=request.getContextPath()%>/QuanLySinhVienDKHDLop?mahd=<%=hoatDong.getMaHoatDong() %>&malop=<%=sinhVien.getMaLop() %>" class="btn btn-info btn-block" style="margin-top: 10px;">CHI TIẾT >></a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<%
						}
					%>					
					
				</div>

				<%
				}
				%>
				<br>
			</div>
		</div>
	</div>

	<!-- /* chân trang */ -->
	<div class="jumbotron text-center" style="margin-bottom: 0">

		<%@ include file="../BoCuc/Footer.jsp"%>

	</div>

</body>
</html>

<script>
	function LamMoi() {
		location.reload();
	}
</script>