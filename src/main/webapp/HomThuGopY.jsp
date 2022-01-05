<%@page import="java.util.List"%>
<%@page import="Model.GopY"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hòm thư góp ý</title>
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
.span-1{
	font-weight: 700;
	
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
					SinhVien sinhVien = (SinhVien) request.getAttribute("sinhVien");
				%>
				<div class="container-fluid">
					<h3 style="text-align: center">HÒM THƯ GÓP Ý</h3>
					<hr>
					<div class="row">
						<div class="col-lg-7" style="margin-top: 10px; margin-bottom: 10px">
							<form action="<%=request.getContextPath()%>/HomThuGopY_insert" method="POST">
								<div class="form-group">
									<input type="text" class="form-control" id="tieude" placeholder="Nhập tiêu đề" name="txtTieuDe" required autocomplete="off">
									<div class="valid-feedback">Hợp lệ.</div>
									<div class="invalid-feedback">Vui lòng không được bỏ trống thông tin!</div>
								</div>
								<div>
									<input name="txtMaSV" type="text" hidden value="<%=sinhVien.getMaSinhVien()%>">
									<input name="txtNgayGopY" type="text" hidden value="<%= java.time.LocalDate.now() %>">
																	
								</div>
								<div class="form-group">
									<textarea class="form-control" name="txtNoiDung" id="noidung" cols="30" rows="15" placeholder="Nhập nội dung" required></textarea>
									<div class="valid-feedback">Hợp lệ.</div>
									<div class="invalid-feedback">Vui lòng không được bỏ trống thông tin!</div>
								</div>
								<button type="submit" name="btnGui" class="btn btn-primary btn-block">Gửi góp ý</button>
							</form>
						</div>
						<div class="col-lg-5 border" style="margin-top: 10px; margin-bottom: 10px">
							<h5 style="text-align: center; margin-top: 10px">NỘI DUNG GÓP Ý</h5>
							<hr>
							<div style="height: 400px; overflow-y: scroll;">
							
								
			                            <%		
											List<GopY> list =( List<GopY>) request.getAttribute("gopYs");
											
											for (GopY gopYs : list) {	
										%>
								<div class="card">
		                            <div class="card-body">
		                                <button type="button" class="btn btn-block" data-toggle="modal" data-target="#myModal<%= gopYs.getId()%>">
		                                    <div class="row">
		                                        <div class="col-sm-6">
		                                            <h6 class="card-title" style="margin-left: auto;text-align: left;"><%= sinhVien.getHoTen()%></h6>
		                                        </div>
		                                        <div class="col-sm-6">
		                                            <h6 style="margin-right: auto;text-align: right;">
		                                                <small>
		                                                    <i>
		                                                        Thời gian: <%= gopYs.getNgayGopY() +"-"+ gopYs.getGioGopY() %>
		                                                    </i>
		                                                </small>
		                                            </h6>
		                                        </div>
		                                    </div>
		
		                                    <p class="card-text" style="text-align: left;">Tiêu đề: <%= gopYs.getTieuDe() %></p>
		                                </button>
		
		                                <!-- The Modal -->
		                                <div class="modal fade" id="myModal<%= gopYs.getId()%>">
		                                    <div class="modal-dialog modal-xl">
		                                        <div class="modal-content">
		
		                                            <!-- Modal Header -->
		                                            <div class="modal-header">
		                                                <div style="display: block; margin-left:20px">
		                                                   <p style="font-size:20px"><span class="span-1">Tiêu đề:</span>  <%= gopYs.getTieuDe() %><p>
		                                                </div>
		
		                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
		                                            </div>
		
		                                            <!-- Modal body -->
		                                            <div class="modal-body">
		
		                                                <div class="container mt-3">
		                                                    <div class="media border p-3">
		                                                        <div class="media-body">
		                                                            <div class="p-3">
		                                                                <div class="row">
		                                                                    <div class="col-sm-6">
		                                                                        <h5 class="card-title span-1" style="margin-left: auto;text-align: left;">Nội dung</h5>
		                                                                    </div>
		                                                                    <div class="col-sm-6">
		                                                                        <h6 style="margin-right: auto;text-align: right;">
		                                                                            <small>
		                                                                                <i>
		                                                                                    Thời gian:  <%= gopYs.getNgayGopY() +"  "+ gopYs.getGioGopY() %>
		                                                                                </i>
		                                                                            </small>
		                                                                        </h6>
		                                                                    </div>
		                                                                </div>
		                                                                <hr>
		                                                                <h4>
		                                                                    <small>
		                                                                        <pre style="white-space: pre-line; font-size: 18px; font-family: Cambiria;">
		                                                                            <%= gopYs.getNoiDung() %>
		                                                                        </pre>
		                                                                    </small>
		                                                                </h4>
		                                                            </div>
		
		                                                        </div>
		                                                    </div>
		                                                </div>
		
		                                            </div>
		
		                                            <!-- Modal footer -->
		                                            <div class="modal-footer">
		                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Thoát</button>
		                                            </div>
		
		                                        </div>
		                                    </div>
		                                </div>
		                                
		                            </div>
		                        </div>
		                      <% } %>
							
							</div>
						</div>
					</div>
					<br>
				</div>
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