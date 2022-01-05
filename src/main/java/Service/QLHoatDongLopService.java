package Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import Connection.ConnectionMySQL;
import Model.SinhVien;
import Model.HoatDong;

public class QLHoatDongLopService {
	
	SinhVien sinhVien;
	
	public SinhVien get_CboNamHoc(String maSinhVien) throws SQLException {
		Connection connection = ConnectionMySQL.getConnection();
		
		PreparedStatement pStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM lop INNER JOIN khoahoc ON khoahoc.MaKhoaHoc = lop.MaKhoaHoc "
																						+ "INNER JOIN sinhvien ON sinhvien.MaLop = lop.MaLop WHERE sinhvien.MaSinhVien= ?");
		pStatement.setString(1, maSinhVien);
		ResultSet resultSet = pStatement.executeQuery();
		if(resultSet.next()) {
			String maKhoaHoc = resultSet.getString("MaKhoaHoc");
			String maLop = resultSet.getString("MaLop");
			String tenLop = resultSet.getString("TenLop");
			int namBatDau = resultSet.getInt("NamBatDau");
			int namKetThuc = resultSet.getInt("NamKetThuc");
			
			sinhVien = new SinhVien(maKhoaHoc, maLop, tenLop, namBatDau, namKetThuc);
		}
		return sinhVien;
	}
	
	public List<HoatDong> getAllHoatDong() throws SQLException{
		List<HoatDong> list = new ArrayList<HoatDong>();
		
		Connection connection = ConnectionMySQL.getConnection();
		PreparedStatement pStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM hoatdong");
		
		ResultSet resultSet = pStatement.executeQuery();
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
			list.add(hoatdong);
		}
		
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
	
}
