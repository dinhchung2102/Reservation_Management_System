package entity;

import java.util.Date;
import java.time.LocalDateTime;

public class PhieuDatBan {

//    soLuongKhach INT,
//    tienCoc FLOAT,
//    trangThai NVARCHAR(50),
//    maKH INT FOREIGN KEY REFERENCES KhachHang(maKH) ON DELETE SET NULL,
//    maNV INT FOREIGN KEY REFERENCES NhanVien(maNV) ON DELETE SET NULL,
//    maBan INT FOREIGN KEY REFERENCES Ban(maBan) ON DELETE SET NULL
    private int maPhieuDatBan;
    private LocalDateTime ngayTaoPhieu;
    private LocalDateTime thoiGianDatBan;
    private int soLuongKhach;
    private float tienCoc;
    private String trangThai;
    private KhachHang khachHang;
    private NhanVien nhanVien;
    private Ban ban;

    public PhieuDatBan() {
        super();
    };

    public PhieuDatBan(int maPhieuDatBan, LocalDateTime ngayTaoPhieu, LocalDateTime thoiGianDatBan, int soLuongKhach, float tienCoc, String trangThai, KhachHang khachHang, NhanVien nhanVien, Ban ban) {
        this.maPhieuDatBan = maPhieuDatBan;
        this.ngayTaoPhieu = ngayTaoPhieu;
        this.thoiGianDatBan = thoiGianDatBan;
        this.soLuongKhach = soLuongKhach;
        this.tienCoc = tienCoc;
        this.trangThai = trangThai;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.ban = ban;
    };

    public PhieuDatBan(LocalDateTime ngayTaoPhieu, LocalDateTime thoiGianDatBan, int soLuongKhach, float tienCoc, String trangThai, KhachHang khachHang, NhanVien nhanVien, Ban ban) {
        this.ngayTaoPhieu = ngayTaoPhieu;
        this.thoiGianDatBan = thoiGianDatBan;
        this.soLuongKhach = soLuongKhach;
        this.tienCoc = tienCoc;
        this.trangThai = trangThai;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.ban = ban;
    }

    public int getMaPhieuDatBan() {
        return maPhieuDatBan;
    }

    public void setMaPhieuDatBan(int maPhieuDatBan) {
        this.maPhieuDatBan = maPhieuDatBan;
    }

    public LocalDateTime getNgayTaoPhieu() {
        return ngayTaoPhieu;
    }

    public void setNgayTaoPhieu(LocalDateTime ngayTaoPhieu) {
        this.ngayTaoPhieu = ngayTaoPhieu;
    }

    public LocalDateTime getThoiGianDatBan() {
        return thoiGianDatBan;
    }

    public void setThoiGianDatBan(LocalDateTime thoiGianDatBan) {
        this.thoiGianDatBan = thoiGianDatBan;
    }

    public int getSoLuongKhach() {
        return soLuongKhach;
    }

    public void setSoLuongKhach(int soLuongKhach) {
        this.soLuongKhach = soLuongKhach;
    }

    public float getTienCoc() {
        return tienCoc;
    }

    public void setTienCoc(float tienCoc) {
        this.tienCoc = tienCoc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public Ban getBan() {
        return ban;
    }

    public void setBan(Ban ban) {
        this.ban = ban;
    }
}
