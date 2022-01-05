package Model;

import java.sql.Date;

public class HoatDong {
	private int MaHoatDong, HocKy, SoLuong;
	private String TenHoatDong, ThoiGianDienRa, NoiDung, NamHoc, CapDienRa, KhoaThamGia, File;
	private Date ngayDienRa;
	
	public HoatDong() {
		super();
	}
	
	public HoatDong(int maHoatDong, String tenHoatDong, Date ngayDienRa, String thoiGianDienRa, String noiDung, 
			String namHoc, int hocKy, String capDienRa, String khoaThamGia, int soLuong, String file) {
		super();
		this.MaHoatDong = maHoatDong;
		this.HocKy = hocKy;
		this.SoLuong = soLuong;
		this.TenHoatDong = tenHoatDong;
		this.ThoiGianDienRa = thoiGianDienRa;
		this.NoiDung = noiDung;
		this.NamHoc = namHoc;
		this.CapDienRa = capDienRa;
		this.KhoaThamGia = khoaThamGia;
		this.File = file;
		this.ngayDienRa = ngayDienRa;
	}

	public int getMaHoatDong() {
		return MaHoatDong;
	}
	public void setMaHoatDong(int maHoatDong) {
		MaHoatDong = maHoatDong;
	}
	public int getHocKy() {
		return HocKy;
	}
	public void setHocKy(int hocKy) {
		HocKy = hocKy;
	}
	public int getSoLuong() {
		return SoLuong;
	}
	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}
	public String getTenHoatDong() {
		return TenHoatDong;
	}
	public void setTenHoatDong(String tenHoatDong) {
		TenHoatDong = tenHoatDong;
	}
	public String getThoiGianDienRa() {
		return ThoiGianDienRa;
	}
	public void setThoiGianDienRa(String thoiGianDienRa) {
		ThoiGianDienRa = thoiGianDienRa;
	}
	public String getNoiDung() {
		return NoiDung;
	}
	public void setNoiDung(String noiDung) {
		NoiDung = noiDung;
	}
	public String getNamHoc() {
		return NamHoc;
	}
	public void setNamHoc(String namHoc) {
		NamHoc = namHoc;
	}
	public String getCapDienRa() {
		return CapDienRa;
	}
	public void setCapDienRa(String capDienRa) {
		CapDienRa = capDienRa;
	}
	public String getKhoaThamGia() {
		return KhoaThamGia;
	}
	public void setKhoaThamGia(String khoaThamGia) {
		KhoaThamGia = khoaThamGia;
	}
	public String getFile() {
		return File;
	}
	public void setFile(String file) {
		File = file;
	}
	public Date getNgayDienRa() {
		return ngayDienRa;
	}
	public void setNgayDienRa(Date ngayDienRa) {
		this.ngayDienRa = ngayDienRa;
	}
	
	
}
