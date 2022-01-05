package Model;

import java.sql.Date;

public class GiangVien {
	private String MaGiangVien, HoTen, GioiTinh, SDT, Email, MatKhau;
	private Date NgaySinh;

	public GiangVien(String maGiangVien, String hoTen, String gioiTinh, String sDT, String email, String matKhau, Date ngaySinh) {
		super();
		MaGiangVien = maGiangVien;
		HoTen = hoTen;
		GioiTinh = gioiTinh;
		SDT = sDT;
		Email = email;
		MatKhau = matKhau;
		NgaySinh = ngaySinh;
	}

	public String getMaGiangVien() {
		return MaGiangVien;
	}

	public void setMaGiangVien(String maGiangVien) {
		MaGiangVien = maGiangVien;
	}

	public String getHoTen() {
		return HoTen;
	}

	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}

	public String getGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getMatKhau() {
		return MatKhau;
	}

	public void setMatKhau(String matKhau) {
		MatKhau = matKhau;
	}

	public Date getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		NgaySinh = ngaySinh;
	}
}
