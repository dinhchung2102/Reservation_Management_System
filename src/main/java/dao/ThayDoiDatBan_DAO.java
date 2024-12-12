package dao;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.PhieuDatBan;
import entity.ThayDoiDatBan;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ThayDoiDatBan_DAO {

    // Lấy danh sách các thay đổi theo mã phiếu đặt bàn
    public ArrayList<ThayDoiDatBan> getThayDoiByMaPhieuDatBan(int maPhieuDatBan) {
        ArrayList<ThayDoiDatBan> dsThayDoi = new ArrayList<>();
        String sql = "SELECT * FROM ThayDoiDatBan WHERE maPhieuDatBan = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            preparedStatement.setInt(1, maPhieuDatBan);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int maThayDoi = rs.getInt("maThayDoi");
                LocalDateTime ngayThayDoi = rs.getTimestamp("ngayThayDoi").toLocalDateTime();
                String noiDungThayDoi = rs.getString("noiDungThayDoi");

                // Lấy thông tin Phiếu Đặt Bàn và Nhân Viên liên quan
                PhieuDatBan phieuDatBan = new PhieuDatBan_DAO().getPhieuDatBanTheoMa(rs.getInt("maPhieuDatBan"));
                NhanVien nhanVien = new NhanVien_DAO().getNhanVienTheoMa(rs.getInt("maNV"));

                ThayDoiDatBan thayDoi = new ThayDoiDatBan(maThayDoi, phieuDatBan, nhanVien, ngayThayDoi, noiDungThayDoi);
                dsThayDoi.add(thayDoi);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dsThayDoi;
    }

    // Thêm một thay đổi mới vào cơ sở dữ liệu
    public boolean addThayDoi(ThayDoiDatBan thayDoi) {
        String sql = "INSERT INTO ThayDoiDatBan (maPhieuDatBan, maNV, ngayThayDoi, noiDungThayDoi) VALUES (?, ?, ?, ?)";
        boolean isAdded = false;

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            preparedStatement.setInt(1, thayDoi.getPhieuDatBan().getMaPhieuDatBan());
            preparedStatement.setInt(2, thayDoi.getNhanVien().getMaNV());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(thayDoi.getNgayThayDoi()));
            preparedStatement.setString(4, thayDoi.getNoiDungThayDoi());

            int rowsAffected = preparedStatement.executeUpdate();
            isAdded = (rowsAffected > 0);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isAdded;
    }
}
