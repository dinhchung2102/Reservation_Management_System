package entity;

public class ChiTietPhieuDatBan {
    private float donGia;
    private int soLuong;
    private float thanhTien;
    private MonAn monAn;
    private PhieuDatBan phieuDatBan;
    public ChiTietPhieuDatBan() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ChiTietPhieuDatBan(float donGia, int soLuong, float thanhTien, MonAn monAn, PhieuDatBan phieuDatBan) {
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.monAn = monAn;
        this.phieuDatBan = phieuDatBan;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }

    public MonAn getMonAn() {
        return monAn;
    }

    public void setMonAn(MonAn monAn) {
        this.monAn = monAn;
    }

    public PhieuDatBan getPhieuDatBan() {
        return phieuDatBan;
    }

    public void setPhieuDatBan(PhieuDatBan phieuDatBan) {
        this.phieuDatBan = phieuDatBan;
    }
}
