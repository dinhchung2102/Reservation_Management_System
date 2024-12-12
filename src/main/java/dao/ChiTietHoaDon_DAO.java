package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhuyenMai;
import entity.PhieuDatBan;
import entity.Thue;

public class ChiTietHoaDon_DAO {
    private HoaDon_DAO hoaDon_DAO = new HoaDon_DAO();
    private PhieuDatBan_DAO phieuDatBan_DAO = new PhieuDatBan_DAO();
    private KhuyenMaiDAO khuyenMai_DAO = new KhuyenMaiDAO();
    private Thue_DAO thue_DAO = new Thue_DAO();

    public ArrayList<ChiTietHoaDon> getAllChiTietHoaDon() {
        ArrayList<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<>();
        Connection con = ConnectDB.getConnection();
        String query = "select * from ChiTietHoaDon";

        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                int maChiTietHoaDon = rs.getInt("maChiTietHoaDon");

                HoaDon hoaDon = hoaDon_DAO.getHoaDonTheoMa(rs.getInt("maHoaDon"));
                PhieuDatBan phieuDatBan = phieuDatBan_DAO.getPhieuDatBanTheoMa(rs.getInt("maPhieuDatBan"));
                KhuyenMai khuyenMai = khuyenMai_DAO.getKhuyenMaiByMaKM(rs.getInt("maKM"));
                Thue thue = thue_DAO.getThueTheoMa(rs.getInt("maThue"));

                Double tongThanhTien = rs.getDouble("tongThanhTien");
                Double tongTienCuoi = rs.getDouble("tongTienCuoi");

                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(maChiTietHoaDon, hoaDon, phieuDatBan, khuyenMai, thue,
                        tongThanhTien, tongTienCuoi);

                dsChiTietHoaDon.add(chiTietHoaDon);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dsChiTietHoaDon;
    }

    public boolean themChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        Connection con = ConnectDB.getConnection();
        String query = "insert into ChiTietHoaDon values(?,?,?,?,?,?,?)";
        int n = 0;
        try {
            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setInt(1, chiTietHoaDon.getMaChiTietHoaDon());
            pstm.setInt(2, chiTietHoaDon.getHoaDon().getMaHoaDon());
            pstm.setInt(3, chiTietHoaDon.getPhieuDatBan().getMaPhieuDatBan());
            pstm.setInt(4, chiTietHoaDon.getKhuyenMai().getMaKM());
            pstm.setInt(5, chiTietHoaDon.getThue().getMaThue());
            pstm.setDouble(6, chiTietHoaDon.getTongThanhTien());
            pstm.setDouble(7, chiTietHoaDon.getTongTienCuoi());

            n = pstm.executeUpdate();
        } catch (Exception e) {
            // Kiểm tra lỗi Violation of PRIMARY KEY constraint
            if (e.getMessage().contains("Violation of PRIMARY KEY constraint")) {
                JOptionPane.showMessageDialog(null, "Lỗi: phiếu đặt này đã được tạo hóa đơn");
            } else {
                // Xử lý các lỗi SQL khác
                e.printStackTrace();
            }
        }
        return n > 0;
    }

    public ChiTietHoaDon getChiTietHoaDonTheoMa(int maChiTietHoaDonInput) {
        ChiTietHoaDon chiTietHoaDon = null;

        Connection con = ConnectDB.getConnection();
        String query = "select * from ChiTietHoaDon where maChiTietHoaDon = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1, maChiTietHoaDonInput);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int maChiTietHoaDon = rs.getInt("maChiTietHoaDon");

                HoaDon hoaDon = hoaDon_DAO.getHoaDonTheoMa(rs.getInt("maHoaDon"));
                PhieuDatBan phieuDatBan = phieuDatBan_DAO.getPhieuDatBanTheoMa(rs.getInt("maPhieuDatBan"));
                KhuyenMai khuyenMai = khuyenMai_DAO.getKhuyenMaiByMaKM(rs.getInt("maKM"));
                Thue thue = thue_DAO.getThueTheoMa(rs.getInt("maThue"));

                Double tongThanhTien = rs.getDouble("tongThanhTien");
                Double tongTienCuoi = rs.getDouble("tongTienCuoi");

                chiTietHoaDon = new ChiTietHoaDon(maChiTietHoaDon, hoaDon, phieuDatBan, khuyenMai, thue,
                        tongThanhTien, tongTienCuoi);

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return chiTietHoaDon;
    }
}
