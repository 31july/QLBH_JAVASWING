package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import utils.ConnectionManager;

public class DAO_NhanVien {
	Connection conn;
	
	public void insert(String manv, String hoten, String sdt, String ngayvl) {
		conn = ConnectionManager.getConnection();
		String sql = "insert into nhanvien values(?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, manv);
			ps.setString(2, hoten);
			ps.setString(3, sdt);
			ps.setString(4, ngayvl);
			ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Thêm mới thành công");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(String manv) {
		conn = ConnectionManager.getConnection();
		String sql = "delete from nhanvien where manv = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, manv);
			if(ps.executeUpdate()>0) {
				JOptionPane.showMessageDialog(null, "Xóa thành công");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void update(String hoten, int sdt, String ngayvl, String manv) {
		conn = ConnectionManager.getConnection();
		String sql = "update NHANVIEN set hoten=?, sodt=?, ngvl=? where manv = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, hoten);
			ps.setString(2, String.valueOf(sdt));
			ps.setString(3, ngayvl);
			ps.setString(4, manv);
			if(ps.executeUpdate()>0) {
				JOptionPane.showMessageDialog(null, "Sửa thành công");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
