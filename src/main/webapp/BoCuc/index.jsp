<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es" dir="ltr">
<head>
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
<meta charset="utf-8">
<title>Đăng nhập</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/style/style-Login.css">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700;800&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Readex+Pro:wght@300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/style/style-DangNhap.css">
</head>
<body>
	<div class="main">
		<div class="container a-container" id="a-container">
			<form class="form" id="a-form" method="POST" action="<%=request.getContextPath()%>/LoginUser">
				<h2 class="form_title title">ĐĂNG NHẬP</h2>
				<span class="form__span">Hãy nhập thông tin sinh viên của bạn để đăng nhập</span> 
				<input class="form__input" type="text" name="Username" placeholder="Nhập mã sinh viên"> 
				<input class="form__input" type="password" name="Password" placeholder="Nhập mật khẩu">
				<button type="submit" class="form__button button">ĐĂNG NHẬP</button>
					<%
						String err = request.getParameter("err");
						if ("1".equals(err)) {
							out.print("<div class='alert alert-danger'><strong>Đăng nhập thất bại</strong></div>");
						}
					%>
			</form>
		</div>
		<div class="container b-container" id="b-container">
			<form class="form" id="b-form" method="POST" action="<%=request.getContextPath()%>/LoginAdmin">
				<h2 class="form_title title">ĐĂNG NHẬP</h2>
				<span class="form__span">Hãy nhập thông tin quản trị viên của bạn để đăng nhập</span> 
				<input class="form__input" type="text" name="UsernameAD" placeholder="Nhập tên đăng nhập"> 
				<input class="form__input" type="password" name="PasswordAD" placeholder="Nhập mật khẩu">
				<button type="submit" class="form__button button">ĐĂNG NHẬP</button>
					<%
						String err2 = request.getParameter("err2");
						if ("1".equals(err2)) {
							out.print("<div class='alert alert-danger'><strong>Đăng nhập thất bại</strong></div>");
						}
					%>
			</form>
		</div>
		<div class="switch" id="switch-cnt">
			<div class="switch__circle"></div>
			<div class="switch__circle switch__circle--t"></div>
			<div class="switch__container" id="switch-c1">
				<h2 class="switch__title title">Trường đại học Quy Nhơn</h2>
				<p class="switch__description description">Bạn là sinh viên hay quản trị viên?</p>
				<button class="button2 switch-btn"><span>QUẢN TRỊ</span></button>
			</div>
			<div class="switch__container is-hidden" id="switch-c2">
				<h2 class="switch__title title">Trường đại học Quy Nhơn</h2>
				<p class="switch__description description">Bạn là sinh viên hay quản trị viên?</p>
				<button class="button1 switch-btn"><span>SINH VIÊN</span></button>
			</div>
		</div>
	</div>
</body>
</html>
<script src="${pageContext.request.contextPath}/resource/js/script-Login.js"></script>


