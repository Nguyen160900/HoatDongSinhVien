package Model;

public class Lop extends KhoaHoc{
	private String MaLop, TenLop;
	
	public Lop() {
		super();
	}
	
	public Lop(String maLop, String tenLop) {
		super();
		this.MaLop = maLop;
		this.TenLop = tenLop;
	}
	
	public Lop(String MaKhoaHoc, String MaLop, String TenLop) {
		super(MaKhoaHoc);
		this.MaLop = MaLop;
		this.TenLop = TenLop;
	}
	
	public Lop (String MaKhoaHoc, String MaLop, String TenLop, int NamBatDau, int NamKetThuc) {
		super(MaKhoaHoc, NamBatDau, NamKetThuc);
		this.MaLop = MaLop;
		this.TenLop = TenLop;
	}


	public String getMaLop() {
		return MaLop;
	}

	public void setMaLop(String maLop) {
		MaLop = maLop;
	}

	public String getTenLop() {
		return TenLop;
	}

	public void setTenLop(String tenLop) {
		TenLop = tenLop;
	}	
}
