package Model;

import java.sql.Date;
import java.sql.Time;

public class GopY {
	private int id;
	private String maSinhVien;
	private Date ngayGopY;
	private Time gioGopY;
	private String tieuDe, noiDung;

	public GopY() {
		super();
	}
	

	public GopY(int id, String maSinhVien, Date ngayGopY, Time gioGopY, String tieuDe, String noiDung) {
		super();
		this.id = id;
		this.maSinhVien = maSinhVien;
		this.ngayGopY = ngayGopY;
		this.gioGopY = gioGopY;
		this.tieuDe = tieuDe;
		this.noiDung = noiDung;
	}




	public GopY(String maSinhVien, Date ngayGopY, Time gioGopY, String tieuDe, String noiDung) {
		super();
		this.maSinhVien = maSinhVien;
		this.ngayGopY = ngayGopY;
		this.gioGopY = gioGopY;
		this.tieuDe = tieuDe;
		this.noiDung = noiDung;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	
	public String getMaSinhVien() {
		return maSinhVien;
	}


	public void setMaSinhVien(String maSinhVien) {
		this.maSinhVien = maSinhVien;
	}


	public String getTieuDe() {
		return tieuDe;
	}


	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}


	public String getNoiDung() {
		return noiDung;
	}


	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}


	public Date getNgayGopY() {
		return ngayGopY;
	}


	public void setNgayGopY(Date ngayGopY) {
		this.ngayGopY = ngayGopY;
	}


	public Time getGioGopY() {
		return gioGopY;
	}


	public void setGioGopY(Time gioGopY) {
		this.gioGopY = gioGopY;
	}




	
	
}
