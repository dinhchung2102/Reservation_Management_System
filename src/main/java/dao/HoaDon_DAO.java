package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entity.HoaDon;


public class HoaDon_DAO {
    public ArrayList<HoaDon> getAllHoaDon() {
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
        Connection con = ConnectDB.getConnection();
        String query = "select * from HoaDon";

        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                int maHoaDon = rs.getInt("maHoaDon");
                Timestamp timestampThoiGianThanhToan = rs.getTimestamp("thoiGianThanhToan");
                LocalDateTime thoiGianThanhToan = timestampThoiGianThanhToan.toLocalDateTime();

                HoaDon hoaDon = new HoaDon(maHoaDon, thoiGianThanhToan);

                dsHoaDon.add(hoaDon);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dsHoaDon;
    }
    public HoaDon getHoaDonTheoMa(int maHoaDonInput) {
        HoaDon hoaDon = null;

        Connection con = ConnectDB.getConnection();
        String query = "select * from HoaDon where maHoaDon = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1, maHoaDonInput);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int maHoaDon = rs.getInt("maHoaDon");
                Timestamp timestampThoiGianThanhToan = rs.getTimestamp("thoiGianThanhToan");
                LocalDateTime thoiGianThanhToan = timestampThoiGianThanhToan.toLocalDateTime();

                hoaDon = new HoaDon(maHoaDon, thoiGianThanhToan);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return hoaDon;
    }

    public boolean themHoaDon(HoaDon hoaDon) {
        Connection con = ConnectDB.getConnection();
        String query = "insert into HoaDon (thoiGianThanhToan) values (?)"; // Cập nhật câu lệnh SQL nếu cần
        int n = 0;
        try {
            PreparedStatement pstm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setTimestamp(1, Timestamp.valueOf(hoaDon.getThoiGianThanhToan()));

            n = pstm.executeUpdate();
            if (n > 0) {
                try (ResultSet rs = pstm.getGeneratedKeys()) {
                    if (rs.next()) {
                        int maHoaDon = rs.getInt(1);
                        hoaDon.setMaHoaDon(maHoaDon);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
}
