package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionMySQL;
import Model.Lop;

public class LopService {
	Lop lop;

	public Lop getLop(String maSinhVien) {

		Connection connection = ConnectionMySQL.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from sinhvien inner join lop on lop.MaLop = sinhvien.MaLop where MaSinhVien = ?");
			ps.setString(1, maSinhVien);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String maLop = rs.getString("MaLop");
				String tenLop = rs.getString("TenLop");

				lop = new Lop(maLop, tenLop);
			}
		} catch (Exception e) {
		}

		return lop;
	}

	public List<Lop> getLop_1(int maHoatDong) {

		List<Lop> list = new ArrayList<>();
		Connection connection = ConnectionMySQL.getConnection();
		try {

			PreparedStatement ps = connection.prepareStatement("select  DISTINCT lop.MaLop, lop.TenLop from lop inner join sinhvien on sinhvien.MaLop = lop.MaLop inner join dangkyhoatdong on dangkyhoatdong.MaSinhVien = sinhvien.MaSinhVien where MaHoatDong = ?");
			ps.setInt(1, maHoatDong);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String maLop = rs.getString("MaLop");
				String tenLop = rs.getString("TenLop");

				lop = new Lop(maLop, tenLop);

				list.add(lop);
			}
		} catch (Exception e) {
		}

		return list;
	}
}
