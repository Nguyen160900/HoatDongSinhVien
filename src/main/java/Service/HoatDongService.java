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

public class HoatDongService {

	public List<HoatDong> getAllHoatDong() {
		List<HoatDong> list = new ArrayList<>();
		Connection connection = ConnectionMySQL.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from hoatdong");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Integer maHoatDong = rs.getInt("MaHoatDong");
				String tenHoatDong = rs.getString("TenHoatDong");
				Date ngayDienRa = rs.getDate("NgayDienRa");
				String thoiGianDienRa = rs.getString("ThoiGianDienRa");
				String noiDung = rs.getString("NoiDung");
				String namHoc = rs.getString("NamHoc");
				Integer hocKy = rs.getInt("HocKy");
				String capDienRa = rs.getString("CapDienRa");
				String khoaThamGia = rs.getString("KhoaThamGia");
				Integer soLuong = rs.getInt("SoLuong");
				String file = rs.getString("File");
				
				HoatDong hoatDong = new HoatDong(maHoatDong, tenHoatDong, ngayDienRa, thoiGianDienRa, noiDung, namHoc, hocKy, capDienRa, khoaThamGia, soLuong, file);
				list.add(hoatDong);
				
			}
		} catch (SQLException e) { }
		
		return list;
	}
	
	public List<HoatDong> getHoatDong_LocHoatDong(Date ngayDienRaHoatDong) {
		List<HoatDong> list = new ArrayList<HoatDong>();
		Connection connection = ConnectionMySQL.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from hoatdong where NgayDienRa >= ? ORDER BY NgayDienRa DESC");
			ps.setDate(1, ngayDienRaHoatDong);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Integer maHoatDong = rs.getInt("MaHoatDong");
				String tenHoatDong = rs.getString("TenHoatDong");
				Date ngayDienRa = rs.getDate("NgayDienRa");
				String thoiGianDienRa = rs.getString("ThoiGianDienRa");
				String noiDung = rs.getString("NoiDung");
				String namHoc = rs.getString("NamHoc");
				Integer hocKy = rs.getInt("HocKy");
				String capDienRa = rs.getString("CapDienRa");
				String khoaThamGia = rs.getString("KhoaThamGia");
				Integer soLuong = rs.getInt("SoLuong");
				String file = rs.getString("File");
				
				HoatDong hoatDong = new HoatDong(maHoatDong, tenHoatDong, ngayDienRa, thoiGianDienRa, noiDung, namHoc, hocKy, capDienRa, khoaThamGia, soLuong, file);
				list.add(hoatDong);
				
			}
		} catch (SQLException e) { }
		
		return list;
	}
	
	HoatDong hoatDong;
	
	public HoatDong getHoatDong(Integer maHoatDong_Loc) {
		Connection connection = ConnectionMySQL.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from hoatdong where MaHoatDong = ?");
			ps.setInt(1, maHoatDong_Loc);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				Integer maHoatDong = rs.getInt("MaHoatDong");
				String tenHoatDong = rs.getString("TenHoatDong");
				Date ngayDienRa = rs.getDate("NgayDienRa");
				String thoiGianDienRa = rs.getString("ThoiGianDienRa");
				String noiDung = rs.getString("NoiDung");
				String namHoc = rs.getString("NamHoc");
				Integer hocKy = rs.getInt("HocKy");
				String capDienRa = rs.getString("CapDienRa");
				String khoaThamGia = rs.getString("KhoaThamGia");
				Integer soLuong = rs.getInt("SoLuong");
				String file = rs.getString("File");
				
				hoatDong = new HoatDong(maHoatDong, tenHoatDong, ngayDienRa, thoiGianDienRa, noiDung, namHoc, hocKy, capDienRa, khoaThamGia, soLuong, file);
				
			}
		} catch (SQLException e) { }
		
		return hoatDong;
	}
	
	public boolean DangKyHoatDong(String maSinhVien, int maHoatDong)
	{
		boolean kTra = false;
		String tinhTrang = "Đăng ký";
		
		Connection connection = ConnectionMySQL.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from dangkyhoatdong where MaSinhVien = ? and MaHoatDong = ? and ThamGia = ?");
			ps.setString(1, maSinhVien);
			ps.setInt(2, maHoatDong);
			ps.setString(3, tinhTrang);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				kTra = true;
			}else {
				kTra = false;
			}
		} catch (SQLException e) { }
		
		return kTra;
	}
	
	public static int DangKy(String maSinhVien, int maHoatDong, String tinhTrang){  
        int status=0;  
        try
        {  
            Connection con = ConnectionMySQL.getConnection();  
            PreparedStatement ps = con.prepareStatement("insert into dangkyhoatdong(MaHoatDong, MaSinhVien, ThamGia) values (?,?,?)");  
            ps.setInt(1,maHoatDong);  
            ps.setString(2,maSinhVien);  
            ps.setString(3,tinhTrang);   
              
            status = ps.executeUpdate();  
              
            con.close();  
        }
        catch(Exception ex){ex.printStackTrace();}  
          
        return status;  
    }  
}
