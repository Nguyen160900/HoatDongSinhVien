<%@page import="Service.DSHoatDongService"%>
<%@page import="Model.DSHoatDong"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Danh sách đăng ký hoạt động</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/22403d42e6.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resource/style/style-footer.css">
</head>
<style>
.smallbtn {
	border: none;
	outline: none;
	border-radius: 5px;
	background: transparent;
}

.smallbtn:hover {
	box-shadow: 5px 5px 10px gray;
}

.smallbtn1 {
	border: none;
	outline: none;
	border-radius: 5px;
	background: transparent;
}

.smallbtn1:hover {
	box-shadow: 5px 5px 10px gray;
}

.smallbtn2 {
	border: none;
	outline: none;
	border-radius: 5px;
	background: transparent;
}

.smallbtn2:hover {
	box-shadow: 5px 5px 10px gray;
}

.jumbotron {
	background-image: linear-gradient(to bottom right, #1E90FF, #FFFFFF);
	display: flex;
}
</style>
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
				if (username != null) {
				%>
				<form action="<%=request.getContextPath()%>/DanhSachHoatDong"
					method="POST">
					<h2 style="text-align: center">DANH SÁCH CÁC HOẠT ĐỘNG ĐÃ ĐĂNG KÝ</h2>
					<hr>
					<div class="row">
						<div class="col-sm-2"
							style="justify-content: center; display: flex; margin: auto">
						</div>
						<div class="col-sm-2"
							style="justify-content: center; display: flex;">
							<select name="cboNamHoc" class="form-control" id="cboNamHoc"
								style="margin: 5px 0;">
								<option value="">--Chọn năm học--</option>
								<%
								DSHoatDongService dsHoatDongService= new DSHoatDongService();
								SinhVien sinhVien = new SinhVien();

								sinhVien = dsHoatDongService.get_CboNamHoc(sv.getMaSinhVien());

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
						<div class="col-sm-2"
							style="justify-content: center; display: flex;">
							<select name="cboHocKy" class="form-control" id="cboHocKy"
								style="margin: 5px 0;">
								<option value="0">--Chọn học kỳ--</option>
								<option value="1">Học kỳ 1</option>
								<option value="2">Học kỳ 2</option>
							</select>
						</div>
						<div class="col-sm-2"
							style="justify-content: center; display: flex;">
							<button class="btn btn-primary btn-block" type="submit"
								name="btnLoc" value="btnLoc" style="margin: 5px 0;">Lọc
								dữ liệu</button>
						</div>
						<div class="col-sm-2"
							style="justify-content: center; display: flex;">
							<a
								href="${pageContext.request.contextPath}/DanhSachHoatDong/LamMoi"
								class="btn btn-info btn-block" name="btnLamMoi"
								value="btnLamMoi" style="margin: 5px 0;">Làm mới</a>
						</div>
						<div class="col-sm-2"
							style="justify-content: center; display: flex;"></div>
					</div>
					
					<br>
					
					<div class="row">
						<%
						List<DSHoatDong> list;
						
						if(request.getAttribute("dsHoatDongs") != null){
							list = (List<DSHoatDong>) request.getAttribute("dsHoatDongs");
						} else{
							list = (List<DSHoatDong>)request.getAttribute("dsHoatDongs_TimKiem");
						}
						for (DSHoatDong dsHoatDong : list) {
						%>
						<div class="col-lg-3" style="margin-bottom: 15px">
							<div class="card" style="width: 300px">
								<img class="card-img-top"
									src="${pageContext.request.contextPath}/resource/image/anh3.jpg"
									alt="Card image" style="width: 100%">
								<div class="card-body">
									<h5 class="card-title" style="text-align: center"><%=dsHoatDong.getTenHoatDong()%></h5>
									<hr>
									<p class="card-text">
										Ngày diễn ra:
										<%=dsHoatDong.getNgayDienRa()%></p>
									<p>
										Năm học:
										<%=dsHoatDong.getNamHoc()%>
										<-> Học kỳ:
										<%=dsHoatDong.getHocKy()%></p>

									<div class="row">
										<div class="col-lg-12">
											<%
											if (dsHoatDong.getTinhTrang().equals("Tham gia")) {
											%>
											<div class="alert alert-success" style="margin: 0;">
												<h6 style="text-align: center;">Bạn đã tham gia</h6>
											</div>
											<%
											} else if (dsHoatDong.getTinhTrang().equals("Đăng ký")) {
											%>
											<div class="alert alert-info" style="margin: 0;">
												<h6 style="text-align: center;">Hoạt động đã đăng ký</h6>
											</div>
											<%
											} else {
											%>
											<div class="alert alert-danger" style="margin: 0;">
												<h6 style="text-align: center;">Bạn không tham gia</h6>
											</div>
											<%
											}
											%>
										</div>
									</div>
									<div class="row">
										<div class="col-lg-12">
											<button type="button" data-toggle="modal"
												data-target="#mdChitiet<%=dsHoatDong.getMaHoatDong()%>"
												class="btn btn-info btn-block" style="margin-top: 10px;">CHI
												TIẾT</button>
										</div>
									</div>
									<!-- MODAL CHI TIẾT, CHỈNH SỬA -->
									<div class="modal fade"
										id="mdChitiet<%=dsHoatDong.getMaHoatDong()%>"
										data-backdrop="static" data-keyboard="false" tabindex="-1"
										aria-labelledby="mdChitietLabel" aria-hidden="true">
										<div class="modal-dialog modal-dialog-scrollable modal-lg">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="mdChitietLabel">Chi tiết
														hoạt động</h5>
													<button type="button" class="close" data-dismiss="modal">&times;</button>
												</div>
												<div class="modal-body">
													<div class="row">
														<div class="col-sm-12"
															style="justify-content: center; display: block;">
															<p style="font-size: 18px;">
																Tên hoạt động: <input type="text" class="form-control"
																	id="tenhoatdong" name="txtTenHD"
																	style="font-size: 18px; pointer-events: none;"
																	value="<%=dsHoatDong.getTenHoatDong()%>">
															</p>
														</div>
													</div>
													<div class="row">
														<div class="col-sm-6"
															style="justify-content: center; display: block;">
															<p style="font-size: 18px;">
																Ngày diễn ra: <input type="text" class="form-control"
																	id="Ngaydienra" name="txtNgay"
																	style="font-size: 18px; pointer-events: none;"
																	value="<%=dsHoatDong.getNgayDienRa()%>">
															</p>
														</div>
														<div class="col-sm-6"
															style="justify-content: center; display: block;">
															<p style="font-size: 18px;">
																Thời gian diễn ra: <input type="text"
																	class="form-control" id="TGdienra" name="txtTG"
																	style="font-size: 18px; pointer-events: none;"
																	value="<%=dsHoatDong.getThoiGianDienRa()%>">
															</p>
														</div>
													</div>
													<div class="row">
														<div class="col-sm-6"
															style="justify-content: center; display: block;">
															<p style="font-size: 18px;">
																Năm học: <input type="text" class="form-control"
																	id="namhoc" name="txtNamhoc"
																	style="font-size: 18px; pointer-events: none;"
																	value="<%=dsHoatDong.getNamHoc()%>">
															</p>
														</div>
														<div class="col-sm-6"
															style="justify-content: center; display: block;">
															<p style="font-size: 18px;">
																Học kì: <input type="text" class="form-control"
																	id="hocki" name="txthocki"
																	style="font-size: 18px; pointer-events: none;"
																	value="<%=dsHoatDong.getHocKy()%>">
															</p>
														</div>
													</div>
													<div class="row">
														<div class="col-sm-6"
															style="justify-content: center; display: block;">
															<p style="font-size: 18px;">
																Khóa tham gia: <input type="text" class="form-control"
																	id="khoathamgia" name="txtkhoa"
																	style="font-size: 18px; pointer-events: none;"
																	value="<%=dsHoatDong.getKhoaThamGia()%>">
															</p>
														</div>
														<div class="col-sm-6"
															style="justify-content: center; display: block;">
															<p style="font-size: 18px;">
																Số lượng: <input type="text" class="form-control"
																	id="soluong" name="txtsoluong"
																	style="font-size: 18px; pointer-events: none;"
																	value="<%=dsHoatDong.getSoLuong()%>">
															</p>
														</div>
													</div>
													<div class="row">
														<div class="col-sm-12"
															style="justify-content: center; display: block;">
															<p style="font-size: 18px;">
																Nội dung:
																<textarea class="form-control" id="noidung"
																	name="txtNoiDung"
																	style="font-size: 18px; overflow-y: scroll; resize: none; height: 500px;"
																	rows="3"><%=dsHoatDong.getNoiDung()%></textarea>
															</p>
														</div>
													</div>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-danger"
														data-dismiss="modal">Thoát</button>
												</div>
											</div>
										</div>
									</div>
									<!-- END MODAL -->
								</div>
							</div>
						</div>
						<%
						}
						%>
					</div>

				</form>
				<%
				}
				%>
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