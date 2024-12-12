package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVien;

public class NhanVien_DAO {
	public ArrayList<NhanVien> getAllNhanVien(){
		ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
		Connection con = ConnectDB.getConnection();
		String query = "select * from NhanVien";
		
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			
			while (rs.next()) {
				int maNV = rs.getInt("maNV");
				String tenNV = rs.getString("tenNV");
				String gioiTinh = rs.getString("gioiTinh");
				Date ngaySinh = rs.getDate("ngaySinh");
				String soDT = rs.getString("soDT");
				String email = rs.getString("email");
				String diaChi = rs.getString("diaChi");
				
				NhanVien nv = new NhanVien(maNV, tenNV, gioiTinh, ngaySinh, soDT, email, diaChi);
				dsNhanVien.add(nv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsNhanVien;
	}
	public NhanVien getNhanVienTheoMa(int maNhanVien) {
		NhanVien nv = null;
		
		Connection con = ConnectDB.getConnection();
		String query = "select * from NhanVien where maNV = ?";
		try {
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setInt(1, maNhanVien);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				int maNV = rs.getInt("maNV");
				String tenNV = rs.getString("tenNV");
				String gioiTinh = rs.getString("gioiTinh");
				Date ngaySinh = rs.getDate("ngaySinh");
				String soDT = rs.getString("soDT");
				String email = rs.getString("email");
				String diaChi = rs.getString("diaChi");
				
				nv = new NhanVien(maNV, tenNV, gioiTinh, ngaySinh, soDT, email, diaChi);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nv;
		
	}
	public boolean addNhanVien(NhanVien nv) {
		boolean isSuccess = false;
		Connection con = ConnectDB.getConnection();

		String query = "INSERT INTO NhanVien (tenNV, gioiTinh, ngaySinh, soDT, email, diaChi) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			// Sử dụng PreparedStatement để tránh SQL injection
			PreparedStatement pstm = con.prepareStatement(query);

			// Gán giá trị cho các tham số trong câu lệnh SQL
			pstm.setString(1, nv.getTenNV());
			pstm.setString(2, nv.getGioiTinh());
			pstm.setDate(3, new Date(nv.getNgaySinh().getTime()));  // Chuyển java.util.Date thành java.sql.Date
			pstm.setString(4, nv.getSoDT());
			pstm.setString(5, nv.getEmail());
			pstm.setString(6, nv.getDiaChi());

			// Thực thi câu lệnh SQL
			int rowsAffected = pstm.executeUpdate();

			if (rowsAffected > 0) {
				isSuccess = true;  // Nếu thêm thành công, trả về true
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isSuccess;  // Trả về kết quả thêm nhân viên thành công hay không
	}

	public boolean capNhatNhanVien(NhanVien nhanVien, int maNhanVien) {
		Connection con = ConnectDB.getConnection();
		String query = "UPDATE NhanVien SET tenNV = ?, gioiTinh = ?, ngaySinh = ?, soDT = ?, email = ?, diaChi = ? WHERE maNV = ?";
		int n = 0;

		try {
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setString(1, nhanVien.getTenNV());
			pstm.setString(2, nhanVien.getGioiTinh());
			Date sqlDate = new Date(nhanVien.getNgaySinh().getTime());
			pstm.setDate(3, sqlDate);
			pstm.setString(4, nhanVien.getSoDT());
			pstm.setString(5, nhanVien.getEmail());
			pstm.setString(6, nhanVien.getDiaChi());
			pstm.setInt(7, maNhanVien); // Điều kiện WHERE, sử dụng maNhanVien

			n = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean xoaNhanVien(int maNhanVien) {
		Connection con = ConnectDB.getConnection();
		String query = "DELETE FROM NhanVien WHERE maNV = ?";
		int n = 0;

		try {
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setInt(1, maNhanVien);  // Điều kiện WHERE, sử dụng maNhanVien

			n = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return n > 0;
	}

}
