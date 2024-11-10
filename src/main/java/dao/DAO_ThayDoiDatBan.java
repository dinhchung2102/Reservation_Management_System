package dao;

import connectDB.ConnectDB;
import entity.ThayDoiDatBan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class DAO_ThayDoiDatBan {
    public boolean huyDatBan(ThayDoiDatBan thayDoiDatBan) {
        // Kết nối đến cơ sở dữ liệu
        Connection con = ConnectDB.getConnection();
        String query = "INSERT INTO ThayDoiDatBan(maPhieuDatBan, maNV, ngayThayDoi, noiDungThayDoi) VALUES(?, ?, ?, ?)";

        try {
            // Tạo PreparedStatement với tùy chọn RETURN_GENERATED_KEYS để lấy khóa chính nếu cần
            PreparedStatement pstm = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            // Thiết lập các tham số cho PreparedStatement
            pstm.setInt(1, thayDoiDatBan.getPhieuDatBan().getMaPhieuDatBan());
            pstm.setInt(2, thayDoiDatBan.getNhanVien().getMaNV());
            pstm.setTimestamp(3, Timestamp.valueOf(thayDoiDatBan.getNgayThayDoi()));
            pstm.setString(4, thayDoiDatBan.getNoiDungThayDoi());

            // Thực thi câu lệnh cập nhật
            pstm.executeUpdate();

            return true; // Trả về true nếu thực hiện thành công

        } catch (Exception e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi
        }
    }
}
