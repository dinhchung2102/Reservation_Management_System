package dao;

import connectDB.ConnectDB;
import entity.Ban;
import entity.KhuVuc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAO_Ban {
    public List<Ban> getBansByKhuVuc(String khuVuc) {
        List<Ban> bans = new ArrayList<Ban>();
        
        String sql = "SELECT b.maBan, b.loaiBan, b.soGheNgoi, b.moTa, b.trangThai, b.maKhuVuc " +
                "FROM Ban b " +
                "INNER JOIN KhuVuc k ON b.maKhuVuc = k.maKhuVuc " +
                "WHERE k.tenKhuVuc = ?";

        new ConnectDB();
		try (Connection connection = ConnectDB.getConnection(); 
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setString(1, khuVuc); 
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
            	while (resultSet.next()) {
            	    int maBan = resultSet.getInt("maBan");
            	    String loaiBan = resultSet.getString("loaiBan");
            	    int soGheNgoi = resultSet.getInt("soGheNgoi"); 
            	    String moTa = resultSet.getString("moTa"); 
            	    boolean trangThai = resultSet.getBoolean("trangThai"); 
            	    int maKhuVuc = resultSet.getInt("maKhuVuc");

            	    Ban ban = new Ban(maBan, loaiBan, soGheNgoi, moTa, trangThai, new DAO_KhuVuc().getKhuVucById(maKhuVuc));
            	    bans.add(ban);
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bans;
    }
    public int getSoGheByMaBan(Integer maBan) {
    	String sql= "SELECT soGheNgoi from Ban WHERE maBan = ?";
    	int soGheNgoi = -1;
    	
    	new ConnectDB();
		try (Connection connection = ConnectDB.getConnection();
    			PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
    		preparedStatement.setInt(1, maBan);
    		try (ResultSet resultSet = preparedStatement.executeQuery()) {
    			while (resultSet.next()) {
    				soGheNgoi = resultSet.getInt("soGheNgoi");
				}
    			
			} 
    		
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return soGheNgoi;
	}
    public boolean capNhatTrangThaiBanById(int maBan, boolean trangThai) {
        String sql = "UPDATE Ban SET trangThai = ? WHERE maBan = ?";
        boolean isUpdated = false;

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setBoolean(1, trangThai);
            preparedStatement.setInt(2, maBan);
            
            int rowsAffected = preparedStatement.executeUpdate();
            isUpdated = (rowsAffected > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isUpdated; 
    }
    public Ban getBanById(int maBan) {
        Ban ban = null; 
        String sql = "SELECT * FROM Ban WHERE maBan = ?";

        try (Connection connection = ConnectDB.getConnection(); 
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setInt(1, maBan);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) { 
                    String loaiBan = resultSet.getString("loaiBan");
                    int soGheNgoi = resultSet.getInt("soGheNgoi"); 
                    String moTa = resultSet.getString("moTa"); 
                    boolean trangThai = resultSet.getBoolean("trangThai"); 
                    int maKhuVuc = resultSet.getInt("maKhuVuc");

                    ban = new Ban(maBan, loaiBan, soGheNgoi, moTa, trangThai, new DAO_KhuVuc().getKhuVucById(maKhuVuc)); 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ban;
    }
    public KhuVuc getKhuVucByMaBan(int maBan) {
        KhuVuc khuVuc = null;
        String sql = "SELECT K.tenKhuVuc, K.maKhuVuc FROM KhuVuc K JOIN Ban B ON K.maKhuVuc = B.maKhuVuc WHERE B.maBan = ?";

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Set parameter for the query
            preparedStatement.setInt(1, maBan);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    String tenKhuVuc = resultSet.getString("tenKhuVuc");
                    int maKhuVuc = resultSet.getInt("maKhuVuc");


                    khuVuc = new KhuVuc(maKhuVuc, tenKhuVuc, 0, "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return khuVuc;
    }
    public boolean themBan(Ban ban) {
        String sql = "INSERT INTO Ban (loaiBan, soGheNgoi, moTa, trangThai, maKhuVuc) VALUES (?, ?, ?, ?, ?)";
        boolean isInserted = false;

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, ban.getLoaiBan());
            preparedStatement.setInt(2, ban.getSoGheNgoi());
            preparedStatement.setString(3, ban.getMoTa());
            preparedStatement.setBoolean(4, ban.isTrangThai());
            preparedStatement.setInt(5, ban.getKhuVuc().getMaKhuVuc());

            int rowsAffected = preparedStatement.executeUpdate();
            isInserted = (rowsAffected > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isInserted;
    }
    public boolean xoaBan(int maBan) {
        String sql = "DELETE FROM Ban WHERE maBan = ?";
        boolean isDeleted = false;

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, maBan);
            int rowsAffected = preparedStatement.executeUpdate();
            isDeleted = (rowsAffected > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isDeleted;
    }

    public boolean capNhatBan(Ban ban) {
        String sql = "UPDATE Ban SET loaiBan = ?, soGheNgoi = ?, moTa = ?, trangThai = ?, maKhuVuc = ? WHERE maBan = ?";
        boolean isUpdated = false;

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, ban.getLoaiBan());
            preparedStatement.setInt(2, ban.getSoGheNgoi());
            preparedStatement.setString(3, ban.getMoTa());
            preparedStatement.setBoolean(4, ban.isTrangThai());
            preparedStatement.setInt(5, ban.getKhuVuc().getMaKhuVuc());
            preparedStatement.setInt(6, ban.getMaBan());

            int rowsAffected = preparedStatement.executeUpdate();
            isUpdated = (rowsAffected > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isUpdated;
    }



}
