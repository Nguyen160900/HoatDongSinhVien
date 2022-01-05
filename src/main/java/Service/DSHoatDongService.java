package Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionMySQL;
import Model.DSHoatDong;
import Model.HoatDong;
import Model.SinhVien;

public class DSHoatDongService {
	
	SinhVien sinhVien;

	public List<DSHoatDong> getAllHDDangky(String maSinhVien) throws SQLException {
		List<DSHoatDong> list = new ArrayList<DSHoatDong>();
		
		Connection connection = ConnectionMySQL.getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement("select * from dangkyhoatdong "
				+ "INNER JOIN hoatdong ON dangkyhoatdong.MaHoatDong = hoatdong.MaHoatDong "
				+ "where dangkyhoatdong.MaSinhVien = ?");

		preparedStatement.setString(1, maSinhVien);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			String tinhTrang = resultSet.getString("ThamGia");
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

			DSHoatDong dsHoatDong = new DSHoatDong(maSinhVien, tinhTrang, maHoatDong, tenHoatDong, ngayDienRa, thoiGianDienRa, noiDung, namHoc, hocKy, capDienRa, khoaThamGia, soLuong, khoaThamGia);
			list.add(dsHoatDong);
		}
		return list;
	}
	
	
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

	public List<DSHoatDong> TimKiem(String maSinhVien, String NamHoc, int HocKy) throws SQLException{
		List<DSHoatDong> hoatDongs = new ArrayList<DSHoatDong>();
		
		Connection connection = ConnectionMySQL.getConnection();
		PreparedStatement  ps; 
		
		if(HocKy == 0) {
			ps = (PreparedStatement) connection.prepareStatement("select * from dangkyhoatdong "
					+ "INNER JOIN hoatdong ON dangkyhoatdong.MaHoatDong = hoatdong.MaHoatDong "
					+ "where dangkyhoatdong.MaSinhVien = ? and hoatdong.NamHoc= ?");
			ps.setString(1, maSinhVien);
			ps.setString(2, NamHoc);
		}else if(NamHoc == "") {
			ps = (PreparedStatement) connection.prepareStatement("select * from dangkyhoatdong "
					+ "INNER JOIN hoatdong ON dangkyhoatdong.MaHoatDong = hoatdong.MaHoatDong "
					+ "where dangkyhoatdong.MaSinhVien = ? and hoatdong.HocKy= ?");
			ps.setString(1, maSinhVien);
			ps.setInt(2, HocKy);
		}else {
			ps = (PreparedStatement) connection.prepareStatement("select * from dangkyhoatdong "
					+ "INNER JOIN hoatdong ON dangkyhoatdong.MaHoatDong = hoatdong.MaHoatDong "
					+ "where dangkyhoatdong.MaSinhVien = ? and hoatdong.NamHoc = ? AND hoatdong.HocKy = ?");
			ps.setString(1, maSinhVien);
			ps.setString(2, NamHoc);
			ps.setInt(3, HocKy);
		}
		
		ResultSet resultSet = ps.executeQuery();
		
		while(resultSet.next()) {
			String tinhTrang = resultSet.getString("ThamGia");
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
			
			DSHoatDong dsHoatDong = new DSHoatDong(maSinhVien, tinhTrang, maHoatDong, tenHoatDong, ngayDienRa, thoiGianDienRa, noiDung, namHoc, hocKy, capDienRa, khoaThamGia, soLuong, khoaThamGia);
			hoatDongs.add(dsHoatDong);
		}
		return hoatDongs;
	}
	/*
	 * public static void main(String[] args) { System.out.println("Hello");
	 * DSHoatDongService dsHoatDongService = new DSHoatDongService(); String
	 * usernameString = "4151050048"; List<DSHoatDong> list; try { list =
	 * (List<DSHoatDong>) dsHoatDongService.getAllHDDangky(usernameString); for
	 * (DSHoatDong dsDangKyHoatDong : list) { System.out.println("Hello" +
	 * dsDangKyHoatDong.getTinhTrang()); } } catch (SQLException e) {} }
	 */

}
