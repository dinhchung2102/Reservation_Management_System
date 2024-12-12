package dao;

import connectDB.ConnectDB;
import entity.ThayDoiDatBan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class DAO_ThayDoiDatBan {
    public boolean huyDatBan(ThayDoiDatBan thayDoiDatBan) {
        Connection con = ConnectDB.getConnection();
        String query = "INSERT INTO ThayDoiDatBan(maPhieuDatBan, maNV, ngayThayDoi, noiDungThayDoi) VALUES(?, ?, ?, ?)";

        try {
            PreparedStatement pstm = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            pstm.setInt(1, thayDoiDatBan.getPhieuDatBan().getMaPhieuDatBan());
            pstm.setInt(2, thayDoiDatBan.getNhanVien().getMaNV());
            pstm.setTimestamp(3, Timestamp.valueOf(thayDoiDatBan.getNgayThayDoi()));
            pstm.setString(4, thayDoiDatBan.getNoiDungThayDoi());

            pstm.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
