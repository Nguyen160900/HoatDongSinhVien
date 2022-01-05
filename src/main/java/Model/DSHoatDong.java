package Model;

import java.sql.Date;

public class DSHoatDong extends HoatDong {
	private String maSinhVien, tinhTrang;

	public DSHoatDong(String maSinhVien, String tinhTrang, int maHoatDong, String tenHoatDong, Date ngayDienRa,
			String thoiGianDienRa, String noiDung, String namHoc, int hocKy, String capDienRa, String khoaThamGia,
			int soLuong, String file) {
		super(maHoatDong, tenHoatDong, ngayDienRa, thoiGianDienRa, noiDung, namHoc, hocKy, capDienRa, khoaThamGia, soLuong, file);
		this.maSinhVien = maSinhVien;
		this.tinhTrang = tinhTrang;
	}

	public String getMaSinhVien() {
		return maSinhVien;
	}

	public void setMaSinhVien(String maSinhVien) {
		this.maSinhVien = maSinhVien;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
}
