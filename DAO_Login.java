package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.ConnectionManager;

public class DAO_Login {

	Connection conn;
	public boolean Login(String username, String password) {
		conn = ConnectionManager.getConnection();
		String sql = "select * from account where email= ? and password = ?";
		try {
			PreparedStatement prst = conn.prepareStatement(sql);
			prst.setString(1, username);
			prst.setString(2, password);
			ResultSet rs = prst.executeQuery();
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
