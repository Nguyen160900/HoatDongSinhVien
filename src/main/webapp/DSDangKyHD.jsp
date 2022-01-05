<%@page import="Service.LopService"%>
<%@page import="Model.DangKyHoatDong"%>
<%@page import="Model.Lop"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Out"%>
<%@page import="Service.DSDangKyHDService"%>
<%@page import="java.util.List"%>
<%@page import="Model.HoatDong"%>
<%@page import="Model.KhoaHoc"%>
<%@page import="Service.KhoaHocService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Danh sách đăng ký hoạt động</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/style/style-footer.css">
<style>
.jumbotron {
	background-image: linear-gradient(to bottom right, #1E90FF, #FFFFFF);
	display: flex;
}

.btn {
	margin: 15px 15px;
}

.btn1 {
	margin: 15px 15px;
	width: 100px;
}

.btn2 {
	border: 2px solid black;
	background-color: #faf5f1;
	color: black;
	font-size: 16px;
	cursor: pointer;
	width: 150px;
	height:45px
}

.form-control {
	margin: 15px 0;
}

.mybtn {
	display: none
}

.show {
	display: block;
}

#tbsv3 {
	/* border:5px solid none; */
	max-width: 1200px;
	width: 100%;
	overflow: auto;
	overflow: scroll;
	height: 700px;
	margin: 10px auto;
}

.info {
	border-color: #2196F3;
	color: dodgerblue;
	font-weight:600;
}

.info:hover {
	background: #007bff;
	color: white;
}
.txtText{
	text-align: center; 
	vertical-align: inherit;
}
</style>
</head>

<%
HoatDong hoatDong = (HoatDong) request.getAttribute("hoatDong");
UserService userService = new UserService();
DSDangKyHDService dsDangKyHDService = new DSDangKyHDService();
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

				<div>
					<h3 style="text-align: center">DANH SÁCH SINH VIÊN THAM GIA HOẠT ĐỘNG</h3>
					<h5 style="text-align: center">
						Năm học: <%=hoatDong.getNamHoc()%> - Học kỳ: <%=hoatDong.getHocKy()%>
					</h5>
					<br>
					<div style="text-align: center; font-size: 18px;">
						<span style="font-weight:500">Hoạt động: </span> <%=hoatDong.getTenHoatDong()%>
					</div>
					<div style="text-align: center; font-size: 18px;margin-top:10px">
						<span style="font-weight:500; ">Ngày diễn ra: </span> <%=hoatDong.getNgayDienRa()%> <span style="font-weight:500; "> <=> Thời gian diễn ra: </span> <%=hoatDong.getThoiGianDienRa()%>
					</div>
					<br>					
					<hr>
									
					<div class="row">
						<div class="col-sm-8" style="justify-content: center; display: flex;">
							<input class="form-control" style="height:45px" type="text" id="myInput" name="txtTimKiem" placeholder="Nhập nội dung cần cần tìm" aria-label="Search" autocomplete="off">
						</div>
						<div class="col-sm-4" style="justify-content: center; display: flex;">							
							<form action="<%=request.getContextPath()%>/DSDangKyHD_LamMoi" method="POST">
								<input type="text" name="txtMaHD" value="<%out.print(Integer.parseInt(request.getParameter("maHoatDong")));%>" hidden style="font-size: 18px;">
								<button class="btn info btn2" type="submit" name="btnLamMoi">Làm mới</button>
							</form>
							<button class="btn info btn2" type="submit" id="btnchon" name="btnChon" id="btnchon">Chọn</button>
						</div>
					</div>
					
					<form action="<%=request.getContextPath()%>/DSDangKyHD_Loc" method="POST">
						<div class="row">	
							<div class="col-sm-1"></div>
							<div class="col-sm-2" style="justify-content: center; display: flex; margin: auto">
								<span>Lọc theo:</span>
							</div>
							<div class="col-sm-2" style="justify-content: center; display: flex;">
								<select name="cboKhoaHoc" class="form-control" id="cboKhoaHoc">
									<option value="" selected="selected">--Chọn khóa học--</option>
									<%
										KhoaHocService khoaHocService = new KhoaHocService();
										List<KhoaHoc> listKhoaHoc = (List<KhoaHoc>) khoaHocService.getKhoaHoc();
										for(KhoaHoc khoaHoc:listKhoaHoc)
										{
											out.print("<option value='"+ khoaHoc.getMaKhoaHoc() +"' >"+khoaHoc.getMaKhoaHoc()+"</option>");
										}
									%>
								</select>
							</div>
							<div class="col-sm-2" style="justify-content: center; display: flex;">
								<select name="cboLop" class="form-control" id="cboLop">
									<option value="" selected="selected">--Chọn lớp--</option>
									<%
										LopService lopService = new LopService();
										List<Lop> listLops = (List<Lop>) lopService.getLop_1(Integer.parseInt(request.getParameter("maHoatDong")));
										for(Lop lop:listLops){
											out.print("<option value='"+ lop.getMaLop()+"' >"+lop.getTenLop()+"</option>");
										}
									%>
								</select>
							</div>
							<div class="col-sm-2" style="justify-content: center; display: flex;">
								<select name="cboTinhTrang_loc" class="form-control">
									<option value="">--Chọn tình trạng--</option>
									<option value="Đăng ký">Đăng ký</option>
									<option value="Không tham gia">Không tham gia</option>
									<option value="Tham gia">Tham gia</option>												
								</select>
							</div>
							
							<div class="col-sm-2" style="justify-content: center; display: flex;">
								<input type="text" name="txtMaHDloc" value="<% out.print(Integer.parseInt(request.getParameter("maHoatDong"))); %>" hidden style="font-size: 18px;">
								
								<button class="btn btn-success" type="submit" name="btnLoc">Lọc dữ liệu</button>
							</div>
							<div class="col-sm-1"></div>
						</div>
					</form>
						<hr>
						
						<div class = "row">
							<div class="col-sm-9"></div>
							<div class="col-sm-3" style="justify-content: center; display: flex; font-size: 18px;">
								<h5 style="font-weight:700; margin-bottom:15px">Số lượng: 
								<%
									if(request.getAttribute("soLuong") != null){
										out.print(request.getAttribute("soLuong"));
									}
									else{
										out.print(request.getAttribute("soLuong_loc"));
									}
								%>
								</h5>							
							</div>
						</div>	
				</div>

				
				<form name="formManagement" action="" method="POST">
					<div class="table-responsive" style="overflow-y: scroll; height: 650px;">
						<input type = "text" name="txtMaHD1" hidden value = "<%out.print(Integer.parseInt(request.getParameter("maHoatDong")));%>">
						<table id="myTable" class="table table-bordered table-hover"
							style="width: 100%">
							<thead>
								<tr style="text-align: center">
									<th class = "myhidden mybtn txtText">Chọn</th>
									<th style="vertical-align: inherit; text-align: center">STT</th>
									<th style="vertical-align: inherit; text-align: center">Mã sinh viên</th>
									<th style="vertical-align: inherit; text-align: center">Họ tên</th>
									<th style="vertical-align: inherit; text-align: center">Lớp</th>
									<th style="vertical-align: inherit; text-align: center">Tham gia</th>
									<th style="vertical-align: inherit; text-align: center">Cập nhật</th>
									<th style="vertical-align: inherit; text-align: center">Xóa</th>
								</tr>
							</thead>
							<tbody>
								<%
								List<DangKyHoatDong> dsDangKyHoatDongs;

								int stt = 0;
								
								if(request.getAttribute("dsDangKyHoatDongs") != null){
									dsDangKyHoatDongs = (List<DangKyHoatDong>) request.getAttribute("dsDangKyHoatDongs");
								}
								else{
									dsDangKyHoatDongs = (List<DangKyHoatDong>) request.getAttribute("dsDangKyHoatDongs_loc");
								}

								for (DangKyHoatDong dsDangKyHoatDong : dsDangKyHoatDongs) {
									stt += 1;
								%>
								<tr id="thongtin">
									<td class="cot myhidden mybtn" style="vertical-align: inherit; text-align: center">
										<div class="form-check-inline" style="padding:12px; margin:0px">
											<input type="checkbox" class="form-check-input" name="mangthamgia" style="text-align: center; vertical-align: inherit;" value="<%=dsDangKyHoatDong.getMaSinhVien() %>">
										</div>
									</td>
									<td class="cot" style="text-align: center; vertical-align: inherit;"><%=stt%></td>
									<td class="cot" style="text-align: center; vertical-align: inherit;"><%=dsDangKyHoatDong.getMaSinhVien()%></td>
									<td class="cot" style="text-align: center; vertical-align: inherit;"><%=dsDangKyHoatDong.getHoTen()%></td>
									<td class="cot" style="text-align: center; vertical-align: inherit;"><%=dsDangKyHoatDong.getTenLop()%></td>
									<td class="cot" style="text-align: center; vertical-align: inherit;"><%=dsDangKyHoatDong.getTinhTrang()%></td>
									<td class="cot" style="text-align: center; vertical-align: inherit;">
										<a class="btn btn-link editbtn" data-toggle="modal" data-target="#editModal" style="margin: 0 0;"><i class="fas fa-edit"></i></a>
									</td>
									<td class="cot" style="text-align: center; vertical-align: inherit;">
										<a class="btn btn-link xoabtn" data-toggle="modal" data-target="#xoaModal" style="margin: 0 0;"><i class="fas fa-trash-alt"></i></a>
									</td>

								</tr>

								<%
								}
								%>
							</tbody>
						</table>
						<!-- <div id="mytr" class="mybtn"> -->
						<div class="mybtn myhidden">
							<button hidden value="" name="btnName"></button>
							<button class='btn btn2 info' type='submit' value="Tham gia" name='btnThamGia' onclick="ThamGia()">Tham gia</button>
							<button class='btn btn2 info' type='submit' value="Không tham gia" name='btnKhongThamGia' onclick="KhongThamGia()">Không tham gia</button>
							<button class='btn btn2 info' type='submit' value="Xóa" name='btnXoa1' onclick="Xoa()">Xóa</button>
						</div>
						<br>
					</div>
				</form>

				<script>
                     $(document).ready(function() {
                         $("#myInput").on("keyup", function() {
                             var value = $(this).val().toLowerCase();
                             $("#myTable #thongtin").filter(function() {
                                 $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                             });
                         });
                     });
                 </script>

				<!-- MODAL XÓA-->
				<form action="<%=request.getContextPath()%>/DSDangKyHD/delete" method="POST">
					<div class="modal fade" id="xoaModal">
						<div class="modal-dialog modal-lg"  style="display: flex; justify-content: center;">
							<div class="modal-content" style="width: 400px;">
								<div class="modal-header">
									<h4 class="modal-title">Xóa sinh viên</h4>
									<button type="button" class="btn" data-dismiss="modal">
										<b>X</b>
									</button>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-sm-12" style="justify-content: center; display: block;">
											<input type="text" name="txtMaHD_Xoa" value="<%out.print(Integer.parseInt(request.getParameter("maHoatDong")));%>" hidden style="font-size: 18px;"> 
											<input type="text"class="form-control" name="txtMasv" id="masv" hidden style="font-size: 18px; pointer-events: none;">											
										</div>
									</div>
									<h5>Bạn có chắc chắn muốn xóa sinh viên này?</h5>
								</div>
								<div class="modal-footer">
									<button type="submit" class="btn btn-primary" name="btnXoa" value="btnXoa">Xóa</button>
									<button type="button" class="btn btn-secondary" data-dismiss="modal">Thoát</button>
								</div>
							</div>
						</div>
					</div>
				</form>
				<!-- END MODAL -->

				<!-- MODAL CHỈNH SỬA -->
				<form action="<%=request.getContextPath()%>/DSDangKyHD/update" method="POST">

					<div class="modal fade" id="editModal">
						<div class="modal-dialog modal-lg" style="display: flex; justify-content: center;">
							<div class="modal-content" style="width: 350px;">
								<div class="modal-header">
									<div>
										<h4 class="modal-title" style="text-align: left;">Cậpnhật</h4>
									</div>
									<button type="button" class="close" data-dismiss="modal">&times;</button>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-sm-6" style="justify-content: center; display: block;">
											<input style="font-size: 18px; pointer-events: none;" type="text" class="form-control" hidden name="txtMaHD_Capnhat" id="mahoatdong" value="<%out.print(Integer.parseInt(request.getParameter("maHoatDong")));%>" >
										</div>
										<div class="col-sm-6" style="justify-content: center; display: block;">
											<input style="font-size: 18px; pointer-events: none;" type="text" class="form-control" hidden name="txtMaSinhVien" id="masinhvien">
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12" style="justify-content: center; display: block;">
											<p>Tình trạng</p>
											<select name="cboTinhTrang" class="form-control" id="cbotinhtrang">
												<!-- <option value="" selected="selected">--Chọn tình trạng--</option> -->
												<option value="Đăng ký">Đăng ký</option>
												<option value="Không tham gia">Không tham gia</option>
												<option value="Tham gia">Tham gia</option>												
											</select>
										</div>
									</div>

									<br>
									<button type="submit" class="btn btn-primary btn-block" id="btnCapNhat" name="btnCapNhat" value="btnCapNhat" style="margin: 0 0;">Cập nhật</button>
								</div>
							</div>
						</div>
					</div>
				</form>
				<!-- END MODAL -->
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
// Gọi modal xóa
$(document).ready(function() {
    $('.xoabtn').on('click', function() {
        $('#xoaModal').modal('show');

        $tr = $(this).closest('tr');

        var data = $tr.children("td").map(function() {
            return $(this).text();
        }).get();

        console.log(data);
        $('#masv').val(data[2]);
    });
});
    
    // Gọi modal cập nhật 
    $(document).ready(function() {
        $('.editbtn').on('click', function() {
            $('#editModal').modal('show');

            $tr = $(this).closest('tr');

            var data = $tr.children("td").map(function() {
                return $(this).text();
            }).get();

            console.log(data);
           
            $('#masinhvien').val(data[2]);
            $('#cbotinhtrang').val(data[5]);
        });
    });
</script>

<script>
document.getElementById("btnchon").onclick = function() {myFunction()};

function myFunction() {
  /* document.getElementById("mytr").classList.toggle("show"); */
/*   document.getElementById("myth").classList.toggle("show")
  document.getElementById("mytd").classList.toggle("show"); */
  
  var myhidden = document.getElementsByClassName('myhidden');
  for (var i = 0; i < myhidden.length; i += 1) {
	  myhidden[i].classList.toggle("show");
  }
}
</script>

<script language="JavaScript">
            
     function ThamGia(){
         document.formManagement.btnName.value="Tham gia";
         formManagement.submit();
         formManagement.action="<%=request.getContextPath()%>/DSDangKyHD_thamgia";
         
     }
         
     function KhongThamGia(){
         document.formManagement.btnName.value="Không tham gia";
         formManagement.submit();
         formManagement.action="<%=request.getContextPath()%>/DSDangKyHD_khongthamgia";
     }
     
     function Xoa(){
         document.formManagement.btnName.value="Xóa";
         formManagement.submit();
         formManagement.action="<%=request.getContextPath()%>/DSDangKyHD_xoa";
	}
</script>

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