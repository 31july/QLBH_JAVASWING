package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import utils.ConnectionManager;

public class DAO_Register {
	Connection conn;
	
	public void insert(String email, String password) {
		conn = ConnectionManager.getConnection();
		String sql = "insert into ACCOUNT values(?, ?)";
		try {
			PreparedStatement prst = conn.prepareStatement(sql);
			prst.setString(1, email);
			prst.setString(2, password);
			if(prst.executeUpdate()>0) {
				 JOptionPane.showMessageDialog(null, "New User Add");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}
	
}
