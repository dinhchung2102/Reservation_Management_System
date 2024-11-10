package entity;

import java.time.LocalDateTime;

public class ThayDoiDatBan {
    private  int maThayDoi;
    private PhieuDatBan phieuDatBan;
    private  NhanVien nhanVien;
    private LocalDateTime ngayThayDoi;
    private  String noiDungThayDoi;

    public ThayDoiDatBan() {
        super();
    }

    public ThayDoiDatBan(int maThayDoi, PhieuDatBan phieuDatBan, NhanVien nhanVien, LocalDateTime ngayThayDoi, String noiDungThayDoi) {
        this.maThayDoi = maThayDoi;
        this.phieuDatBan = phieuDatBan;
        this.nhanVien = nhanVien;
        this.ngayThayDoi = ngayThayDoi;
        this.noiDungThayDoi = noiDungThayDoi;
    }

    public ThayDoiDatBan(PhieuDatBan phieuDatBan, NhanVien nhanVien, LocalDateTime ngayThayDoi, String noiDungThayDoi) {
        this.phieuDatBan = phieuDatBan;
        this.nhanVien = nhanVien;
        this.ngayThayDoi = ngayThayDoi;
        this.noiDungThayDoi = noiDungThayDoi;
    }

    public int getMaThayDoi() {
        return maThayDoi;
    }

    public void setMaThayDoi(int maThayDoi) {
        this.maThayDoi = maThayDoi;
    }

    public PhieuDatBan getPhieuDatBan() {
        return phieuDatBan;
    }

    public void setPhieuDatBan(PhieuDatBan phieuDatBan) {
        this.phieuDatBan = phieuDatBan;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public LocalDateTime getNgayThayDoi() {
        return ngayThayDoi;
    }

    public void setNgayThayDoi(LocalDateTime ngayThayDoi) {
        this.ngayThayDoi = ngayThayDoi;
    }

    public String getNoiDungThayDoi() {
        return noiDungThayDoi;
    }

    public void setNoiDungThayDoi(String noiDungThayDoi) {
        this.noiDungThayDoi = noiDungThayDoi;
    }
}
