package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Ban;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatBan;

public class PhieuDatBan_DAO {
	DAO_KhachHang khachHang_DAO = new DAO_KhachHang();
	NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
	DAO_Ban ban_DAO = new DAO_Ban();


	public ArrayList<PhieuDatBan> getAllPhieuDatBan() {
		ArrayList<PhieuDatBan> dsPhieuDatBan = new ArrayList<>();
		Connection con = ConnectDB.getConnection();
		String query = "select * from PhieuDatBan";

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);

			while (rs.next()) {
				int maPhieuDatBan = rs.getInt("maPhieuDatBan");

				// Lấy giá trị Timestamp từ cơ sở dữ liệu cho cả ngày và giờ
				Timestamp timestampNgayTaoPhieu = rs.getTimestamp("ngayTaoPhieu");
				LocalDateTime ngayTaoPhieu = timestampNgayTaoPhieu.toLocalDateTime();

				Timestamp timestampThoiGianDatBan = rs.getTimestamp("thoiGianDatBan");
				LocalDateTime thoiGianDatBan = timestampThoiGianDatBan.toLocalDateTime();

				int soLuongKhach = rs.getInt("soLuongKhach");
				float tienCoc = rs.getFloat("tienCoc");
				String trangThai = rs.getString("trangThai");

				KhachHang khachHang = khachHang_DAO.getKhachHangTheoMa(rs.getInt("maKH"));
				NhanVien nhanVien = nhanVien_DAO.getNhanVienTheoMa(rs.getInt("maNV"));
				Ban ban = ban_DAO.getBanById(rs.getInt("maBan"));

				PhieuDatBan phieuDatBan = new PhieuDatBan(maPhieuDatBan, ngayTaoPhieu, thoiGianDatBan, soLuongKhach, tienCoc, trangThai, khachHang, nhanVien, ban);
				dsPhieuDatBan.add(phieuDatBan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhieuDatBan;
	}



	public PhieuDatBan getPhieuDatBanTheoMa(int maPhieuDatBanInput) {
		PhieuDatBan phieuDatBan = null;

		Connection con = ConnectDB.getConnection();
		String query = "select * from PhieuDatBan where maPhieuDatBan = ?";
		try {
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setInt(1, maPhieuDatBanInput);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				int maPhieuDatBan = rs.getInt("maPhieuDatBan");
				Timestamp timestampNgayTaoPhieu = rs.getTimestamp("ngayTaoPhieu");
				LocalDateTime ngayTaoPhieu = timestampNgayTaoPhieu.toLocalDateTime();
				Timestamp timestampThoiGianDatBan = rs.getTimestamp("thoiGianDatBan");
				LocalDateTime thoiGianDatBan = timestampThoiGianDatBan.toLocalDateTime();
				int soLuongKhach = rs.getInt("soLuongKhach");
				float tienCoc = rs.getFloat("tienCoc");
				String trangThai = rs.getString("trangThai");
				KhachHang khachHang = khachHang_DAO.getKhachHangTheoMa(rs.getInt("maKH"));
				NhanVien nhanVien = nhanVien_DAO.getNhanVienTheoMa(rs.getInt("maNV"));
				Ban ban = ban_DAO.getBanById(rs.getInt("maBan"));
				
				phieuDatBan = new PhieuDatBan(maPhieuDatBan, ngayTaoPhieu, thoiGianDatBan,soLuongKhach, tienCoc, trangThai, khachHang, nhanVien, ban);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phieuDatBan;
	}
	//trả về mã phiếu đặt bàn vừa được thêm
	public int themPhieuDatBan(PhieuDatBan phieuDatBan) {
	    Connection con = ConnectDB.getConnection();
	    String query = "insert into PhieuDatBan(ngayTaoPhieu, thoiGianDatBan,soLuongKhach,tienCoc, trangThai, maKH, maNV, maBan) values(?,?,?,?,?,?,?,?)";
	    
	    int maPhieuDatBan = -1; // Giá trị mặc định nếu không lấy được maPhieuDatBan
	    try {
	        // Sử dụng Statement.RETURN_GENERATED_KEYS để lấy khóa chính vừa được tạo
	        PreparedStatement pstm = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

	        // Chuyển đổi dữ liệu và thiết lập các tham số
	        pstm.setTimestamp(1, Timestamp.valueOf(phieuDatBan.getNgayTaoPhieu()));
	        pstm.setTimestamp(2, Timestamp.valueOf(phieuDatBan.getThoiGianDatBan()));
			pstm.setInt(3, phieuDatBan.getSoLuongKhach());
			pstm.setFloat(4, phieuDatBan.getTienCoc());
			pstm.setString(5, phieuDatBan.getTrangThai());
	        pstm.setInt(6, phieuDatBan.getKhachHang().getMaKH());
	        pstm.setInt(7, phieuDatBan.getNhanVien().getMaNV());
	        pstm.setInt(8, phieuDatBan.getBan().getMaBan());

	        // Thực thi câu lệnh SQL
	        int affectedRows = pstm.executeUpdate();

	        // Kiểm tra nếu có dòng nào bị ảnh hưởng
	        if (affectedRows > 0) {
	            // Lấy khóa chính vừa được tạo
	            try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    maPhieuDatBan = generatedKeys.getInt(1); // Lấy giá trị maPhieuDatBan
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return maPhieuDatBan; // Trả về mã phiếu đặt bàn vừa được thêm
	}
}
