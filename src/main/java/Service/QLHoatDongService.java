package Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionMySQL;
import Model.HoatDong;
import Model.SinhVien;

public class QLHoatDongService {
	
	public List<HoatDong> getAllHoatDong() {
		List<HoatDong> list = new ArrayList<HoatDong>();
		
		Connection connection = ConnectionMySQL.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from hoatdong");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				int maHoatDong = rs.getInt("MaHoatDong");
				String tenHoatDong = rs.getString("TenHoatDong");
				Date ngayDienRa = rs.getDate("NgayDienRa");
				String thoiGianDienRa = rs.getString("ThoiGianDienRa");
				String noiDung = rs.getString("NoiDung");
				String namHoc = rs.getString("NamHoc");
				int hocKy = rs.getInt("HocKy");
				String capDienRa = rs.getString("CapDienRa");
				String khoaThamGia = rs.getString("KhoaThamGia");
				int soLuong = rs.getInt("SoLuong");
				String file = rs.getString("File");
				
				HoatDong hoatDong = new HoatDong(maHoatDong, tenHoatDong, ngayDienRa, thoiGianDienRa, noiDung, namHoc, hocKy, capDienRa, khoaThamGia, soLuong, file);
				list.add(hoatDong);
				
			}
		} catch (SQLException e) { }
		
		return list;
	}
	
	public List<HoatDong> TimKiem(String NamHoc, int HocKy) throws SQLException{
		List<HoatDong> hoatDongs = new ArrayList<HoatDong>();
		
		Connection connection = ConnectionMySQL.getConnection();
		PreparedStatement  ps; 
		
		if(HocKy == 0) {
			ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM hoatdong WHERE hoatdong.NamHoc= ?");
			ps.setString(1, NamHoc);
		}else if(NamHoc == "") {
			ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM hoatdong WHERE hoatdong.HocKy= ?");
			ps.setInt(1, HocKy);
		}else {
			ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM hoatdong WHERE hoatdong.NamHoc = ? AND hoatdong.HocKy = ?");
			ps.setString(1, NamHoc);
			ps.setInt(2, HocKy);
		}
		
		ResultSet resultSet = ps.executeQuery();
		
		while(resultSet.next()) {
			int maHoatDong = resultSet.getInt("MaHoatDong");
			String tenHoatDong = resultSet.getString("TenHoatDong");
			Date ngayDienRa = resultSet.getDate("NgayDienRa");
			String thoiGianDienRa = resultSet.getString("ThoiGianDienRa");
			String noiDung = resultSet.getString("NoiDung");
			String namHoc = resultSet.getString("NamHoc");
			int hocKy = resultSet.getInt("HocKy");
			String capDienRa = resultSet.getString("CapDienRa");
			String khoaThamGia = resultSet.getString("KhoaThamGia");
			int soLuong = resultSet.getInt("SoLuong");
			String file = resultSet.getString("File");
			
			HoatDong hoatdong = new HoatDong(maHoatDong, tenHoatDong, ngayDienRa, thoiGianDienRa, noiDung, namHoc, hocKy, capDienRa, khoaThamGia, soLuong, file);
			hoatDongs.add(hoatdong);
			
		}
		return hoatDongs;
		
	}
	
	public int insert(String tenHoatDong, Date ngayDienRa, String thoiGianDienRa, String noiDung, String namHoc, int hocKy, String capDienRa, String khoaThamGia, int soLuong, String file) throws SQLException {
		int status = 0;
		
		Connection connection = ConnectionMySQL.getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement("insert into hoatdong(TenHoatDong, NgayDienRa, ThoiGianDienRa, NoiDung, NamHoc, HocKy, CapDienRa, KhoaThamGia, SoLuong, File) values(?,?,?,?,?,?,?,?,?,?)");
		preparedStatement.setString(1, tenHoatDong);
		preparedStatement.setDate(2, ngayDienRa);
		preparedStatement.setString(3, thoiGianDienRa);
		preparedStatement.setString(4, noiDung);
		preparedStatement.setString(5, namHoc);
		preparedStatement.setInt(6, hocKy);
		preparedStatement.setString(7, capDienRa);
		preparedStatement.setString(8, khoaThamGia);
		preparedStatement.setInt(9, soLuong);
		preparedStatement.setString(10, file);
		
		status = preparedStatement.executeUpdate();
		
		connection.close();		
		
		return status;
	}
	
	public int update(int maHoatDong, String tenHoatDong, Date ngayDienRa, String thoiGianDienRa, String noiDung, String namHoc, int hocKy, String capDienRa, String khoaThamGia, int soLuong, String file) throws SQLException {
		int status = 0;
		
		Connection connection = ConnectionMySQL.getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement("update hoatdong set TenHoatDong = ?, NgayDienRa = ?, ThoiGianDienRa = ?, NoiDung = ?, NamHoc = ?, HocKy = ?, CapDienRa = ?, KhoaThamGia = ?, SoLuong = ?, File = ? where MaHoatDong = ?");
		preparedStatement.setString(1, tenHoatDong);
		preparedStatement.setDate(2, ngayDienRa);
		preparedStatement.setString(3, thoiGianDienRa);
		preparedStatement.setString(4, noiDung);
		preparedStatement.setString(5, namHoc);
		preparedStatement.setInt(6, hocKy);
		preparedStatement.setString(7, capDienRa);
		preparedStatement.setString(8, khoaThamGia);
		preparedStatement.setInt(9, soLuong);
		preparedStatement.setString(10, file);
		preparedStatement.setInt(11, maHoatDong);
		
		status = preparedStatement.executeUpdate();
		
		connection.close();		
		
		return status;
	}
	
	public int delete(int maHoatDong) throws SQLException {
		int status = 0;
		
		Connection connection = ConnectionMySQL.getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement("delete from hoatdong where MaHoatDong = ?");
		preparedStatement.setInt(1, maHoatDong);
		
		status = preparedStatement.executeUpdate();
		
		connection.close();		
		
		return status;
	}

}
