<%@page import="Service.QLHomThuGopYService"%>
<%@ page import="Model.GopY"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý hòm thư góp ý</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.2.0/umd/popper.min.js"></script>
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

	<!-- top đầu trang -->
	<div class="jumbotron text-center"
		style="margin-bottom: 0; padding: 20px">

		<%@ include file="../BoCuc/Top.jsp"%>

	</div>

	<!-- menu của trang / menu user 1 -->
	<%@ include file="../BoCuc/MenuAD.jsp"%>

	<!-- thân của trang -->
	<div class="container-fluid" style="margin-top: 30px;">
		<h4 style="text-align: center">QUẢN LÝ GÓP Ý SINH VIÊN</h4>
		<hr>

		<div class="row">
			<div class="col-sm-1"></div>

			<div class="col-sm-10">
				<%
				if (username != null) {
				%>
				<form action="<%=request.getContextPath()%>/QLHomThuGopY_delete" method="POST">
					<%
					int first = 0, last = 0, pages = 1, stt = 0;

					if (request.getParameter("pages") != null) {
						pages = (int) Integer.parseInt(request.getParameter("pages"));
					}
					//Lấy tổng sản phẩm trong data bằng query select count(id) from name_table với JDBC Connect
					int total = new QLHomThuGopYService().getCount();

					if (total <= 20) {
						first = 0;
						last = total;
					} else {
						first = (pages - 1) * 20;
						last = 20;
					}
					//Lấy ra danh sách sản phẩm
					List<GopY> list = new QLHomThuGopYService().getAll_GopYs(first, last);
					for (GopY gopYs : list) {
						UserService userService = new UserService();
						SinhVien sinhVien = userService.ThongTinDangNhap(gopYs.getMaSinhVien());
					%>
					<div class="card">
						<div class="card-body">
							<button type="button" class="btn btn-block" data-toggle="modal"
								data-target="#myModal<%=gopYs.getId()%>">
								<div class="row">
									<div class="col-sm-6" style="display: flex;">
										<h6 class="card-title" style="text-align: left;">
											<%=sinhVien.getHoTen() + " [" + gopYs.getMaSinhVien() + "]"%>
										</h6>
										
									</div>
									<div class="col-sm-6">
										<h6 style="margin-right: auto; text-align: right;">
											<small> <i> <%="Thời gian: " + gopYs.getNgayGopY() + " - " + gopYs.getGioGopY()%>
											</i>
											</small>
										</h6>
									</div>
								</div>

								<p class="card-text" style="text-align: left;">
									<%="Tiêu đề: " + gopYs.getTieuDe()%>
								</p>
							</button>
							<button class="btn" style="float: right" type="submit" 
							name="txtMaGopY" value="<%=gopYs.getId()%>"> <i class="fas fa-trash-alt"></i></button>

							<!-- The Modal -->
							<div class="modal fade" id="myModal<%=gopYs.getId()%>">
								<div class="modal-dialog modal-xl">
									<div class="modal-content">

										<!-- Modal Header -->
										<div class="modal-header">
											<div style="display: block;">
												<h5 class="modal-title">
													<%=sinhVien.getHoTen() + " [" + gopYs.getMaSinhVien() + "]"%>
												</h5>
												<h5 class="modal-title">
													<%="Thời gian gửi: " + gopYs.getNgayGopY() + " <=> " + gopYs.getGioGopY()%>
												</h5>
												<hr>
												<h5 class="modal-title">
													<%="Tiêu đề: " + gopYs.getTieuDe()%>
												</h5>
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
																	<h5 class="card-title"
																		style="margin-left: auto; text-align: left;">Nội
																		dung</h5>
																</div>
																<div class="col-sm-6">
																	<h6 style="margin-right: auto; text-align: right;">
																		<small> <i> <%="Thời gian gửi: " + gopYs.getNgayGopY() + " - " + gopYs.getGioGopY()%>
																		</i>
																		</small>
																	</h6>
																</div>
															</div>
															<hr>
															<h4>
																<small> <pre
																		style="white-space: pre-line; font-size: 18px; font-family: Cambiria;">
                                                                            <%=gopYs.getNoiDung()%>
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
											<button type="button" class="btn btn-secondary"
												data-dismiss="modal">Thoát</button>
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
					<%
					}
					%>
					<br>
					<ul class="pagination justify-content-center">
						<%
						//Button Previous
						int back = 0;
						if (pages == 0 || pages == 1) {
							back = 1;//Luon la page 1
						} else {
							back = pages - 1;//Neu pages tu 20 tro len thi back tru 1
						}
						%>
						<li class="page-item"><a class="page-link"
							href="QLHomThuGopY?pages=<%=back%>"><i>Previous</i></a></li>
						<%
						//Button Number pages
						int loop = 0, num = 0;
						if ((total / 20) % 20 == 0) {
							num = total / 20;
						} else {
							num = (total + 1) / 20;
						}
						//Nếu total lẻ thêm 1
						if (total % 20 != 0) {
							loop = (total / 20) + 1;

						} else {
							//Nếu total chẵn nhỏ hơn fullpage và # fullPage thì thêm 1
							if (total < (num * 20) + 20 && total != num * 20) {
								loop = (total / 20) + 1;
							} else {
								//Nếu bằng fullPage thì không thêm
								loop = (total / 20);
							}
						}
						//Lap so pages
						for (int i = 1; i <= loop; i++) {
						%>
						<%
						if (pages == i) {
						%>

						<li class="page-item"><span><a class="page-link"
								href="QLHomThuGopY?pages=<%=i%>"><%=i%></a></span></li>
						<%
						} else {
						%>
						<li class="page-item"><a class="page-link"
							href="QLHomThuGopY?pages=<%=i%>"><%=i%></a></li>

						<%
						}
						}
						%>
						<%
						//Button Next
						int next = 0;
						//Nếu total lẻ
						if (total % 20 != 0) {
							if (pages == (total / 20) + 1) {
								next = pages;//Khong next
							} else {
								next = pages + 1;//Co next
							}
						} else {
							//Nếu total chẵn nhỏ hơn fullpage
							//Và không fullPage thì thêm 1
							if (total < (num * 20) + 20 && total != num * 20) {
								if (pages == (total / 20) + 1) {
							next = pages;//Khong next
								} else {
							next = pages + 1;//Co next
								}
							} else {
								//Nếu fullPage đến trang cuối dừng
								//Chưa tới trang cuối thì được next
								if (pages == (total / 20)) {
							next = pages;//Khong next
								} else {
							next = pages + 1;//Co next
								}
							}
						}
						%>
						<li class="page-item"><a class="page-link"
							href="QLHomThuGopY?pages=<%=next%>"><i class="next">Next</i></a></li>
					</ul>
				</form>
				<br>
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