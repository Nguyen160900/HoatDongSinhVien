<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resource/style/style-DangNhap.css">
</head>
<body>

	<div class="nentrang">
		<div class="formdangnhap" style="width: 600px">
			<form action="<%=request.getContextPath()%>/LoginUser" method="POST" class="needs-validation" novalidate>
				<img src="${pageContext.request.contextPath}/resource/image/logo1.png" class="rounded">
				<h3 style="text-align: center">
					<b>TRƯỜNG ĐẠI HỌC QUY NHƠN</b>
				</h3>
				<h3 style="text-align: center">
					<i>Hoạt động sinh viên</i>
				</h3>
				<span>------ &#10020; ------</span>
				<style>
				span {
					content: "\2724";
					margin-bottom: 15px;
				}
				</style>
				<br>
				<br>
				<div class="form-group">
					<input type="text" class="form-control" id="Username" placeholder="Nhập mã đăng nhập" name="Username" required>
					<div class="valid-feedback">Hợp lệ.</div>
					<div class="invalid-feedback">Vui lòng không được bỏ trống
						thông tin!</div>
				</div>
				<div class="form-group">
					<input type="password" class="form-control" name="Password" id="Password" placeholder="Nhập mật khẩu" required></input>
					<div class="valid-feedback">Hợp lệ.</div>
					<div class="invalid-feedback">Vui lòng không được bỏ trống thông tin!</div>
				</div>
				<div class="form-group">
					<%
						String err = request.getParameter("err");
						if ("1".equals(err)) {
							out.print("<div class='alert alert-danger'><strong>Đăng nhập thất bại</strong></div>");
						}
					%>
				</div>
				<button type="submit" name="btn_submit"
					class="btn btn-outline-dark btn-lg">
					<b>ĐĂNG NHẬP</b>
				</button>
				<br>
			</form>

			<br>
		</div>
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