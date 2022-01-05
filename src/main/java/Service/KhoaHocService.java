package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionMySQL;
import Model.HoatDong;
import Model.KhoaHoc;

public class KhoaHocService {
	
	public List<KhoaHoc> getKhoaHoc() throws SQLException{
		List<KhoaHoc> list = new ArrayList<KhoaHoc>();
		
		Connection connection = ConnectionMySQL.getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement("select * from khoahoc order by NamBatDau DESC limit 5");
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			String maKhoaHocString = resultSet.getString("MaKhoaHoc");
			
			KhoaHoc khoaHoc = new KhoaHoc(maKhoaHocString);
			
			list.add(khoaHoc);
		}
		
		return list;
	}
}
