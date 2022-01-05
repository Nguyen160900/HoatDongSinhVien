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

public class DSDangKyHDService {
	public boolean KiemTra_SV_ThamGiaHD(String maSinhVien, int maHoatDong) throws SQLException {
		boolean kTra = false;
		
		Connection connection = ConnectionMySQL.getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement("select * from dangkyhoatdong"
																			+ "INNER JOIN sinhvien ON sinhvien.MaSinhVien = dangkyhoatdong.MaSinhVien"
																			+ "INNER JOIN hoatdong ON hoatdong.MaHoatDong = dangkyhoatdong.MaHoatDong"
																			+ "where sinhvien.MaSinhVien = ? and dangkyhoatdong.MaHoatDong = ?");
		preparedStatement.setString(1, maSinhVien);
		preparedStatement.setInt(2, maHoatDong);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()) {
			kTra = true;
		}else {
			kTra = false;
		}
		
		return kTra;
	}
	
	public List<DangKyHoatDong> getAll_HoatDong_SV(int maHoatDong) throws SQLException{
		List<DangKyHoatDong> list = new ArrayList<DangKyHoatDong>();
		
		Connection connection = ConnectionMySQL.getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM dangkyhoatdong INNER JOIN hoatdong ON dangkyhoatdong.MaHoatDong = hoatdong.MaHoatDong "
																			+ "INNER JOIN sinhvien ON dangkyhoatdong.MaSinhVien = sinhvien.MaSinhVien "
																			+ "INNER JOIN lop ON sinhvien.MaLop = lop.MaLop "
																			+ "WHERE hoatdong.MaHoatDong = ?");
		preparedStatement.setInt(1, maHoatDong);
		
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
		return list;
	}
	
	public int getAll_SoLuong(int maHoatDong) throws SQLException{
		int SoLuong = 0;
		
		Connection connection = ConnectionMySQL.getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(sinhvien.MaSinhVien) as dem FROM dangkyhoatdong INNER JOIN hoatdong ON dangkyhoatdong.MaHoatDong = hoatdong.MaHoatDong "
																			+ "INNER JOIN sinhvien ON dangkyhoatdong.MaSinhVien = sinhvien.MaSinhVien "
																			+ "INNER JOIN lop ON sinhvien.MaLop = lop.MaLop "
																			+ "WHERE hoatdong.MaHoatDong = ?");
		preparedStatement.setInt(1, maHoatDong);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()) {
			SoLuong = resultSet.getInt("dem");
		}
		return SoLuong;
	}
	
	public int LoadSoLuong(String maLop, String maKhoaHoc, String tinhTrang, int maHoatDong) throws SQLException {
		Connection connection = ConnectionMySQL.getConnection();
		
		int soluong = 0;
		String sql = "";
		
		if(maKhoaHoc == "" && tinhTrang == "") {
			sql = "select COUNT(sinhvien.MaSinhVien) as dem from sinhvien inner join dangkyhoatdong on dangkyhoatdong.MaSinhVien = sinhvien.MaSinhVien inner join lop on lop.MaLop = sinhvien.MaLop where lop.MaLop = '"+maLop+"' and dangkyhoatdong.MaHoatDong = '"+maHoatDong+"'";
		}else if (maLop == "" && tinhTrang == "") {
			sql = "select COUNT(sinhvien.MaSinhVien) as dem from sinhvien inner join dangkyhoatdong on dangkyhoatdong.MaSinhVien = sinhvien.MaSinhVien inner join lop on lop.MaLop = sinhvien.MaLop inner join khoahoc on lop.MaKhoaHoc = khoahoc.MaKhoaHoc where khoahoc.MaKhoaHoc = '"+maKhoaHoc+"' and dangkyhoatdong.MaHoatDong = '"+maHoatDong+"'";
		}else if (maKhoaHoc == "" && maLop == "") {
			sql = "select COUNT(sinhvien.MaSinhVien) as dem from sinhvien inner join dangkyhoatdong on dangkyhoatdong.MaSinhVien = sinhvien.MaSinhVien inner join lop on lop.MaLop = sinhvien.MaLop where dangkyhoatdong.ThamGia = '"+tinhTrang+"' and dangkyhoatdong.MaHoatDong = '"+maHoatDong+"'";
		}else if (tinhTrang == "") {
			sql = "select COUNT(sinhvien.MaSinhVien) as dem from sinhvien inner join dangkyhoatdong on dangkyhoatdong.MaSinhVien = sinhvien.MaSinhVien inner join lop on lop.MaLop = sinhvien.MaLop inner join khoahoc on lop.MaKhoaHoc = khoahoc.MaKhoaHoc where khoahoc.MaKhoaHoc = '"+maKhoaHoc+"' and lop.MaLop = '"+maLop+"' and dangkyhoatdong.MaHoatDong = '"+maHoatDong+"'";
		}else if (maKhoaHoc == "") {
			sql = "select COUNT(sinhvien.MaSinhVien) as dem from sinhvien inner join dangkyhoatdong on dangkyhoatdong.MaSinhVien = sinhvien.MaSinhVien inner join lop on lop.MaLop = sinhvien.MaLop where lop.MaLop = '"+maLop+"' and dangkyhoatdong.ThamGia = '"+tinhTrang+"' and dangkyhoatdong.MaHoatDong = '"+maHoatDong+"'";
		}else if (maLop == "") {
			sql = "select COUNT(sinhvien.MaSinhVien) as dem from sinhvien inner join dangkyhoatdong on dangkyhoatdong.MaSinhVien = sinhvien.MaSinhVien inner join lop on lop.MaLop = sinhvien.MaLop inner join khoahoc on lop.MaKhoaHoc = khoahoc.MaKhoaHoc where khoahoc.MaKhoaHoc = '"+maKhoaHoc+"' and dangkyhoatdong.ThamGia = '"+tinhTrang+"' and dangkyhoatdong.MaHoatDong = '"+maHoatDong+"'";
		}else {
			sql = "select COUNT(sinhvien.MaSinhVien) as dem from sinhvien inner join dangkyhoatdong on dangkyhoatdong.MaSinhVien = sinhvien.MaSinhVien inner join lop on lop.MaLop = sinhvien.MaLop inner join khoahoc on lop.MaKhoaHoc = khoahoc.MaKhoaHoc where khoahoc.MaKhoaHoc = '"+maKhoaHoc+"' and dangkyhoatdong.ThamGia = '"+tinhTrang+"' and lop.MaLop = '"+maLop+"' and dangkyhoatdong.MaHoatDong = '"+maHoatDong+"'";
		}
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()) {
			soluong = resultSet.getInt("dem");
		}
		return soluong;
	}
	
	public List<DangKyHoatDong> LocDuLieu(String maLop, String maKhoaHoc, String tinhTrang, int maHoatDong) throws SQLException {
		Connection connection = ConnectionMySQL.getConnection();
		
		List<DangKyHoatDong> list = new ArrayList<DangKyHoatDong>();
		String sql = "";
		
		if(maKhoaHoc == "" && tinhTrang == "") {
			sql = "select * from sinhvien inner join dangkyhoatdong on dangkyhoatdong.MaSinhVien = sinhvien.MaSinhVien inner join lop on lop.MaLop = sinhvien.MaLop where lop.MaLop = '"+maLop+"' and dangkyhoatdong.MaHoatDong = '"+maHoatDong+"'";
		}else if (maLop == "" && tinhTrang == "") {
			sql = "select * from sinhvien inner join dangkyhoatdong on dangkyhoatdong.MaSinhVien = sinhvien.MaSinhVien inner join lop on lop.MaLop = sinhvien.MaLop inner join khoahoc on lop.MaKhoaHoc = khoahoc.MaKhoaHoc where khoahoc.MaKhoaHoc = '"+maKhoaHoc+"' and dangkyhoatdong.MaHoatDong = '"+maHoatDong+"'";
		}else if (maKhoaHoc == "" && maLop == "") {
			sql = "select * from sinhvien inner join dangkyhoatdong on dangkyhoatdong.MaSinhVien = sinhvien.MaSinhVien inner join lop on lop.MaLop = sinhvien.MaLop where dangkyhoatdong.ThamGia = '"+tinhTrang+"' and dangkyhoatdong.MaHoatDong = '"+maHoatDong+"'";
		}else if (tinhTrang == "") {
			sql = "select * from sinhvien inner join dangkyhoatdong on dangkyhoatdong.MaSinhVien = sinhvien.MaSinhVien inner join lop on lop.MaLop = sinhvien.MaLop inner join khoahoc on lop.MaKhoaHoc = khoahoc.MaKhoaHoc where khoahoc.MaKhoaHoc = '"+maKhoaHoc+"' and lop.MaLop = '"+maLop+"' and dangkyhoatdong.MaHoatDong = '"+maHoatDong+"'";
		}else if (maKhoaHoc == "") {
			sql = "select * from sinhvien inner join dangkyhoatdong on dangkyhoatdong.MaSinhVien = sinhvien.MaSinhVien inner join lop on lop.MaLop = sinhvien.MaLop where lop.MaLop = '"+maLop+"' and dangkyhoatdong.ThamGia = '"+tinhTrang+"' and dangkyhoatdong.MaHoatDong = '"+maHoatDong+"'";
		}else if (maLop == "") {
			sql = "select * from sinhvien inner join dangkyhoatdong on dangkyhoatdong.MaSinhVien = sinhvien.MaSinhVien inner join lop on lop.MaLop = sinhvien.MaLop inner join khoahoc on lop.MaKhoaHoc = khoahoc.MaKhoaHoc where khoahoc.MaKhoaHoc = '"+maKhoaHoc+"' and dangkyhoatdong.ThamGia = '"+tinhTrang+"' and dangkyhoatdong.MaHoatDong = '"+maHoatDong+"'";
		}else {
			sql = "select * from sinhvien inner join dangkyhoatdong on dangkyhoatdong.MaSinhVien = sinhvien.MaSinhVien inner join lop on lop.MaLop = sinhvien.MaLop inner join khoahoc on lop.MaKhoaHoc = khoahoc.MaKhoaHoc where khoahoc.MaKhoaHoc = '"+maKhoaHoc+"' and dangkyhoatdong.ThamGia = '"+tinhTrang+"' and lop.MaLop = '"+maLop+"' and dangkyhoatdong.MaHoatDong = '"+maHoatDong+"'";
		}
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			String maSinhVien = resultSet.getString("MaSinhVien");
			String hoTenString = resultSet.getString("HoTen");
			Date ngaySinh = resultSet.getDate("NgaySinh");
			String tenLop = resultSet.getString("TenLop");
			String tinhtrang = resultSet.getString("ThamGia");
			
			DangKyHoatDong dangKyHoatDong = new DangKyHoatDong(maSinhVien, hoTenString, ngaySinh, tenLop, tinhtrang);
			
			list.add(dangKyHoatDong);
		}
		return list;
	}
	
	
	public int delete(int maHoatDong, String maSinhVien) throws SQLException {
		int status = 0;
		
		Connection connection = ConnectionMySQL.getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement("delete from dangkyhoatdong where MaHoatDong = ? and MaSinhVien = ?");
		preparedStatement.setInt(1, maHoatDong);
		preparedStatement.setString(2, maSinhVien);
		
		status = preparedStatement.executeUpdate();
		
		connection.close();		
		
		return status;
	}
	
	public int update(int maHoatDong, String maSinhVien, String tinhTrang) throws SQLException {
		int status = 0;
		
		Connection connection = ConnectionMySQL.getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement("update dangkyhoatdong set ThamGia = ? where MaHoatDong = ? and MaSinhVien = ?");
		preparedStatement.setString(1, tinhTrang);
		preparedStatement.setInt(2, maHoatDong);
		preparedStatement.setString(3, maSinhVien);
		
		status = preparedStatement.executeUpdate();
		
		connection.close();
		
		return status;
	}
	
	public int update_thamgia(int maHoatDong, String maSinhVien) throws SQLException {
		int status = 0;
		
		Connection connection = ConnectionMySQL.getConnection();
		String tinhtrang = "Tham gia";
		
		PreparedStatement preparedStatement = connection.prepareStatement("update dangkyhoatdong set ThamGia = ? where MaHoatDong = ? and MaSinhVien = ?");
		preparedStatement.setString(1, tinhtrang);
		preparedStatement.setInt(2, maHoatDong);
		preparedStatement.setString(3, maSinhVien);
		
		status = preparedStatement.executeUpdate();
		
		connection.close();
		
		return status;
	}
	
	public int update_khongthamgia( int maHoatDong, String maSinhVien) throws SQLException {
		int status = 0;
		
		Connection connection = ConnectionMySQL.getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement("update dangkyhoatdong set ThamGia = ? where MaHoatDong = ? and MaSinhVien = ?");
		preparedStatement.setString(1, "Không tham gia");
		preparedStatement.setInt(2, maHoatDong);
		preparedStatement.setString(3, maSinhVien);
		
		status = preparedStatement.executeUpdate();
		
		connection.close();
		
		return status;
	}
}
