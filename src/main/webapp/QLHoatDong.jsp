<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Service.KhoaHocService"%>
<%@page import="Model.KhoaHoc"%>
<%@page import="Model.HoatDong"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Quản lý hoạt động</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/style/style-footer.css">
<style>
  	.jumbotron{
	    background-image: linear-gradient(to bottom right, #1E90FF, #FFFFFF); 
	    display: flex;
	}
	
	p{
		margin-top: 10px;
		margin-bottom: 10px;
	}
	
	.container-fluid_1 {
	  overflow-y: auto;
	  height: 600px;
	  width: 100%;
	}
	
	
</style>
</head>

<%
Calendar calendar = Calendar.getInstance();
int nam = calendar.get(Calendar.YEAR);

int nammoi = nam + 3;
int namcu = nam - 3;

int nammoi_1 = nam + 3;
int namcu_1 = nam - 3;

int nammoi_2 = nam + 3;
int namcu_2 = nam - 3;
%>

<body>

	<!-- Đầu trang -->
	<div class="jumbotron text-center"
		style="margin-bottom: 0; padding: 20px">

		<%@ include file="../BoCuc/Top.jsp"%>

	</div>

	<!-- /* menu của trang */ -->
	<%@ include file="../BoCuc/MenuAD.jsp"%>

	<!--  /* thân của trang */ -->
	<div class="container-fluid" style="margin-top: 30px">
		<div class="row">
			<div class="col-sm-1"></div>

			<div class="col-sm-10">
					<%
					if (username != null) {
					%>
						<div class="container-fluid">
							<h3 style="text-align: center">DANH SÁCH CÁC HOẠT ĐỘNG</h3>
							<hr>
							<!-- Phần tìm kiếm, lọc dữ liệu -->
							<div>
								<form action="<%=request.getContextPath()%>/QLHoatDong_LamMoi" method="POST" >
								<div class="row">
									<div class="col-sm-1" style="justify-content: center; display: flex;"></div>
									<div class="col-sm-6" style="justify-content: center; display: flex;">
										<input class="form-control" type="text" id="myInput" name="txtTimKiem" placeholder="Nhập nội dung cần cần tìm" aria-label="Search">
									</div>
									<div class="col-sm-2" style="justify-content: center; display: flex;">
										<button class="btn btn-primary btn-block" type="button" class="btn btn-primary" data-toggle="modal" data-target="#addModal">Thêm mới</button>
									</div>
									<div class="col-sm-2" style="justify-content: center; display: flex;">
										<button class="btn btn-primary btn-block" type="submit" name="btnLamMoi">Làm mới</button>
									</div>
									<div class="col-sm-1" style="justify-content: center; display: flex;"></div>
								</div>
								</form>
								
								<br>
								
								<form action="<%=request.getContextPath()%>/QuanLyHoatDong_TimKiem" method="POST" >
									<div class="row">
										<div class="col-sm-2" style="justify-content: center; display: flex;"></div>	
										<div class="col-sm-2" style="justify-content: center; display: flex; margin: auto">
											<span>Lọc dữ liệu theo:</span>
										</div>
										<div class="col-sm-2" style="justify-content: center; display: flex;">
											<select name="cboNamHocLoc" class="form-control" id="cboNamHocLoc">
												<option value="" selected="selected">--Chọn năm học--</option>
	                                             <%
		                                            while(namcu < nammoi){
	                                                	String nam1 = namcu + "-" + (namcu + 1);
	                                                	out.print("<option value='" + nam1 +"'>"+ nam1 +"</option>");
	                                                	namcu++;
	                                                }
	                                             %>
											</select>
										</div>
										<div class="col-sm-2" style="justify-content: center; display: flex;">
											<select name="cboHocKyLoc" class="form-control" id="cboHocKyLoc">
												<option value="0" selected="selected">--Chọn học kỳ--</option>
												<option value="1">Học kỳ 1</option>
												<option value="2">Học kỳ 2</option>
											</select>
										</div>
										<div class="col-sm-2" style="justify-content: center; display: flex;">
											<button class="btn btn-success" type="submit" name="btnLoc">Lọc dữ liệu</button>
										</div>
										<div class="col-sm-2" style="justify-content: center; display: flex;"></div>
									</div>
								</form>
							</div>
							<!-- Kết thúc phần tìm kiếm -->

							<!-- CÁC MODAL -->
							<form action="<%=request.getContextPath()%>/QLHoatDong/insert" method="POST" class="needs-validation" novalidate>
								<!-- modal thêm  -->
								<div class="modal fade" id="addModal">
									<div class="modal-dialog modal-lg">
										<div class="modal-content">
	
											<!-- Modal Header -->
											<div class="modal-header">
												<h4 class="modal-title">Thêm hoạt động</h4>
												<button type="button" class="close" data-dismiss="modal">&times;</button>
											</div>
	
											<!-- Modal body -->
												<div class="modal-body">
													<div class="row">
														<div class="col-sm-12" style="justify-content: center; display: block;">
															<p style="margin-top: 0">Tên hoạt động:</p>
															<input type="text" class="form-control" id="tenhoatdong" name="txtTenHoatDong" style="font-size: 18px;" required>
															<div class="valid-feedback">Thông tin hợp lệ.</div>
    														<div class="invalid-feedback">Vui lòng không bỏ trống thông tin.</div>
														</div>
													</div>
													<div class="row">
														<div class="col-sm-6" style="justify-content: center; display: block;">
															<p>Ngày diễn ra</p>
															<input type="date" class="form-control" id="ngaydienra" name="dateNgayDienRa" required>
															<div class="valid-feedback">Thông tin hợp lệ.</div>
    														<div class="invalid-feedback">Vui lòng không bỏ trống thông tin.</div>
														</div>
														<div class="col-sm-6" style="justify-content: center; display: block;">
															<p>Thời gian</p>
															<select name="cboThoiGianDienRa" class="form-control" id="cboThoiGianDienRa" required>
																<option value="" selected="selected">--Chọn thời gian--</option>
																<%
																	String[] thoigian = {"00:00", "00:30", "01:00", "01:30", "02:00", "02:30", 
																						"03:00", "03:30", "04:00", "04:30", "05:00", "05:30", 
																						"06:00", "06:30", "07:00", "07:30", "08:00", "08:30", 
																						"09:00", "09:30", "10:00", "10:30", "11:00", "11:30", 
																						"12:00", "12:30", "13:00", "13:30", "14:00", "14:30", 
																						"15:00", "15:30", "16:00", "16:30", "17:00", "17:30", 
																						"18:00", "18:30", "19:00", "19:30", "20:00", "20:30", 
																						"21:00", "21:30", "22:00", "22:30", "23:00", "23:30", };
						                                            for(int i = 0; i < 48; i++){
					                                                	out.print("<option value='" + thoigian[i] +"'>"+ thoigian[i] +"</option>");
					                                                }
					                                             %>
															</select>
															<div class="valid-feedback">Thông tin hợp lệ.</div>
    														<div class="invalid-feedback">Vui lòng không bỏ trống thông tin.</div>
														</div>
													</div>
													<div class="row">
														<div class="col-sm-4" style="justify-content: center; display: block;">
															<p>Chọn năm học</p>
															<select name="cboNamHoc" class="form-control" id="cboNamHoc" required>
																<option value="" selected="selected">--Chọn năm học--</option>
																<%
																while(namcu_1 < nammoi_1){
				                                                	String nam1 = namcu_1 + "-" + (namcu_1 + 1);
				                                                	out.print("<option value='" + nam1 +"'>"+ nam1 +"</option>");
				                                                	namcu_1++;
				                                                }
					                                             %>
															</select>
															<div class="valid-feedback">Thông tin hợp lệ.</div>
    														<div class="invalid-feedback">Vui lòng không bỏ trống thông tin.</div>
														</div>
														<div class="col-sm-4" style="justify-content: center; display: block;">
															<p>Chọn học kỳ</p>
															<select name="cboHocKy" class="form-control" id="cboHocKy" required>
																<option value="" selected="selected">--Chọn học kỳ--</option>
																<option value="1">Học kỳ 1</option>
																<option value="2">Học kỳ 2</option>
															</select>
															<div class="valid-feedback">Thông tin hợp lệ.</div>
    														<div class="invalid-feedback">Vui lòng không bỏ trống thông tin.</div>
														</div>
														<div class="col-sm-4" style="justify-content: center; display: block;">
															<p>Chọn cấp diễn ra</p>
															<select name="cboCapDienRa" class="form-control" id="cboCapDienRa" required>
																<option value="" selected="selected">-- Chọn cấp diễn ra --</option>
																<option value="CAPTINH">CAPTINH - Cấp tỉnh</option>
																<option value="CAPTRUONG">CAPTRUONG - Cấp trường</option>
																<option value="CAPKHOA">CAPKHOA - Cấp khoa</option>
																<option value="CAPLOP">CAPLOP - Cấp lớp</option>
																<option value="HIENMAU">HIENMAU - Hiến máu</option>
																<option value="TN1">TN1 - Tình nguyện (Mùa hè xanh, tiếp sức mùa thi, ...)</option>
																<option value="TN2">TN2 - Tình nguyện tại chỗ</option>
																<option value="DIEUDONG">DIEUDONG - Huy động lực lượng cấp khoa trở lên</option>
															</select>
															<div class="valid-feedback">Thông tin hợp lệ.</div>
    														<div class="invalid-feedback">Vui lòng không bỏ trống thông tin.</div>
														</div>
													</div>
		
													<div class="row">
														<div class="col-sm-6" style="justify-content: center; display: block;">
															<p>Khóa tham gia</p>
															<!-- <input type="text" class="form-control" id="khoathamgia" name="txtKhoaThamGia" style="font-size: 18px;"> -->
															
															<%
																KhoaHocService khoaHocService = new KhoaHocService();
																List<KhoaHoc> listKhoaHoc = (List<KhoaHoc>) khoaHocService.getKhoaHoc();
																for(KhoaHoc khoaHoc:listKhoaHoc)
																{
																	out.print("<div class='form-check-inline'>");
																	out.print("<label class='form-check-label'>");
																	out.print("<input type='checkbox' class='form-check-input' id='khoathamgia' value='"+ khoaHoc.getMaKhoaHoc() +"' name='ckbKhoaThamGia'>"+ khoaHoc.getMaKhoaHoc());
																	out.print("</label>");
																	out.print("</div>");
																}
															%>
															<p style="color: red;">Vui lòng chọn khóa học tham gia.</p>
														</div>
														<div class="col-sm-6" style="justify-content: center; display: block;">
															<p>Số lượng</p>
															<input type="number" class="form-control" id="soluong" name="txtSoLuong" style="font-size: 18px;" required>
															<div class="valid-feedback">Thông tin hợp lệ.</div>
    														<div class="invalid-feedback">Vui lòng không bỏ trống thông tin.</div>
														</div>
		
													</div>
													<div class="row">
														<div class="col-sm-12"
															style="justify-content: center; display: block;">
															<p>File</p>
															<input type="text" class="form-control" id="file" name="Filedrive" style="font-size: 18px;">
															<!-- <input type="text" class="form-control" id="file" name="File" style="font-size: 18px;" > -->
														</div>
													</div>
													<div>
														<p>Nội dung</p>
														<textarea class="form-control" name="txtNoiDung" id="noidung" cols="40" rows="6" style="font-size: 18px;"></textarea>
													</div>
												</div>
	
											<!-- Modal footer -->
											<div class="modal-footer">
												<button class="btn btn-primary" type="submit" name="btnThem">Thêm mới</button>
												<button type="button" class="btn btn-secondary" data-dismiss="modal">Thoát</button>
											</div>
	
										</div>
									</div>
								</div>
							</form>
							<!-- end modal -->

							<form action="<%=request.getContextPath()%>/QLHoatDong/update" method="POST">
							<!-- Modal cập nhật-->
							<div class="modal fade" id="editModal">
								<div class="modal-dialog modal-lg">
									<div class="modal-content">

										<div class="modal-header">
											<div>
												<h4 class="modal-title" style="text-align: left;">Chỉnh sửa hoạt động</h4>
											</div>
											<button type="button" class="close" data-dismiss="modal">&times;</button>
										</div>

										<div class="modal-body">
											<div class="row">
												<div class="col-sm-4" style="justify-content: center; display: block;">
													<p>Mã hoạt động:</p>
													<input type="text" class="form-control" id="mahoatdong1" name="txtMaHoatDong1" style="font-size: 18px; pointer-events: none;">
												</div>
												<div class="col-sm-4" style="justify-content: center; display: block;">
													<p>Ngày diễn ra</p>
													<input type="date" class="form-control" name="dateNgayDienRa1" id="ngaydienra1">
												</div>
												<div class="col-sm-4" style="justify-content: center; display: block;">
													<p>Thời gian</p>
													<select name="cboThoiGianDienRa1" class="form-control" id="thoigian1">
														<option value="" selected="selected">--Chọn thời gian--</option>
														<%
															String[] thoigian_1 = {"00:00", "00:30", "01:00", "01:30", "02:00", "02:30", 
																				"03:00", "03:30", "04:00", "04:30", "05:00", "05:30", 
																				"06:00", "06:30", "07:00", "07:30", "08:00", "08:30", 
																				"09:00", "09:30", "10:00", "10:30", "11:00", "11:30", 
																				"12:00", "12:30", "13:00", "13:30", "14:00", "14:30", 
																				"15:00", "15:30", "16:00", "16:30", "17:00", "17:30", 
																				"18:00", "18:30", "19:00", "19:30", "20:00", "20:30", 
																				"21:00", "21:30", "22:00", "22:30", "23:00", "23:30", };
				                                            for(int i = 0; i < 48; i++){
			                                                	out.print("<option value='" + thoigian_1[i] +"'>"+ thoigian_1[i] +"</option>");
			                                                }
			                                             %>
													</select>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-12" style="justify-content: center; display: block;">
													<p>Tên hoạt động:</p>
													<input type="text" class="form-control" id="tenhoatdong1" name="txtTenHoatDong1" style="font-size: 18px;">
												</div>
											</div>

											<div class="row">
												<div class="col-sm-4" style="justify-content: center; display: block;">
													<p>Chọn năm học</p>
													<select name="cboNamHoc1" class="form-control" id="cbonamhoc1">
														<option value="" selected="selected">--Chọn năm học--</option>
														<%
				                                            while(namcu_2 < nammoi_2){
			                                                	String nam1 = namcu_2 + "-" + (namcu_2 + 1);
			                                                	out.print("<option value='" + nam1 +"'>"+ nam1 +"</option>");
			                                                	namcu_2++;
			                                                }
			                                             %>
													</select>
												</div>
												<div class="col-sm-4" style="justify-content: center; display: block;">
													<p>Chọn học kỳ</p>
													<select name="cboHocKy1" class="form-control" id="cbohocky1">
														<option value="" selected="selected">--Chọn học kỳ--</option>
														<option value="1">Học kỳ 1</option>
														<option value="2">Học kỳ 2</option>
													</select>
												</div>
												<div class="col-sm-4" style="justify-content: center; display: block;">
													<p>Chọn cấp</p>
													<select name="cboCapDienRa1" class="form-control" id="cbocapdienra1">
														<option value="" selected="selected">-- Chọn cấp diễn ra --</option>
														<option value="CAPTINH">CAPTINH - Cấp tỉnh</option>
														<option value="CAPTRUONG">CAPTRUONG - Cấp trường</option>
														<option value="CAPKHOA">CAPKHOA - Cấp khoa</option>
														<option value="CAPLOP">CAPLOP - Cấp lớp</option>
														<option value="HIENMAU">HIENMAU - Hiến máu</option>
														<option value="TN1">TN1 - Tình nguyện (Mùa hè xanh, tiếp sức mùa thi, ...)</option>
														<option value="TN2">TN2 - Tình nguyện tại chỗ</option>
														<option value="DIEUDONG">DIEUDONG - Huy động lực lượng cấp khoa trở lên</option>
													</select>
												</div>
											</div>

											<div class="row">
												<div class="col-sm-6" style="justify-content: center; display: block;">
													<p>Khóa tham gia</p>
													<!-- <input type="text" class="form-control" id="khoathamgia1" name="txtKhoaThamGia1" style="font-size: 18px;"> -->
													<%
														for(KhoaHoc khoaHoc_1:listKhoaHoc)
														{
															out.print("<div class='form-check-inline'>");
															out.print("<label class='form-check-label'>");
															out.print("<input type='checkbox' class='form-check-input khoathamgia1' value='"+ khoaHoc_1.getMaKhoaHoc() +"' name='ckbKhoaThamGia1'>"+ khoaHoc_1.getMaKhoaHoc());
															out.print("</label>");
															out.print("</div>");
														}
													%>
													<p style="color: red;">Vui lòng chọn khóa học tham gia.</p>
												</div>
												<div class="col-sm-6" style="justify-content: center; display: block;">
													<p>Số lượng</p>
													<input type="text" class="form-control" id="soluong1" name="txtSoLuong1" style="font-size: 18px;">
												</div>
											</div>

											<div class="row">
												<div class="col-sm-12" style="justify-content: center; display: block;">
													<p>File</p>
													<input type="text" class="form-control" id="file1" name="Filedrive1" style="font-size: 18px;">
													<!-- <input type="text" class="form-control" id="file1" name="File1" style="font-size: 18px;" > -->
												</div>
											</div>
											<div>
												<p>Nội dung</p>
												<textarea class="form-control" name="txtNoiDung1" id="noidung1" cols="40" rows="6" style="font-size: 18px;"></textarea>
											</div>

											<br>
											<button type="submit" class="btn btn-primary btn-block" id="btnCapNhat" name="btnCapNhat" value="btnCapNhat" style="margin: 0 0;">Cập nhật</button>
										</div>
									</div>
								</div>
							</div>
							</form>
							<!-- end modal -->

							<!-- Modal xem chi tiết  -->
							<div class="modal fade" id="chitietModal">
								<div class="modal-dialog modal-lg">
									<div class="modal-content">

										<div class="modal-header">
											<div>
												<h4 class="modal-title" style="text-align: left;">Xem chi tiết hoạt động</h4>
											</div>
											<button type="button" class="close" data-dismiss="modal">&times;</button>
										</div>

										<div class="modal-body">
											<div class="row">
												<div class="col-sm-12" style="justify-content: center; display: block;">
													<p>Tên hoạt động:</p>
													<input type="text" class="form-control" id="tenhoatdong2" style="font-size: 18px; pointer-events: none;">
												</div>

											</div>

											<div class="row">
												<div class="col-sm-6" style="justify-content: center; display: block;">
													<p>Ngày diễn ra</p>
													<input type="date" class="form-control" id="ngaydienra2" style="font-size: 18px; pointer-events: none;">
												</div>
												<div class="col-sm-6" style="justify-content: center; display: block;">
													<p>Thời gian</p>
													<input type="text" class="form-control" id="thoigian2" name="txtThoiGianDR2" style="font-size: 18px; pointer-events: none;">
												</div>
											</div>

											<div class="row">
												<div class="col-sm-4" style="justify-content: center; display: block;">
													<p>Năm học</p>
													<input type="text" class="form-control" id="namhoc2" style="font-size: 18px; pointer-events: none;">
												</div>
												<div class="col-sm-4" style="justify-content: center; display: block;">
													<p>Học kỳ</p>
													<input type="text" class="form-control" id="hocky2" style="font-size: 18px; pointer-events: none;">
												</div>
												<div class="col-sm-4" style="justify-content: center; display: block;">
													<p>Cấp diễn ra</p>
													<input type="text" class="form-control" id="capdienra2" style="font-size: 18px; pointer-events: none;">
												</div>
											</div>

											<div class="row">
												<div class="col-sm-6" style="justify-content: center; display: block;">
													<p>Khóa tham gia</p>
													<!-- <input type="text" class="form-control" id="khoathamgia2" style="font-size: 18px; pointer-events: none;"> -->
													<%
														for(KhoaHoc khoaHoc_2:listKhoaHoc)
														{
															out.print("<div class='form-check-inline'>");
															out.print("<label class='form-check-label'>");
															out.print("<input type='checkbox' class='form-check-input khoathamgia2' value='"+ khoaHoc_2.getMaKhoaHoc() +"' name='ckbKhoaThamGia2'>"+ khoaHoc_2.getMaKhoaHoc());
															out.print("</label>");
															out.print("</div>");
														}
													%>
												</div>
												<div class="col-sm-6" style="justify-content: center; display: block;">
													<p>Số lượng</p>
													<input type="text" class="form-control" id="soluong2" style="font-size: 18px; pointer-events: none;">
												</div>
											</div>
											<div class="row">
												<div class="col-sm-12" style="justify-content: center; display: block;">
													<p>File</p>
													<input type="text" class="form-control" id="file2" name="Filedrive2" style="font-size: 18px; pointer-events: none;">
													<!-- <input type="text" class="form-control" id="file2" name="File2" style="font-size: 18px;" > -->
												</div>
											</div>
											<div>
												<p>Nội dung</p>
												<textarea class="form-control" id="noidung2" cols="40" rows="6" style="font-size: 18px; pointer-events: none;"></textarea>
											</div>

										</div>
									</div>
								</div>
							</div>
							<!-- end modal -->

							<form action="<%=request.getContextPath()%>/QLHoatDong/delete" method="POST">
							<!-- modal xóa -->
							<div class="modal fade" id="xoaModal">
								<div class="modal-dialog modal-lg">
									<div class="modal-content">

										<div class="modal-header">
											<div>
												<h4 class="modal-title" style="text-align: left;">Xóa hoạt động</h4>
											</div>

											<button type="button" class="close" data-dismiss="modal">&times;</button>
										</div>

										<div class="modal-body">
											<div>
												<div class="row">
													<div class="col-sm-12" style="justify-content: center; display: block;">
														<input type="text" class="form-control" name="txtMaHoatDong3" id="mahoatdong3" hidden style="font-size: 18px; pointer-events: none;">
													</div>
												</div>
												<h4>Bạn chắc chắn muốn xóa thông tin này không?</h4>
											</div>
										</div>

										<!-- Modal footer -->
										<div class="modal-footer">
											<button class="btn btn-primary" type="submit" name="btnXoa">Xóa</button>
											<button type="button" class="btn btn-secondary" data-dismiss="modal">Thoát</button>
										</div>
									</div>
								</div>
							</div>
							<!-- end modal -->
							</form>

							<!-- Phần load danh sách -->
							<div style="width: 100%">
								<br>
 									<div class="container-fluid_1">
									<table class="table table-bordered table-hover" style="width: 100%; text-align: center; vertical-align: inherit;" id="bangthongtin">
										<thead>
											<tr class="header">
												<th class="textDS" style="vertical-align: inherit;">STT</th>
												<th class="textDS" style="vertical-align: inherit;" hidden>Mã hoạt động</th>
												<th class="textDS" style="vertical-align: inherit;">Tên hoạt động</th>
												<th class="textDS" style="vertical-align: inherit;">Ngày diễn ra</th>
												<th class="textDS" style="vertical-align: inherit;" hidden>Thời gian</th>
												<th Class="textDS" style="vertical-align: inherit;">Cấp diễn ra</th>
												<th class="textDS" style="vertical-align: inherit;">Năm học</th>
												<th class="textDS" style="vertical-align: inherit; width: 50px;">Học kỳ</th>
												<th class="textDS" style="vertical-align: inherit;" hidden>Nội dung</th>
												<th class="textDS" style="vertical-align: inherit; width: 180px;">Khóa tham gia</th>
												<th class="textDS" style="vertical-align: inherit; width: 50px;">Số lượng</th>
												<th class="textDS" style="vertical-align: inherit;" hidden>File</th>
												<th class="textDS" style="vertical-align: inherit;" hidden>Ngày diễn ra</th>
												<th class="textDS" style="vertical-align: inherit; width: 50px;">Chi tiết</th>
												<th class="textDS" style="vertical-align: inherit; width: 50px;">Chỉnh sửa</th>
												<th class="textDS" style="vertical-align: inherit; width: 50px;">Xóa</th>
												<th class="textDS" style="vertical-align: inherit; width: 50px;">Danh sách</th>
										</thead>
										<tbody id="myTable">
											<%
												/* List<HoatDong> list = (List<HoatDong>) request.getAttribute("dsHoatDongs"); */
												
												List<HoatDong> list;
												
												if(request.getAttribute("dsHoatDongs") != null){
													list = (List<HoatDong>) request.getAttribute("dsHoatDongs");
												}
												else{
													list = (List<HoatDong>) request.getAttribute("hoatDongs");
												}
											
												int stt = 0;
											
												for(HoatDong hoatdong:list){
													stt+=1;
											%>
											<tr class="hang1">
							                    <td class="cot" style="text-align: center; vertical-align: inherit;"><%= stt %></td>
							                    <td class="cot" style="text-align: center; vertical-align: inherit;" hidden><%= hoatdong.getMaHoatDong() %></td>
							                    <td class="cot" style="text-align: center; vertical-align: inherit; text-align: left"><%= hoatdong.getTenHoatDong() %></td>
							                    <td class="cot" style="text-align: center; vertical-align: inherit; width: 125px;">
							                    	<%
								                    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
								                        String strDate = formatter.format(hoatdong.getNgayDienRa());
 														out.print(strDate);
 													%>
							                    </td>
							                    <td class="cot" style="text-align: center; vertical-align: inherit;" hidden><%= hoatdong.getThoiGianDienRa() %></td>
							                    <td class="cot" style="text-align: center; vertical-align: inherit;"><%= hoatdong.getCapDienRa() %></td>
							                    <td class="cot" style="text-align: center; vertical-align: inherit; width: 110px;"><%= hoatdong.getNamHoc() %></td>
							                    <td class="cot" style="text-align: center; vertical-align: inherit;"><%= hoatdong.getHocKy() %></td>
							                    <td class="cot" style="text-align: center; vertical-align: inherit;" hidden><%= hoatdong.getNoiDung() %></td>
							                    <td class="cot" style="text-align: center; vertical-align: inherit; text-align: left"><%= hoatdong.getKhoaThamGia() %></td>
							                    <td class="cot" style="text-align: center; vertical-align: inherit;"><%= hoatdong.getSoLuong() %></td>
							                    <td class="cot" style="text-align: center; vertical-align: inherit;" hidden><%= hoatdong.getFile() %></td>
							                    <td class="cot" style="text-align: center; vertical-align: inherit;" hidden><%= hoatdong.getNgayDienRa() %></td>
							                    <td class="cot" style="text-align: center; vertical-align: inherit;"><a class="btn btn-link chitietbtn" data-toggle="modal" data-target="#chitietModal" style="margin: 0 0;"><i class="fas fa-info-circle"></i></a></td>
							                    <td class="cot" style="text-align: center; vertical-align: inherit;"><a class="btn btn-link editbtn" data-toggle="modal" data-target="#editModal" style="margin: 0 0;"><i class="fas fa-edit"></i></a></td>
							                    <td class="cot" style="text-align: center; vertical-align: inherit;"><a class="btn btn-link xoabtn" data-toggle="modal" data-target="#xoaModal" style="margin: 0 0;"><i class="fas fa-trash-alt"></i></a></td>
							                    <td class="cot" style="text-align: center; vertical-align: inherit;"><a class="btn btn-link" href="${pageContext.request.contextPath}/DSDangKyHD?maHoatDong=<%= hoatdong.getMaHoatDong()%>"><i class="far fa-list-alt"></i></a></td>
						                    </tr>
						                    <% } %>
										</tbody>
									</table>
								</div>
							</div>
							<!-- End load Ddnh sách -->
							<!-- END -->
						</div>

						<br>

					<%
					}
					%>
				<br>
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
<script>
     // Gọi modal cập nhật 
     $(document).ready(function() {
         $('.editbtn').on('click', function() {
             $('#editModal').modal('show');

             $tr = $(this).closest('tr');

             var data = $tr.children("td").map(function() {
                 return $(this).text();
             }).get();
             
             var khoathamgia = data[9];
             
            if(khoathamgia.length <= 3){
            	 var khoa1 = data[9].substring(0,3);
            	 $('.khoathamgia1').val([khoa1]); 
            }
            else if(khoathamgia.length > 3 && khoathamgia.length <= 9)
     		{
            	var khoa1 = data[9].substring(0,3);
            	var khoa2 = data[9].substring(6,9);
           	 	$('.khoathamgia1').val([khoa1, khoa2]);
     		}
			else if(khoathamgia.length > 9 && khoathamgia.length <= 15)
     		{
				var khoa1 = data[9].substring(0,3);
            	var khoa2 = data[9].substring(6,9);
            	var khoa3 = data[9].substring(12,15);
           	 	$('.khoathamgia1').val([khoa1, khoa2, khoa3]);
     		}
			else if(khoathamgia.length > 15 && khoathamgia.length <= 21)
     		{
				var khoa1 = data[9].substring(0,3);
            	var khoa2 = data[9].substring(6,9);
            	var khoa3 = data[9].substring(12,15);
            	var khoa4 = data[9].substring(18,21);
           	 	$('.khoathamgia1').val([khoa1, khoa2, khoa3, khoa4]);
     		}
     		else{
     			var khoa1 = data[9].substring(0,3);
            	var khoa2 = data[9].substring(6,9);
            	var khoa3 = data[9].substring(12,15);
            	var khoa4 = data[9].substring(18,21);
            	var khoa5 = data[9].substring(24,27);
           	 	$('.khoathamgia1').val([khoa1, khoa2, khoa3, khoa4, khoa5]);
     		}
             
             console.log(data);
             $('#mahoatdong1').val(data[1]);
             $('#tenhoatdong1').val(data[2]);
             $('#ngaydienra1').val(data[12]);
             $('#thoigian1').val(data[4]);
             $('#cbocapdienra1').val(data[5]);
             $('#cbonamhoc1').val(data[6]);
             $('#cbohocky1').val(data[7]);
             $('#noidung1').val(data[8]);
             /* $('.khoathamgia1').val([data[10], data[11], data[12], data[13], data[14]]);  */
             /* $('.khoathamgia1').val(["K44","K41"]); */
             $('#soluong1').val(data[10]);
             $('#file1').val(data[11]);
         });
     });

     // Gọi modal xem chi tiết 
     $(document).ready(function() {
         $('.chitietbtn').on('click', function() {
             $('#chitietModal').modal('show');

             $tr = $(this).closest('tr');

             var data = $tr.children("td").map(function() {
                 return $(this).text();
             }).get();
             
             var khoathamgia = data[9];
             
             if(khoathamgia.length <= 3){
             	 var khoa1 = data[9].substring(0,3);
             	 $('.khoathamgia2').val([khoa1]); 
             }
             else if(khoathamgia.length > 3 && khoathamgia.length <= 9)
      		{
             	var khoa1 = data[9].substring(0,3);
             	var khoa2 = data[9].substring(6,9);
            	 	$('.khoathamgia2').val([khoa1, khoa2]);
      		}
 			else if(khoathamgia.length > 9 && khoathamgia.length <= 15)
      		{
 				var khoa1 = data[9].substring(0,3);
             	var khoa2 = data[9].substring(6,9);
             	var khoa3 = data[9].substring(12,15);
            	 	$('.khoathamgia2').val([khoa1, khoa2, khoa3]);
      		}
 			else if(khoathamgia.length > 15 && khoathamgia.length <= 21)
      		{
 				var khoa1 = data[9].substring(0,3);
             	var khoa2 = data[9].substring(6,9);
             	var khoa3 = data[9].substring(12,15);
             	var khoa4 = data[9].substring(18,21);
            	 	$('.khoathamgia2').val([khoa1, khoa2, khoa3, khoa4]);
      		}
      		else{
      			var khoa1 = data[9].substring(0,3);
             	var khoa2 = data[9].substring(6,9);
             	var khoa3 = data[9].substring(12,15);
             	var khoa4 = data[9].substring(18,21);
             	var khoa5 = data[9].substring(24,27);
            	 	$('.khoathamgia2').val([khoa1, khoa2, khoa3, khoa4, khoa5]);
      		}

             console.log(data);
             $('#tenhoatdong2').val(data[2]);
             $('#ngaydienra2').val(data[12]);
             $('#thoigian2').val(data[4]);
             $('#capdienra2').val(data[5]);
             $('#namhoc2').val(data[6]);
             $('#hocky2').val(data[7]);
             $('#noidung2').val(data[8]);
             /* $('#khoathamgia2').val(data[9]); */
             $('#soluong2').val(data[10]);
             $('#file2').val(data[11]);

         });
     });

     //Gọi modal xóa
     $(document).ready(function() {
         $('.xoabtn').on('click', function() {
             $('#xoaModal').modal('show');

             $tr = $(this).closest('tr');

             var data = $tr.children("td").map(function() {
                 return $(this).text();
             }).get();

             console.log(data);
             $('#mahoatdong3').val(data[1]);
             $('#tenhoatdong3').val(data[2]);
         });
     });


     $(document).ready(function() {
         $("#myInput").on("keyup", function() {
             var value = $(this).val().toLowerCase();
             $("#myTable tr").filter(function() {
                 $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
             });
         });
     });
 </script>
 
 <script>
// Disable form submissions if there are invalid fields
(function() {
  'use strict';
  window.addEventListener('load', function() {
    // Get the forms we want to add validation styles to
    var forms = document.getElementsByClassName('needs-validation');
    // Loop over them and prevent submission
    var validation = Array.prototype.filter.call(forms, function(form) {
      form.addEventListener('submit', function(event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        form.classList.add('was-validated');
      }, false);
    });
  }, false);
})();
</script>