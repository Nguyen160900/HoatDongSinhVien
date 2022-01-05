<%@page import="java.util.List"%>
<%@page import="Service.HoatDongService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.HoatDong"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Đăng ký hoạt động</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/style/style-footer.css">
    <style>
    	.jumbotron{
		    background-image: linear-gradient(to bottom right, #1E90FF, #FFFFFF); 
		    display: flex;
		}
    </style>
</head>
<body>
	
	<!-- Đầu trang -->
    <div class="jumbotron text-center" style="margin-bottom:0; padding: 20px">

        <%@ include file="../BoCuc/Top.jsp"%> 

    </div>

    <!-- /* menu của trang */ -->
	<%@ include file="../BoCuc/Menu.jsp"%> 

    <!--  /* thân của trang */ -->
    <div class="container-fluid" style="margin-top:30px">
        <div class="row">

            <div class="col-sm-12">
                <form action="<%=request.getContextPath()%>/DKHoatDong" method="POST">
                	<%
                		if(username != null)
                		{
                	%>
                    <h2 style="text-align: center">ĐĂNG KÝ HOẠT ĐỘNG SINH VIÊN</h2>
                    <hr>

                    <div class="row">
                    
                    	<%
                    		List<HoatDong> list = (List<HoatDong>) request.getAttribute("hoatDongs");
                    		for(HoatDong hoatDongs:list)
                    		{
                    	%>
                    					       
						<div class="col-lg-3 col-sm-6" style="margin-bottom: 15px">
                           <div class="card" style="width:350px">
                               <img class="card-img-top" src="${pageContext.request.contextPath}/resource/image/anh3.jpg" alt="Card image" style="width:100%">
                               <div class="card-body">
                                   <h5 class="card-title" style="text-align: center"><%=hoatDongs.getTenHoatDong()%></h5>
                                   <hr>
                                   <p class="card-text">Ngày diễn ra: <%=hoatDongs.getNgayDienRa() %></p>
                                   <p>Năm học: <%=hoatDongs.getNamHoc()%> <-> Học kỳ: <%=hoatDongs.getHocKy()%></p>
                                   <p>Đối tượng: <%=hoatDongs.getKhoaThamGia()%></p>

                                   <div class="row">
                                   		<% 
                                   			int maHoatDong = hoatDongs.getMaHoatDong();
                                   			boolean kiemTraDKHD = new HoatDongService().DangKyHoatDong(username, maHoatDong);
                                   			
                                   			if(kiemTraDKHD)
                                   			{
                                   		%>
                                   		<div class="col-lg-12">
                                            <div class="alert alert-info" style="margin: 0;">
                                                <h6 style="text-align: center; margin: auto;">Hoạt động đã đăng ký</h6>
                                            </div>
                                        </div>
                                   		<%
                                   			}else{
                                   		%>
										<div class="col-lg-6">
                                            <button type="button" data-toggle="modal" data-target="#mdChitiet<%=hoatDongs.getMaHoatDong() %>" class="btn btn-info btn-block button" style="margin: 5px;">CHI TIẾT</button>
                                        </div>
                                        <div class="col-lg-6">
                                            <a class="btn btn-primary btn-block button" style="margin: 5px;" href="${pageContext.request.contextPath}/DKHoatDong/DangKyHoatDong_1?maHoatDong_1=<%=hoatDongs.getMaHoatDong() %>">ĐĂNG KÝ</a>
                                        </div>
                                        <br>
                                        <% } %>
                                        
                                   </div> 
                                   
                               </div>
                               
                               <!-- MODAL CHI TIẾT, CHỈNH SỬA -->
                               <div class="modal fade" id="mdChitiet<%=hoatDongs.getMaHoatDong() %>" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="mdChitietLabel" aria-hidden="true">
                                   <div class="modal-dialog modal-dialog-scrollable modal-lg">
                                       <div class="modal-content">
                                           <div class="modal-header">
                                               <h5 class="modal-title" id="mdChitietLabel">Chi tiết hoạt động</h5>
                                               <button type="button" class="close" data-dismiss="modal">&times;</button>
                                           </div>
                                           <div class="modal-body">
                                               <div class="row">
                                                   <div class="col-sm-12" style="justify-content: center;display: block;">
                                                       <p style=" font-size: 18px;">
                                                           Tên hoạt động:
                                                           <input type="text" class="form-control" id="tenhoatdong" name="txtTenHD" style="font-size: 18px; pointer-events: none;" value="<%=hoatDongs.getTenHoatDong() %>">
                                                       </p>
                                                   </div>
                                               </div>
                                               <div class="row">
                                                   <div class="col-sm-6" style="justify-content: center;display: block;">
                                                       <p style=" font-size: 18px;">
                                                           Ngày diễn ra:
                                                           <input type="text" class="form-control" id="Ngaydienra" name="txtNgay" style="font-size: 18px; pointer-events: none;" value="<%=hoatDongs.getNgayDienRa() %>">
                                                       </p>
                                                   </div>
                                                   <div class="col-sm-6" style="justify-content: center;display: block;">
                                                       <p style=" font-size: 18px;">
                                                           Thời gian diễn ra:
                                                           <input type="text" class="form-control" id="TGdienra" name="txtTG" style="font-size: 18px; pointer-events: none;" value="<%=hoatDongs.getThoiGianDienRa() %>">
                                                       </p>
                                                   </div>
                                               </div>
                                               <div class="row">
                                                   <div class="col-sm-6" style="justify-content: center;display: block;">
                                                       <p style=" font-size: 18px;">
                                                           Năm học:
                                                           <input type="text" class="form-control" id="namhoc" name="txtNamhoc" style="font-size: 18px; pointer-events: none;" value="<%=hoatDongs.getNamHoc() %>">
                                                       </p>
                                                   </div>
                                                   <div class="col-sm-6" style="justify-content: center;display: block;">
                                                       <p style=" font-size: 18px;">
                                                           Học kì:
                                                           <input type="text" class="form-control" id="hocki" name="txthocki" style="font-size: 18px; pointer-events: none;" value="<%=hoatDongs.getHocKy() %>">
                                                       </p>
                                                   </div>
                                               </div>
                                               <div class="row">
                                                   <div class="col-sm-6" style="justify-content: center;display: block;">
                                                       <p style=" font-size: 18px;">
                                                           Khóa tham gia:
                                                           <input type="text" class="form-control" id="khoathamgia" name="txtkhoa" style="font-size: 18px; pointer-events: none;" value="<%=hoatDongs.getKhoaThamGia() %>">
                                                       </p>
                                                   </div>
                                                   <div class="col-sm-6" style="justify-content: center;display: block;">
                                                       <p style=" font-size: 18px;">
                                                           Số lượng:
                                                           <input type="text" class="form-control" id="soluong" name="txtsoluong" style="font-size: 18px; pointer-events: none;" value="<%=hoatDongs.getSoLuong() %>">
                                                       </p>
                                                   </div>
                                               </div>
                                               <div class="row">
                                                   <div class="col-sm-12" style="justify-content: center;display: block;">
                                                       <p style=" font-size: 18px;">
                                                           Nội dung:
                                                           <textarea class="form-control" id="noidung" name="txtNoiDung" style=" font-size: 18px; overflow-y: scroll; resize: none; height: 500px;" rows="3"><%=hoatDongs.getNoiDung() %></textarea>
                                                       </p>
                                                   </div>
                                               </div>
                                           </div>
                                           <div class="modal-footer">
                                               <a class="btn btn-primary" href="${pageContext.request.contextPath}/DKHoatDong/DangKyHoatDong_2?maHoatDong_2=<%=hoatDongs.getMaHoatDong() %>">Đăng ký</a>
                                               <button type="button" class="btn btn-danger" data-dismiss="modal">Thoát</button>
                                           </div>
                                       </div>
                                   </div>
                               </div>
                               <!-- END MODAL -->
                               
                           </div>
                           
                       </div>
                       
                       <% } %>
                       
                    </div>
                    
					<% } %>
					
                </form>
                <br>
            </div>

        </div>
    </div>

    <!-- /* chân trang */ -->
    <div class="jumbotron text-center" style="margin-bottom:0">

        <%@ include file="../BoCuc/Footer.jsp"%> 

    </div>
	
</body>
</html>