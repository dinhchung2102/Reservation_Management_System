package entity;

import java.time.LocalDateTime;

public class HoaDon {
    private int maHoaDon;
    private LocalDateTime thoiGianThanhToan;

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public LocalDateTime getThoiGianThanhToan() {
        return thoiGianThanhToan;
    }

    public void setThoiGianThanhToan(LocalDateTime thoiGianThanhToan) {
        this.thoiGianThanhToan = thoiGianThanhToan;
    }


    public HoaDon(int maHoaDon, LocalDateTime thoiGianThanhToan) {
        super();
        this.maHoaDon = maHoaDon;
        this.thoiGianThanhToan = thoiGianThanhToan;
    }

    public HoaDon() {
        super();
        // TODO Auto-generated constructor stub
    }

}
