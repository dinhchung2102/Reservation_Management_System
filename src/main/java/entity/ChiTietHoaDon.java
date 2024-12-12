package entity;

public class ChiTietHoaDon {
    private int maChiTietHoaDon;

    private HoaDon hoaDon;
    private PhieuDatBan phieuDatBan;
    private KhuyenMai khuyenMai;
    private Thue thue;

    private Double tongThanhTien;
    private Double tongTienCuoi;


    public int getMaChiTietHoaDon() {
        return maChiTietHoaDon;
    }


    public void setMaChiTietHoaDon(int maChiTietHoaDon) {
        this.maChiTietHoaDon = maChiTietHoaDon;
    }


    public HoaDon getHoaDon() {
        return hoaDon;
    }


    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }


    public PhieuDatBan getPhieuDatBan() {
        return phieuDatBan;
    }


    public void setPhieuDatBan(PhieuDatBan phieuDatBan) {
        this.phieuDatBan = phieuDatBan;
    }


    public KhuyenMai getKhuyenMai() {
        return khuyenMai;
    }


    public void setKhuyenMai(KhuyenMai khuyenMai) {
        this.khuyenMai = khuyenMai;
    }


    public Thue getThue() {
        return thue;
    }


    public void setThue(Thue thue) {
        this.thue = thue;
    }


    public Double getTongThanhTien() {
        return tongThanhTien;
    }


    public void setTongThanhTien(Double tongThanhTien) {
        this.tongThanhTien = tongThanhTien;
    }


    public Double getTongTienCuoi() {
        return tongTienCuoi;
    }


    public void setTongTienCuoi(Double tongTienCuoi) {
        this.tongTienCuoi = tongTienCuoi;
    }



    public ChiTietHoaDon(int maChiTietHoaDon, HoaDon hoaDon, PhieuDatBan phieuDatBan, KhuyenMai khuyenMai, Thue thue,
                         Double tongThanhTien, Double tongTienCuoi) {
        super();
        this.maChiTietHoaDon = maChiTietHoaDon;
        this.hoaDon = hoaDon;
        this.phieuDatBan = phieuDatBan;
        this.khuyenMai = khuyenMai;
        this.thue = thue;
        this.tongThanhTien = tongThanhTien;
        this.tongTienCuoi = tongTienCuoi;
    }


    public ChiTietHoaDon() {
        super();
        // TODO Auto-generated constructor stub
    }

}
