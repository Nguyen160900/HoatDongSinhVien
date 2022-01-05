package Model;

public class KhoaHoc {
	private String MaKhoaHoc;
	private int NamBatDau, NamKetThuc;
	
	public KhoaHoc() {
		super();
	}
	
	public KhoaHoc(String MaKhoaHoc) {
		this.MaKhoaHoc = MaKhoaHoc;
	}
	
	public KhoaHoc (String MaKhoaHoc, int NamBatDau, int NamKetThuc) {
		this.MaKhoaHoc = MaKhoaHoc;
		this.NamBatDau = NamBatDau;
		this.NamKetThuc = NamKetThuc;
	}
	
	public String getMaKhoaHoc() {
		return MaKhoaHoc;
	}
	public void setMaKhoaHoc(String maKhoaHoc) {
		MaKhoaHoc = maKhoaHoc;
	}
	public int getNamBatDau() {
		return NamBatDau;
	}
	public void setNamBatDau(int namBatDau) {
		NamBatDau = namBatDau;
	}
	public int getNamKetThuc() {
		return NamKetThuc;
	}
	public void setNamKetThuc(int namKetThuc) {
		NamKetThuc = namKetThuc;
	}
		
}
