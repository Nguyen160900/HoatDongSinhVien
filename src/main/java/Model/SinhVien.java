package Model;

import java.sql.Date;

public class SinhVien extends Lop {
	private String MaSinhVien, HoTen, GioiTinh, SDT, Email, ChucVu, TinhTrangHoc, MatKhau;
	private int Quyen;
	private Date NgaySinh;
	
	public SinhVien() {
		super();
	}
	
	public SinhVien(String maSinhVien, String hoTen, String gioiTinh, String sDT, String email, String chucVu,
			String tinhTrangHoc, String matKhau, int quyen, Date ngaySinh) {
		super();
		MaSinhVien = maSinhVien;
		HoTen = hoTen;
		GioiTinh = gioiTinh;
		SDT = sDT;
		Email = email;
		ChucVu = chucVu;
		TinhTrangHoc = tinhTrangHoc;
		MatKhau = matKhau;
		Quyen = quyen;
		NgaySinh = ngaySinh;
	}
	
	public SinhVien(String maSinhVien, String hoTen, String gioiTinh, String sDT, String email, String chucVu,
			String tinhTrangHoc, String matKhau, int quyen, Date ngaySinh, String MaLop, String TenLop, String MaKhoaHoc) {
		super(MaKhoaHoc, MaLop, TenLop);
		MaSinhVien = maSinhVien;
		HoTen = hoTen;
		GioiTinh = gioiTinh;
		SDT = sDT;
		Email = email;
		ChucVu = chucVu;
		TinhTrangHoc = tinhTrangHoc;
		MatKhau = matKhau;
		Quyen = quyen;
		NgaySinh = ngaySinh;
	}

	public SinhVien(String maSinhVien, String hoTen, String maLop, String tenLop) {
		super(maLop, tenLop);
		this.MaSinhVien = maSinhVien;
		this.HoTen = hoTen;
	}
	
	public SinhVien(String maSinhVien, String hoTen, String maKhoaHoc, String maLop, String tenLop) {
		super(maKhoaHoc, maLop, tenLop);
		this.MaSinhVien = maSinhVien;
		this.HoTen = hoTen;
	}
	
	public SinhVien (String MaKhoaHoc, String MaLop, String TenLop, int NamBatDau, int NamKetThuc) {
		super(MaKhoaHoc, MaLop, TenLop, NamBatDau, NamKetThuc);
		
	}
	
	public String getMaSinhVien() {
		return MaSinhVien;
	}
	public void setMaSinhVien(String maSinhVien) {
		MaSinhVien = maSinhVien;
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
	public String getChucVu() {
		return ChucVu;
	}
	public void setChucVu(String chucVu) {
		ChucVu = chucVu;
	}
	public String getTinhTrangHoc() {
		return TinhTrangHoc;
	}
	public void setTinhTrangHoc(String tinhTrangHoc) {
		TinhTrangHoc = tinhTrangHoc;
	}
	public String getMatKhau() {
		return MatKhau;
	}
	public void setMatKhau(String matKhau) {
		MatKhau = matKhau;
	}
	public int getQuyen() {
		return Quyen;
	}
	public void setQuyen(int quyen) {
		Quyen = quyen;
	}
	public Date getNgaySinh() {
		return NgaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		NgaySinh = ngaySinh;
	}
}
