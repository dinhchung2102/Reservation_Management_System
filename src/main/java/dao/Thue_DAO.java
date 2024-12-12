package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entity.Thue;

public class Thue_DAO {
    public Thue getThueTheoMa(int mathue) {
        Thue thue = null;

        Connection con = ConnectDB.getConnection();
        String query = "select * from Thue where maThue = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1, mathue);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int maThue = rs.getInt("maThue");
                String tenThue = rs.getString("tenThue");
                Double giaTriThue = rs.getDouble("giaTriThue");

                thue = new Thue(maThue, tenThue, giaTriThue);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return thue;

    }
}
