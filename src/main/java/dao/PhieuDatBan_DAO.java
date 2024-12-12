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
	public ArrayList<PhieuDatBan> getAllPhieuDatBanByMaKH(int maKH) {
		ArrayList<PhieuDatBan> dsPhieuDatBan = new ArrayList<>();
		String query = "SELECT * FROM PhieuDatBan WHERE maKH = ?";

		// Kết nối và PreparedStatement trong try-with-resources để tự động đóng
		try (Connection con = ConnectDB.getConnection();
			 PreparedStatement stm = con.prepareStatement(query)) {

			// Set tham số maKH vào câu truy vấn
			stm.setInt(1, maKH);

			// Thực thi truy vấn
			try (ResultSet rs = stm.executeQuery()) {
				while (rs.next()) {
					int maPhieuDatBan = rs.getInt("maPhieuDatBan");

					// Lấy giá trị Timestamp từ cơ sở dữ liệu cho cả ngày và giờ
					Timestamp timestampNgayTaoPhieu = rs.getTimestamp("ngayTaoPhieu");
					LocalDateTime ngayTaoPhieu = timestampNgayTaoPhieu != null ? timestampNgayTaoPhieu.toLocalDateTime() : null;

					Timestamp timestampThoiGianDatBan = rs.getTimestamp("thoiGianDatBan");
					LocalDateTime thoiGianDatBan = timestampThoiGianDatBan != null ? timestampThoiGianDatBan.toLocalDateTime() : null;

					int soLuongKhach = rs.getInt("soLuongKhach");
					float tienCoc = rs.getFloat("tienCoc");
					String trangThai = rs.getString("trangThai");

					// Lấy các đối tượng KhachHang, NhanVien và Ban
					KhachHang khachHang = khachHang_DAO.getKhachHangTheoMa(rs.getInt("maKH"));
					NhanVien nhanVien = nhanVien_DAO.getNhanVienTheoMa(rs.getInt("maNV"));
					Ban ban = ban_DAO.getBanById(rs.getInt("maBan"));

					// Tạo đối tượng PhieuDatBan
					PhieuDatBan phieuDatBan = new PhieuDatBan(maPhieuDatBan, ngayTaoPhieu, thoiGianDatBan, soLuongKhach, tienCoc, trangThai, khachHang, nhanVien, ban);

					// Thêm vào danh sách
					dsPhieuDatBan.add(phieuDatBan);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace(); // In lỗi nếu có
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
	public void capNhatTrangThaiByMaPhieu(int maPhieuDatBan, String trangThaiMoi) {
		// Kết nối cơ sở dữ liệu
		Connection con = ConnectDB.getConnection();
		// Câu lệnh SQL cập nhật trạng thái theo mã phiếu đặt bàn
		String query = "UPDATE PhieuDatBan SET trangThai = ? WHERE maPhieuDatBan = ?";

		try (PreparedStatement pstm = con.prepareStatement(query)) {
			// Thiết lập tham số cho câu lệnh SQL
			pstm.setString(1, trangThaiMoi); // Trạng thái mới
			pstm.setInt(2, maPhieuDatBan);  // Mã phiếu đặt bàn

			// Thực thi câu lệnh cập nhật
			int rowsAffected = pstm.executeUpdate();

			// Kiểm tra xem có dòng nào bị ảnh hưởng không
			if (rowsAffected > 0) {
				System.out.println("Cập nhật trạng thái thành công cho phiếu đặt bàn có mã: " + maPhieuDatBan);
			} else {
				System.out.println("Không tìm thấy phiếu đặt bàn với mã: " + maPhieuDatBan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Lỗi khi cập nhật trạng thái phiếu đặt bàn.");
		}
	}


	public PhieuDatBan getPhieuDatBanTheoMaBan(int maBan) {
		PhieuDatBan phieuDatBan = null;

		Connection con = ConnectDB.getConnection();
		String query = "SELECT * FROM PhieuDatBan WHERE maBan = ? AND trangThai = N'Đang sử dụng'";  // Sửa dấu " thành '

		try {
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setInt(1, maBan);
			ResultSet rs = pstm.executeQuery();

			// Kiểm tra kết quả trả về
			if (rs.next()) {
				int maPhieuDatBan = rs.getInt("maPhieuDatBan");
				Timestamp timestampNgayTaoPhieu = rs.getTimestamp("ngayTaoPhieu");
				LocalDateTime ngayTaoPhieu = timestampNgayTaoPhieu.toLocalDateTime();
				Timestamp timestampThoiGianDatBan = rs.getTimestamp("thoiGianDatBan");
				LocalDateTime thoiGianDatBan = timestampThoiGianDatBan.toLocalDateTime();
				int soLuongKhach = rs.getInt("soLuongKhach");
				float tienCoc = rs.getFloat("tienCoc");
				String trangThai = rs.getString("trangThai");

				// Giả sử bạn đã có các đối tượng DAO cho KhachHang, NhanVien và Ban
				KhachHang khachHang = khachHang_DAO.getKhachHangTheoMa(rs.getInt("maKH"));
				NhanVien nhanVien = nhanVien_DAO.getNhanVienTheoMa(rs.getInt("maNV"));
				Ban ban = ban_DAO.getBanById(rs.getInt("maBan"));

				// Tạo đối tượng PhieuDatBan với các giá trị lấy được
				phieuDatBan = new PhieuDatBan(maPhieuDatBan, ngayTaoPhieu, thoiGianDatBan, soLuongKhach, tienCoc, trangThai, khachHang, nhanVien, ban);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return phieuDatBan;
	}

}
