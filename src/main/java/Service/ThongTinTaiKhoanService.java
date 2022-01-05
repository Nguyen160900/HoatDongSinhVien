package Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import Connection.ConnectionMySQL;
import Model.GiangVien;
import Model.SinhVien;

public class ThongTinTaiKhoanService {
	
	SinhVien sinhVien;
	GiangVien giangVien;
	
	public SinhVien taiKhoan_user(String maSinhVien) throws SQLException {
		Connection connection = ConnectionMySQL.getConnection();
		
		PreparedStatement pStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM sinhvien INNER JOIN lop ON sinhvien.MaLop = lop.MaLop "
																	+ "INNER JOIN khoahoc ON khoahoc.MaKhoaHoc = lop.MaKhoaHoc "
																	+ "WHERE sinhvien.MaSinhVien = ?");
		pStatement.setString(1, maSinhVien);
		ResultSet resultSet = pStatement.executeQuery();
		if(resultSet.next()) {
			String maSinhVienString = resultSet.getString("MaSinhVien");
			String hoTen = resultSet.getString("HoTen");
			String gioiTinhString = resultSet.getString("GioiTinh");
	;		String sdtString = resultSet.getString("SDT");
			String emailString = resultSet.getString("Email");
			String chucvuString = resultSet.getString("ChucVu");
			String tinhtranghocString = resultSet.getString("TinhTrangHoc");
			String matkhauString = resultSet.getString("MatKhau");
			int quyen = resultSet.getInt("Quyen");
			Date ngaysinhDate = resultSet.getDate("NgaySinh");
			String malopString = resultSet.getString("MaLop");
			String tenlopString = resultSet.getString("TenLop");
			String makhoahocString = resultSet.getString("MaKhoaHoc");
			
			sinhVien = new SinhVien(maSinhVienString, hoTen, gioiTinhString, sdtString, emailString, chucvuString, tinhtranghocString, matkhauString, quyen, ngaysinhDate, malopString, tenlopString, makhoahocString);
		}
		
		return sinhVien;
				
	}
	
	public GiangVien taiKhoan_admin(String maGiangVien) throws SQLException {
		Connection connection = ConnectionMySQL.getConnection();
		
		PreparedStatement pStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM giangvien WHERE MaGiangVien = ?");
		pStatement.setString(1, maGiangVien);
		ResultSet rs = pStatement.executeQuery();
		if(rs.next()) {
			String MaGiangVien= rs.getString("MaGiangVien");
			String HoTen = rs.getString("HoTen");
			String GioiTinh = rs.getString("GioiTinh"); 
			String SDT = rs.getString("SDT"); 
			String Email = rs. getString("Email"); 
			String MatKhau = rs.getString("MatKhau");
			Date NgaySinh = rs.getDate("NgaySinh");
			
			giangVien = new GiangVien(MaGiangVien, HoTen, GioiTinh, SDT, Email, MatKhau, NgaySinh);
		}
		
		return giangVien;
				
	}
	
//	public static void main(String[] args) throws SQLException {
//		ThongTinTaiKhoanService thongTinTaiKhoanService = new ThongTinTaiKhoanService();
//		String maString = "admin_kcntt";
//		GiangVien gVien = thongTinTaiKhoanService.taiKhoan_admin(maString);
//		System.out.println(gVien.getHoTen());
//	}
}
