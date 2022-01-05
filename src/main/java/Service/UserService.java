package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Connection.ConnectionMySQL;
import Model.SinhVien;

public class UserService {
	public boolean checkLogin(String username, String password) {
		boolean ktra = false;
		try {
			Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from sinhvien where MaSinhVien=? and MatKhau=?");  
	        ps.setString(1, username);
	        ps.setString(2, password);
	        
	        ResultSet rs=ps.executeQuery();  
			
			if (rs.next()) {
				ktra = true;
			} else {
				ktra = false;
			}
		} catch (Exception e) {}
		
		return ktra;
	}
	
	public int QuyenDangNhap(String maSinhVien)
	{
		int quyen = 0;
		Connection connection = ConnectionMySQL.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select Quyen from sinhvien where MaSinhVien = ?");
			ps.setString(1, maSinhVien);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				quyen = rs.getInt("Quyen");
			}
		} catch (SQLException e) { }
		return quyen;
	}
	
	SinhVien sinhVien;
	
	public SinhVien ThongTinDangNhap(String maSinhVien) {
		Connection connection = ConnectionMySQL.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select sinhvien.HoTen, sinhvien.MaSinhVien, khoahoc.MaKhoaHoc, lop.TenLop, lop.MaLop from sinhvien "
																+ "inner join lop on lop.MaLop = sinhvien.MaLop "
																+ "inner join khoahoc on lop.MaKhoaHoc = khoahoc.MaKhoaHoc "
																+ "where MaSinhVien = ?");
			ps.setString(1, maSinhVien);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String hoTen = rs.getString("HoTen");
				String maLop = rs.getString("MaLop");
				String tenLop = rs.getString("TenLop");
				String maKhoaHoc = rs.getString("MaKhoaHoc");
				
				sinhVien = new SinhVien(maSinhVien, hoTen, maKhoaHoc, maLop, tenLop);
			}
		} catch (Exception e) { }
		
		return sinhVien;
	}
	
	public List<SinhVien> getAll_SVLop(String maLop) throws SQLException{
		List<SinhVien> list = new ArrayList<SinhVien>();
		
		Connection connection = ConnectionMySQL.getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement("select * from sinhvien INNER JOIN lop ON sinhvien.MaLop = lop.MaLop " 
																			+ "INNER JOIN khoahoc ON lop.MaKhoaHoc = khoahoc.MaKhoaHoc "
																			+ "where lop.MaLop = ?");
		preparedStatement.setString(1, maLop);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			String maSinhVienString = resultSet.getString("MaSinhVien");
			String hoTenString = resultSet.getString("hoTen");
			String maKhoaHocString = resultSet.getString("MaKhoaHoc");
			String maLopString = resultSet.getString("MaLop");
			String tenLopString = resultSet.getString("TenLop");
			
			SinhVien sinhVien = new SinhVien(maSinhVienString, hoTenString, maLopString, tenLopString);
			
			list.add(sinhVien);
		}
		return list;
	}
	
//	public static void main(String[] args) {
//		SinhVien sVien = new SinhVien();
//		UserService userService = new UserService();
//		String maSinhVien = "4151050048";
//		sVien = userService.ThongTinDangNhap(maSinhVien);
//		
//		System.out.println(sVien.getMaKhoaHoc());
//	}
	
}
