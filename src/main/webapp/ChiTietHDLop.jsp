<%@page import="Service.QuanLySinhVienDKHDLopService"%>
<%@page import="Model.DangKyHoatDong"%>
<%@page import="java.util.List"%>
<%@page import="Model.HoatDong"%>
<%@page import="Service.HoatDongService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Quản lý đăng ký hoạt động lớp</title>
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
				if (username != null) {

                   	QuanLySinhVienDKHDLopService qlDkhdLopService = new QuanLySinhVienDKHDLopService();
				%>

				<h3 style="text-align: center">QUẢN LÝ ĐĂNG KÝ HOẠT ĐỘNG SINH VIÊN</h3>
				<h4 style="text-align: center">Lớp: <%=sv.getTenLop()%></h4>
				<br>
				
				<%
					HoatDongService hoatDongService = new HoatDongService();
					HoatDong hoatDong = new HoatDong();
					
					hoatDong = hoatDongService.getHoatDong(Integer.parseInt(request.getParameter("mahd")));
				%>

				<div class="border border-primary" style="padding: 10px;">
					<h4>
						<small>Hoạt động: <%=hoatDong.getTenHoatDong() %></small>
					</h4>
					<h4>
						<small>Ngày diễn ra: <%=hoatDong.getNgayDienRa() %></small>
					</h4>
					<h4>
						<small>Năm học: <%=hoatDong.getNamHoc() %> - Học kỳ: <%=hoatDong.getHocKy() %></small>
					</h4>
				</div>

				<hr>
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModalAdd" name="btnThem" style="margin: 0 0 15px 0;">
					Thêm sinh viên đăng ký hoạt động
				</button>
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModalUpdate" name="btnSua" style="margin: 0 0 15px 0;">
					Cập nhật sinh viên tham gia hoạt động
				</button>
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModalDelete" name="btnXoa" style="margin: 0 0 15px 0;">
					Xóa sinh viên tham gia hoạt động
				</button>

				<hr>
				<div class="input-group mb-3">
					<input type="text" class="form-control" placeholder="Nhập thông tin cần tìm kiếm ....." id="myInput" style="font-size: 18px">
				</div>

				<div class="table-responsive">
					<table id="myTable" class="table table-bordered table-hover"
						style="width: 100%">
						<thead>
							<tr style="text-align: center">
								<th style="vertical-align: inherit; text-align: center">STT</th>
								<th style="vertical-align: inherit; text-align: center">Mã sinh viên</th>
								<th style="vertical-align: inherit; text-align: center">Họ tên</th>
								<th style="vertical-align: inherit; text-align: center">Lớp</th>
								<th style="vertical-align: inherit; text-align: center">Tham gia</th>
								<th style="vertical-align: inherit; text-align: center">Xóa</th>
							</tr>
						</thead>
						<tbody>
							
							<%
								List<DangKyHoatDong> list = (List<DangKyHoatDong>)request.getAttribute("list_HD_SV");
								int stt=0;
								for(DangKyHoatDong dangKyHoatDong: list){
									stt = stt + 1;
							%>
							
							<tr id="thongtin">
								<td>
									<p style="margin: 7px auto; text-align: center; vertical-align: inherit;"> <%=stt %> </p>
								</td>
								<td>
									<p style="margin: 7px auto; vertical-align: inherit; text-align: center;"> <%=dangKyHoatDong.getMaSinhVien() %> </p>
								</td>
								<td>
									<p style="margin: 7px auto; text-align: center; vertical-align: inherit; width: 250px"> <%=dangKyHoatDong.getHoTen() %> </p>
								</td>
								<td>
									<p style="margin: 7px auto; text-align: center; vertical-align: inherit; width: 200px"> <%=dangKyHoatDong.getTenLop() %> </p>
								</td>
								<td>
									<p style="margin: 7px auto; text-align: center; vertical-align: inherit; width: 120px"> <%=dangKyHoatDong.getTinhTrang() %> </p>
								</td>
								<td style="text-align: center; vertical-align: inherit;">
									<button class=" btn btnchung btnXoa1" type="button" name="btnXoa" data-bs-toggle="modal" data-bs-target="#Xoa1">
										<img src="${pageContext.request.contextPath}/resource/image/delete.png" style="max-width: 30px; height: auto;">
									</button>
								</td>
							</tr>
							
							<% } %>
							
						</tbody>
					</table>
					<br>
				</div>

				<script>
					$(document).ready(function() {
						$("#myInput").on("keyup",function() {
							var value = $(this).val().toLowerCase();
							$("#myTable #thongtin").filter(function() {
								$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
								});
							});
						});
				</script>

				<!-- MODAL XÓA-->
				<form action="<%=request.getContextPath()%>/QuanLySinhVienDKHDLop/delete1" method="post">
				<div class="modal fade" id="Xoa1">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="staticBackdropLabel">Xóa sinh viên</h5>
								<button type="button" class="btn" data-dismiss="modal"> <b>X</b> </button>
							</div>
							<div class="modal-body" style="display: flex; justify-content: center; margin-right: 10px; margin-top: 7px">
								<table hidden>
									<tr>
										<td><input type="text" name="txtMaHoatDong_delete1" value="<% out.print(Integer.parseInt(request.getParameter("mahd"))); %>" readonly></td>
									</tr>
									<tr>
										<td><input type="text" name="txtMaLop_delete1" value="<% out.print(request.getParameter("malop")); %>" readonly></td>
									</tr>
									<tr>
										<td><input type="text" name="masv" id="masv"></td>
									</tr>
								</table>
								<br>
								<h5>Bạn có chắc chắn muốn xóa sinh viên này?</h5>
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-primary" name="btnEdit" value="btnEdit" onsubmit="return false">Xóa</button>
								<button type="button" class="btn btn-secondary" data-dismiss="modal">Thoát</button>
							</div>
						</div>
					</div>
				</div>
				</form>
				<!-- END MODAL -->

				<!-- The Modal Thêm-->
				<form action="<%=request.getContextPath()%>/QuanLySinhVienDKHDLop/insert" method="post">
				<div class="modal fade" id="myModalAdd" ata-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">

							<!-- Modal Header -->
							<div class="modal-header">
								<h4 class="modal-title">Thêm sinh viên đăng ký hoạt động</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>

							<!-- Modal body -->
							<div class="modal-body">
								<input type="text" name="txtMaLop_insert" value="<% out.print(request.getParameter("malop")); %>" readonly hidden>
								<input type="text" name="txtMaHoatDong_insert" value="<% out.print(Integer.parseInt(request.getParameter("mahd"))); %>" readonly hidden>
								<h4 hidden>
									<small>Mã hoạt động: <%=hoatDong.getMaHoatDong() %></small>
								</h4>
								<h4>
									<small>Hoạt động: <%=hoatDong.getTenHoatDong() %></small>
								</h4>
								<h4>
									<small>Ngày diễn ra: <%=hoatDong.getNgayDienRa() %></small>
								</h4>
								<h4>
									<small>Năm học: <%=hoatDong.getNamHoc() %> - Học kỳ: <%=hoatDong.getHocKy() %></small>
								</h4>
								<h4>
									<small>Thêm sinh viên đăng ký:</small>
								</h4>

								<div style="overflow-y: scroll; height: 400px;">
									<% 
										List<SinhVien> listSinhViens = (List<SinhVien>) request.getAttribute("listSinhViens");
										for(SinhVien sinhVienLop:listSinhViens){
											
									%>
                                    <div class="form-check">
                                        <label class="form-check-label form-control">
                                                
                                           <%
	                                           	String thongTinSV_Lop = sinhVienLop.getMaSinhVien() + " - " + sinhVienLop.getHoTen();
                                        		
	                                           	Boolean ktra = qlDkhdLopService.KtraSV_ThamGia(sinhVienLop.getMaSinhVien(), hoatDong.getMaHoatDong());
	                                           	if(ktra){
	                                           		out.print("<input type='checkbox' class='form-check-input' name='sv' value='"+ sinhVienLop.getMaSinhVien() +"' checked >" + thongTinSV_Lop);
	                                           	}else{
	                                           		out.print("<input type='checkbox' class='form-check-input' name='sv' value='"+ sinhVienLop.getMaSinhVien() +"'>" + thongTinSV_Lop);
	                                           	}
                                           %>
                                                
                                        </label>
                                    </div>
                                    <%
										}
                                    %>
								</div> 

							</div>

							<!-- Modal footer -->
							<div class="modal-footer">
								<button type="submit" class="btn btn-primary" name="btnAdd" value="btnAdd" onsubmit="return false">Thêm</button>
								<button type="button" class="btn btn-secondary" data-dismiss="modal">Thoát</button>
							</div>
						</div>
					</div>
				</div>
				</form>
				<!-- END MODAL --> 

				<!-- The Modal Cập nhật-->
				<form action="<%=request.getContextPath()%>/QuanLySinhVienDKHDLop/update" method="post">
				<div class="modal fade" id="myModalUpdate" ata-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">

							<!-- Modal Header -->
							<div class="modal-header">
								<h4 class="modal-title">Cập nhật sinh viên đăng ký hoạt động</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>

							<!-- Modal body -->
							<div class="modal-body">
								<input type="text" name="txtMaLop_update" value="<% out.print(request.getParameter("malop")); %>" readonly hidden>
								<input type="text" name="txtMaHoatDong_update" value="<% out.print(Integer.parseInt(request.getParameter("mahd"))); %>" readonly hidden>
								<h4 hidden>
									<small>Hoạt động: <%=hoatDong.getMaHoatDong() %></small>
								</h4>
								<h4>
									<small>Hoạt động: <%=hoatDong.getTenHoatDong() %></small>
								</h4>
								<h4>
									<small>Ngày diễn ra: <%=hoatDong.getNgayDienRa() %></small>
								</h4>
								<h4>
									<small>Năm học: <%=hoatDong.getNamHoc() %> - Học kỳ: <%=hoatDong.getHocKy() %></small>
								</h4>
								<h4>
									<small>Cập nhật sinh viên đăng ký:</small>
								</h4>

								<input class="form-control" id="myInput1" type="text" placeholder="Nhập thông tin tìm kiếm" style="margin: 15px auto;">

								<div style="overflow-y: scroll; height: 350px;">
									<table style="width: 100%;" class="table-bordered"
										id="myTable1">
										<tr style="text-align: center;">
											<th style="padding: 5px">STT</th>
											<th style="padding: 10px">Sinh viên</th>
											<th style="width: 60px; padding: 15px">Tham gia</th>
											<th style="width: 60px; padding: 10px">Không tham gia</th>
										</tr>
										<% 
                                                int stt_update=0;
												for(DangKyHoatDong dangKyHoatDong_update: list){
													stt_update = stt_update + 1;
													String chuoi_update = dangKyHoatDong_update.getMaSinhVien() + " - " + dangKyHoatDong_update.getHoTen();
										%> 
                                        <tr id="thongtin1">
                                            <td style="text-align: center; padding: 10px"><%=stt_update %></td>
                                            <td><%=chuoi_update %></td>
                                            <td>
                                                <%
                                                String thamGia = "Tham gia";
                                                Boolean ktra_ThamGia = qlDkhdLopService.KtraSV_ThamGia_HoatDong(dangKyHoatDong_update.getMaSinhVien(), hoatDong.getMaHoatDong(), thamGia);
	                                           	if(ktra_ThamGia){
	                                           		out.print("<input type='checkbox' style='margin: auto; text-align: center; display: block;' name='mangthamgia' value='"+ dangKyHoatDong_update.getMaSinhVien() +"' checked >");
	                                           	}else{
	                                           		out.print("<input type='checkbox' style='margin: auto; text-align: center; display: block;' name='mangthamgia' value='"+ dangKyHoatDong_update.getMaSinhVien() +"'>");
	                                           	}   
	                                           	%>
                                            </td>
                                            <td>
                                                <%
                                                String khongThamGia = "Không tham gia";
                                                Boolean ktra_khongThamGia = qlDkhdLopService.KtraSV_ThamGia_HoatDong(dangKyHoatDong_update.getMaSinhVien(), hoatDong.getMaHoatDong(), khongThamGia);
	                                           	if(ktra_khongThamGia){
	                                           		out.print("<input type='checkbox' style='margin: auto; text-align: center; display: block;' name='mangkhongthamgia' value='"+ dangKyHoatDong_update.getMaSinhVien() +"' checked >");
	                                           	}else{
	                                           		out.print("<input type='checkbox' style='margin: auto; text-align: center; display: block;' name='mangkhongthamgia' value='"+ dangKyHoatDong_update.getMaSinhVien() +"'>");
	                                           	}   
	                                           	%>
                                            </td>
                                        </tr>
                                        <% } %>
									</table>
								</div>

								<script>
									$(document).ready(function() {
										$("#myInput1").on("keyup",function() {
											var value = $(this).val().toLowerCase();
											$("#myTable1 #thongtin1").filter(function() {
												$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
												});
											});
										});
								</script>
							</div>

							<!-- Modal footer -->
							<div class="modal-footer">
								<button type="submit" class="btn btn-primary" name="btnUpdate" value="btnUpdate" onsubmit="return false">Cập nhật</button>
								<button type="button" class="btn btn-secondary" data-dismiss="modal">Thoát</button>
							</div>

						</div>
					</div>
				</div>
				</form>
				<!-- END MODAL -->

				<!-- The Modal Xóa-->
				<form action="<%=request.getContextPath()%>/QuanLySinhVienDKHDLop/delete2" method="post">
				<div class="modal fade" id="myModalDelete" ata-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">

							<!-- Modal Header -->
							<div class="modal-header">
								<h4 class="modal-title">Xóa sinh viên đăng ký hoạt động</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>

							<!-- Modal body -->
							<div class="modal-body">
								<input type="text" name="txtMaLop_delete2" value="<% out.print(request.getParameter("malop")); %>" readonly hidden>
								<input type="text" name="txtMaHoatDong_delete2" value="<% out.print(Integer.parseInt(request.getParameter("mahd"))); %>" readonly hidden>
								<h4 hidden>
									<small>Hoạt động:<%=hoatDong.getMaHoatDong() %></small>
								</h4>
								<h4>
									<small>Hoạt động: <%=hoatDong.getTenHoatDong() %></small>
								</h4>
								<h4>
									<small>Ngày diễn ra: <%=hoatDong.getNgayDienRa() %></small>
								</h4>
								<h4>
									<small>Năm học: <%=hoatDong.getNamHoc() %>- Học kỳ: <%=hoatDong.getHocKy() %></small>
								</h4>
								<h4>
									<small>Xóa sinh viên đăng ký:</small>
								</h4>

								<div style="overflow-y: scroll; height: 400px;">
									<table style="width: 100%;" class="table-bordered">
										<tr style="text-align: center;">
											<th style="padding: 5px">STT</th>
											<th style="padding: 10px">Sinh viên</th>
											<th style="width: 60px; padding: 15px">Xóa</th>
										</tr>
										
										<%
											int stt_delete=0;
											for(DangKyHoatDong dangKyHoatDong_delete: list){
												stt_delete = stt_delete + 1;
												String chuoi_delete = dangKyHoatDong_delete.getMaSinhVien() + " - " + dangKyHoatDong_delete.getHoTen();
										%>
                                               
                                            <tr>
                                                <td style="text-align: center; padding: 10px"><%=stt_delete %></td>
                                                <td><%=chuoi_delete %></td>
                                                <td>
                                                    <input type="checkbox" style="margin: auto; text-align: center; display: block;" name="mangxoa" value="<%=dangKyHoatDong_delete.getMaSinhVien() %>">
                                                </td>
                                            </tr>
										<%	
											}
										%>

									</table>
								</div>
							</div>

							<!-- Modal footer -->
							<div class="modal-footer">
								<button type="submit" class="btn btn-primary" name="btnDelete" value="btnDelete" onsubmit="return false">Xóa SV</button>
								<button type="button" class="btn btn-secondary" data-dismiss="modal">Thoát</button>
							</div>

						</div>
					</div>
				</div>
				</form>
				<!-- END MODAL -->

				<% } %>
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
	$(document).ready(function() {

		$('.btnXoa1').on('click', function() {
			$('#Xoa1').modal('show');

			$tr = $(this).closest('tr');

			var data = $tr.children("td").map(function() {
				return $(this).text();
			}).get();

			console.log(data);

			$('#masv').val(data[1].trim());
			$('#tensv').val(data[2]);
			$('#thamgia').val(data[4]);
		});
	});
</script>