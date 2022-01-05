package Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionMySQL;
import Model.GopY;

public class GopYService {
	public List<GopY> getAll_GopYs(String maSinhVien) throws SQLException {
		List<GopY> list = new ArrayList<GopY>();
		
		Connection connection = ConnectionMySQL.getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement("select * from homthugopy where MaSinhVien = ? order by NgayGopY DESC, GioGopY DESC");
		preparedStatement.setString(1, maSinhVien);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			int id = resultSet.getInt("ID");
			String maSV = resultSet.getString("MaSinhVien");
			Date ngayGopY = resultSet.getDate("NgayGopY");
			Time gioGopY = resultSet.getTime("GioGopY");
			String tieuDe = resultSet.getString("TieuDe");
			String noiDung = resultSet.getString("NoiDung");
			
			GopY gopY = new GopY(id, maSinhVien, ngayGopY, gioGopY, tieuDe, noiDung);
			
			list.add(gopY);
		}
		return list;
	}
	
	
	public int insert(String maSinhVien,Date ngayGopY, Time gioGopY, String tieuDe, String noiDung) throws SQLException {
		int status = 0;
		Connection connection = ConnectionMySQL.getConnection();
	
		PreparedStatement preparedStatement = connection.prepareStatement("insert into homthugopy(MaSinhVien, NgayGopY, GioGopY, TieuDe, NoiDung) values(?,?,?,?,?)");
		preparedStatement.setString(1, maSinhVien);
		preparedStatement.setDate(2, ngayGopY);
		preparedStatement.setTime(3, gioGopY);
		preparedStatement.setString(4, tieuDe);
		preparedStatement.setString(5, noiDung);
		
		status = preparedStatement.executeUpdate();
		
		connection.close();		
		
		return status;
	}
}
