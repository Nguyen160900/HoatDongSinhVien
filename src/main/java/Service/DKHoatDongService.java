package Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionMySQL;
import Model.DangKyHoatDong;
import Model.HoatDong;

public class DKHoatDongService {
	public List<DangKyHoatDong> getAllDKHoatDong_Lop(int maHoatDong_ChiTiet, String maLop) {
		List<DangKyHoatDong> list = new ArrayList<>();
		Connection connection = ConnectionMySQL.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM dangkyhoatdong "
																+ "INNER JOIN hoatdong ON hoatdong.MaHoatDong = dangkyhoatdong.MaHoatDong "
																+ "INNER JOIN sinhvien ON sinhvien.MaSinhVien = dangkyhoatdong.MaSinhVien "
																+ "INNER JOIN lop ON lop.MaLop = sinhvien.MaLop "
																+ "WHERE dangkyhoatdong.MaHoatDong = ? AND sinhvien.MaLop = ?");
			ps.setInt(1, maHoatDong_ChiTiet);
			ps.setString(2, maLop);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				String tenHoatDong = rs.getString("TenHoatDong");
				String maSinhVienString = rs.getString("MaSinhVien");
				String hoTenString = rs.getString("HoTen");
				Date ngaySinh = rs.getDate("NgaySinh");
				String tenLop = rs.getString("TenLop");
				String tinhTrang = rs.getString("ThamGia");
				
				DangKyHoatDong dangKyHoatDong = new DangKyHoatDong(tenHoatDong, maSinhVienString, hoTenString, ngaySinh, tenLop, tinhTrang);
				list.add(dangKyHoatDong);
				
			}
		} catch (SQLException e) { }
		
		return list;
	}
}
