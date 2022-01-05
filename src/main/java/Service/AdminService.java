package Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionMySQL;
import Model.GiangVien;

public class AdminService {
	public boolean checkLogin(String username, String password) {
		boolean ktra = false;
		try {
			Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement ps = connection
					.prepareStatement("select * from giangvien where MaGiangVien=? and MatKhau=?");
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				ktra = true;
			} else {
				ktra = false;
			}
		} catch (Exception e) {
		}

		return ktra;
	}

	public int QuyenDangNhap(String maGiangVien) {
		int quyen = 0;
		Connection connection = ConnectionMySQL.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select Quyen from giangvien where MaGiangVien = ?");
			ps.setString(1, maGiangVien);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				quyen = rs.getInt("Quyen");
			}
		} catch (SQLException e) {
		}
		return quyen;
	}

	GiangVien giangVien;

	public GiangVien ThongTinDangNhap(String maGiangVien) {
		Connection connection = ConnectionMySQL.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from giangvien where MaGiangVien = ?");
			ps.setString(1, maGiangVien);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String MaGiangVien= rs.getString("MaGiangVien");
				String HoTen = rs.getString("HoTen");
				String GioiTinh = rs.getString("GioiTinh"); 
				String SDT = rs.getString("SDT"); 
				String Email = rs. getString("Email"); 
				String MatKhau = rs.getString("MatKhau");
				Date NgaySinh = rs.getDate("NgaySinh");
				
				giangVien = new GiangVien(MaGiangVien, HoTen, GioiTinh, SDT, Email, MatKhau, NgaySinh);
			}
		} catch (Exception e) {
		}

		return giangVien;
	}
	
//	public static void main(String[] args) {
//		AdminService adminService = new AdminService();
//		String maString = "4151050048";
//		GiangVien gVien = adminService.ThongTinDangNhap(maString);
//		System.out.println(gVien.getHoTen());
//	}
}
