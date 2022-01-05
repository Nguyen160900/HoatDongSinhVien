package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Connection.ConnectionMySQL;

public class DMKService {
	public boolean CheckMK(String username, String password) {
		boolean ktr = false;
		try {
			Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from sinhvien where MaSinhVien=? and MatKhau=?");  
	        ps.setString(1, username);
	        ps.setString(2, password);
	        
	        ResultSet rs=ps.executeQuery();  
			
			if (rs.next()) {
				ktr = true;
			} else {
				ktr = false;
			}
		} catch (Exception e) {}
		
		return ktr;
	}
	
	
	public int DoiMatKhau(String username, String password) {
		int status = 0;
		try {
			Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement ps = connection.prepareStatement("update sinhvien set MatKhau=? where MaSinhVien=?");  
	        ps.setString(1, password);
	        ps.setString(2, username);

	        status = ps.executeUpdate();
	        connection.close();
		}catch (Exception e) {}
		return status;
	}
	
	public boolean CheckMK_Admin(String username, String password) {
		boolean ktr = false;
		try {
			Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from giangvien where MaGiangVien=? and MatKhau=?");  
	        ps.setString(1, username);
	        ps.setString(2, password);
	        
	        ResultSet rs=ps.executeQuery();  
			
			if (rs.next()) {
				ktr = true;
			} else {
				ktr = false;
			}
		} catch (Exception e) {}
		
		return ktr;
	}
	
	public int DoiMatKhau_AD(String username, String password) {
		int status = 0;
		try {
			Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement ps = connection.prepareStatement("update giangvien set MatKhau=? where MaGiangVien=?");  
	        ps.setString(1, password);
	        ps.setString(2, username);

	        status = ps.executeUpdate();
	        connection.close();
		}catch (Exception e) {}
		return status;
	}
}
