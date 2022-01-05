package Model;

import java.sql.Date;

public class DangKyHoatDong {
	private String maSinhVien, hoTen, tenHoatDong, tinhTrang, maLop, tenLop;
	private int maHoatDong;
	private Date ngaySinh;
	
	public DangKyHoatDong() {
		super();
	}
	
	public DangKyHoatDong(String tenHoatDong, String maSinhVien, String hoTen, Date ngaySinh, String tenLop, String tinhTrang) {
		super();
		this.tenHoatDong = tenHoatDong;
		this.maSinhVien = maSinhVien;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.tenLop = tenLop;
		this.tinhTrang = tinhTrang;
	}
	
	public DangKyHoatDong(String maSinhVien, String hoTen, Date ngaySinh, String tenLop, String tinhTrang) {
		this.maSinhVien = maSinhVien;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.tenLop = tenLop;
		this.tinhTrang = tinhTrang;
	}
	
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getMaSinhVien() {
		return maSinhVien;
	}
	public String getMaLop() {
		return maLop;
	}
	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}
	public String getTenLop() {
		return tenLop;
	}
	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}
	public void setMaSinhVien(String maSinhVien) {
		this.maSinhVien = maSinhVien;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getTenHoatDong() {
		return tenHoatDong;
	}
	public void setTenHoatDong(String tenHoatDong) {
		this.tenHoatDong = tenHoatDong;
	}
	public String getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	public int getMaHoatDong() {
		return maHoatDong;
	}
	public void setMaHoatDong(int maHoatDong) {
		this.maHoatDong = maHoatDong;
	}
	
	
}
