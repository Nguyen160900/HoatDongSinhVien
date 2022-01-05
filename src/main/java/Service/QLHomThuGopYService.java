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

public class QLHomThuGopYService {
	
	public List<GopY> getAll_GopYs(int a, int b) throws SQLException {
		List<GopY> list = new ArrayList<GopY>();
		
		Connection connection = ConnectionMySQL.getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement("select * from homthugopy order by NgayGopY DESC, GioGopY DESC limit ?,?");
		preparedStatement.setInt(1, a);
		preparedStatement.setInt(2, b);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			int id = resultSet.getInt("ID");
			String maSV = resultSet.getString("MaSinhVien");
			Date ngayGopY = resultSet.getDate("NgayGopY");
			Time gioGopY = resultSet.getTime("GioGopY");
			String tieuDe = resultSet.getString("TieuDe");
			String noiDung = resultSet.getString("NoiDung");
			
			GopY gopY = new GopY(id, maSV, ngayGopY, gioGopY, tieuDe, noiDung);
			
			list.add(gopY);
		}
		return list;
	}
	
	public int getCount() {
	    Connection conn = ConnectionMySQL.getConnection();
	    ArrayList<GopY> list = new ArrayList();
	    String sql = "SELECT count(ID) as dem FROM homthugopy";
	    int count = 0;
	    try {
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            count = rs.getInt("dem");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return count;
	}
	
	public int delete(int maGopY) throws SQLException {
		int status = 0;
		
		Connection connection = ConnectionMySQL.getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement("delete from homthugopy where ID = ?");
		preparedStatement.setInt(1, maGopY);
		
		status = preparedStatement.executeUpdate();
		
		connection.close();		
		
		return status;
	}
}
