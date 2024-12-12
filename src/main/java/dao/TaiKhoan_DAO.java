package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;

public class TaiKhoan_DAO {
	public List<TaiKhoan> getAllTaiKhoan() {
		List<TaiKhoan> taiKhoanList = new ArrayList<>();
		String query = "SELECT * FROM TaiKhoan";

		try (Connection con = ConnectDB.getConnection();
			 PreparedStatement pstm = con.prepareStatement(query);
			 ResultSet rs = pstm.executeQuery()) {

			while (rs.next()) {
				String tenDangNhap = rs.getString("tenDangNhap");
				String matKhau = rs.getString("matKhau");
				String loaiTaiKhoan = rs.getString("loaiTaiKhoan");
				int maNV = rs.getInt("maNV");

				// Fetching the associated NhanVien using NhanVien_DAO
				NhanVien nhanVien = new NhanVien_DAO().getNhanVienTheoMa(maNV);

				// Create the TaiKhoan object
				TaiKhoan taiKhoan = new TaiKhoan(tenDangNhap, matKhau, loaiTaiKhoan, nhanVien);

				// Add to the list of TaiKhoan objects
				taiKhoanList.add(taiKhoan);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return taiKhoanList;
	}
	public boolean taoTaiKhoan(TaiKhoan taiKhoan) {
		Connection con = ConnectDB.getConnection();
		String query = "insert into TaiKhoan values(?,?,?,?)";
		int n = 0;
		try {
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setString(1, taiKhoan.getTenDangNhap());
			pstm.setString(2, taiKhoan.getMatKhau());
			pstm.setString(3, taiKhoan.getLoaiTaiKhoan());
			pstm.setInt(4, taiKhoan.getNhanVien().getMaNV());
			n = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean capNhatTaiKhoan(TaiKhoan taiKhoan) {
		Connection con = ConnectDB.getConnection();
		String query = "UPDATE TaiKhoan SET matKhau = ?, loaiTaiKhoan = ?, maNV = ? WHERE tenDangNhap = ?";
		int n = 0;

		try {
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setString(1, taiKhoan.getMatKhau());
			pstm.setString(2, taiKhoan.getLoaiTaiKhoan());
			pstm.setInt(3, taiKhoan.getNhanVien().getMaNV());
			pstm.setString(4, taiKhoan.getTenDangNhap()); // Điều kiện WHERE

			n = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean xoaTaiKhoan(String tenDangNhap) {
		Connection con = ConnectDB.getConnection();
		String query = "DELETE FROM TaiKhoan WHERE tenDangNhap = ?";
		int n = 0;

		try {
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setString(1, tenDangNhap);  // Điều kiện WHERE, sử dụng maNhanVien

			n = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return n > 0;
	}

	public TaiKhoan getTaiKhoanBangTenDangNhap(String tendangnhap) {
		TaiKhoan taiKhoan = null;
		
		Connection con = ConnectDB.getConnection();
		String query = "select * from TaiKhoan where tenDangNhap = ?";
		try {
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setString(1, tendangnhap);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				NhanVien_DAO nhanVien_dao = new NhanVien_DAO();
				String tenDangNhap = rs.getString("tenDangNhap");
				String matKhau = rs.getString("matKhau");
				String loaiTaiKhoan = rs.getString("loaiTaiKhoan");
				NhanVien nhanVien = nhanVien_dao.getNhanVienTheoMa(rs.getInt("maNV"));
				
				taiKhoan = new TaiKhoan(tenDangNhap, matKhau, loaiTaiKhoan, nhanVien);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taiKhoan;
		
	}
	public TaiKhoan dangNhap(String tendangnhap, String matkhau) {
		Connection con = ConnectDB.getConnection();
		String query = "select * from TaiKhoan where tenDangNhap = ? and matKhau = ?";
		try {
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setString(1, tendangnhap);
			pstm.setString(2, matkhau);
			ResultSet rs = pstm.executeQuery();
			
			if (rs.next()) {
				NhanVien_DAO nhanVien_dao = new NhanVien_DAO();
				String tenDangNhap = rs.getString("tenDangNhap");
				String matKhau = rs.getString("matKhau");
				String loaiTaiKhoan = rs.getString("loaiTaiKhoan");
				NhanVien nhanVien = nhanVien_dao.getNhanVienTheoMa(rs.getInt("maNV"));
				
				return new TaiKhoan(tenDangNhap, matKhau, loaiTaiKhoan, nhanVien);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	public int getMaNhanVienByTaiKhoan( String tendangnhap, String matkhau) {
		Connection con = ConnectDB.getConnection();
		String query = "select * from TaiKhoan where tenDangNhap = ? and matKhau = ?";
		int maNV = 0;
		try {
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setString(1, tendangnhap);
			pstm.setString(2, matkhau);
			ResultSet rs = pstm.executeQuery();
			
			if (rs.next()) {
				NhanVien_DAO nhanVien_dao = new NhanVien_DAO();
				String tenDangNhap = rs.getString("tenDangNhap");
				String matKhau = rs.getString("matKhau");
				String loaiTaiKhoan = rs.getString("loaiTaiKhoan");
				maNV = rs.getInt("maNV");
				
				
				return maNV;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maNV;	
	}

	public TaiKhoan getTaiKhoanByMaNhanVien(int maNhanVien) {
		TaiKhoan taiKhoan = null;
		String sql = "SELECT * FROM TaiKhoan WHERE maNV = ?";

		try (Connection conn = ConnectDB.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, maNhanVien);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String tenDangNhap = rs.getString("tenDangNhap");
				String matKhau = rs.getString("matKhau");
				String loaiTaiKhoan = rs.getString( "loaiTaiKhoan");
				taiKhoan = new TaiKhoan(tenDangNhap, matKhau, loaiTaiKhoan, new NhanVien_DAO().getNhanVienTheoMa(maNhanVien));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return taiKhoan;
	}
		
}
