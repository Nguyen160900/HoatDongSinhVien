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

public class QuanLySinhVienDKHDLopService {
	
	public List<DangKyHoatDong> getAll_HoatDong_SV(int maHoatDong, String maLop) throws SQLException{
		List<DangKyHoatDong> list = new ArrayList<DangKyHoatDong>();
		
		Connection connection = ConnectionMySQL.getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM dangkyhoatdong INNER JOIN hoatdong ON dangkyhoatdong.MaHoatDong = hoatdong.MaHoatDong "
																			+ "INNER JOIN sinhvien ON dangkyhoatdong.MaSinhVien = sinhvien.MaSinhVien "
																			+ "INNER JOIN lop ON sinhvien.MaLop = lop.MaLop "
																			+ "WHERE hoatdong.MaHoatDong = ? AND lop.MaLop = ?");
		preparedStatement.setInt(1, maHoatDong);
		preparedStatement.setString(2, maLop);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			String maSinhVien = resultSet.getString("MaSinhVien");
			String hoTenString = resultSet.getString("HoTen");
			Date ngaySinh = resultSet.getDate("NgaySinh");
			String tenLop = resultSet.getString("TenLop");
			String tinhTrang = resultSet.getString("ThamGia");
			
			DangKyHoatDong dangKyHoatDong = new DangKyHoatDong(maSinhVien, hoTenString, ngaySinh, tenLop, tinhTrang);
			
			list.add(dangKyHoatDong);
		}
		
		connection.close();
		
		return list;
	}
	
	public boolean KtraSV_ThamGia(String maSinhVien, int maHD) throws SQLException {
		boolean ktra = false;
		
		Connection connection = ConnectionMySQL.getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement("select * from dangkyhoatdong "
																		+ "INNER JOIN sinhvien ON sinhvien.MaSinhVien = dangkyhoatdong.MaSinhVien "
																		+ "INNER JOIN hoatdong ON hoatdong.MaHoatDong = dangkyhoatdong.MaHoatDong "
																		+ "where sinhvien.MaSinhVien = ? and dangkyhoatdong.MaHoatDong = ?");
		preparedStatement.setString(1, maSinhVien);
		preparedStatement.setInt(2, maHD);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()) {
			ktra = true;
		}
		else {
			ktra = false;
		}
		
		connection.close();
		
		return ktra;	
	}

	public boolean KtraSV_ThamGia_HoatDong(String maSinhVien, int maHD, String tinhTrang) throws SQLException {
		boolean ktra = false;
		Connection connection = ConnectionMySQL.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from dangkyhoatdong "
				+ "INNER JOIN sinhvien ON sinhvien.MaSinhVien = dangkyhoatdong.MaSinhVien "
				+ "INNER JOIN hoatdong ON hoatdong.MaHoatDong = dangkyhoatdong.MaHoatDong "
				+ "where sinhvien.MaSinhVien = ? and dangkyhoatdong.MaHoatDong = ? and dangkyhoatdong.ThamGia = ?");
		preparedStatement.setString(1, maSinhVien);
		preparedStatement.setInt(2, maHD);
		preparedStatement.setString(3, tinhTrang);
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {
			ktra = true;
		}
		else {
			ktra = false;
		}
		
		connection.close();
		
		return ktra;	
	}
	
	public int Them_SV_HoatDong(int mahd, String maSV, String tinhTrang) throws SQLException {
		int status = 0;
		
		Connection connection = ConnectionMySQL.getConnection();
		
		boolean kTraThem = KtraSV_ThamGia(maSV, mahd);
		
		if(kTraThem == false) {
		
			PreparedStatement preparedStatement = connection.prepareStatement("insert into dangkyhoatdong (MaHoatDong, MaSinhVien, ThamGia) values (?,?,?) ");
			preparedStatement.setInt(1, mahd);
			preparedStatement.setString(2, maSV);
			preparedStatement.setString(3, tinhTrang);
			
			status = preparedStatement.executeUpdate();
			
			connection.close();
		
		}
		
		return status;
	}
	
	public int Sua_SV_HoatDong(int mahd, String maSV, String tinhTrang) throws SQLException {
		int status = 0;
		
		Connection connection = ConnectionMySQL.getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement("update dangkyhoatdong set ThamGia = ? where MaHoatDong = ? AND MaSinhVien = ?");

		preparedStatement.setString(1, tinhTrang);
		preparedStatement.setInt(2, mahd);
		preparedStatement.setString(3, maSV);
		
		status = preparedStatement.executeUpdate();
		
		connection.close();
		
		return status;
	}
	
	public int Xoa_SV_HoatDong(int mahd, String maSV) throws SQLException {
		int status = 0;
		
		Connection connection = ConnectionMySQL.getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement("delete from dangkyhoatdong where MaHoatDong = ? AND MaSinhVien = ?");
		preparedStatement.setInt(1, mahd);
		preparedStatement.setString(2, maSV);
		
		status = preparedStatement.executeUpdate();
		
		connection.close();
		
		return status;
	}
	
	
}
