package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import utils.ConnectionManager;

public class DAO_KhachHang {
	Connection conn;
	public void insert(String MaKH, String HoTen, String DiaChi, String SDT, String NgayDK, int DoanhThu) {
		conn = ConnectionManager.getConnection();
		String sql = "insert into khachhang values(?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, MaKH);
			ps.setString(2, HoTen);
			ps.setString(3, DiaChi);
			ps.setString(4, SDT);
			ps.setString(5, NgayDK);
			ps.setInt(6, DoanhThu);
			if(ps.executeUpdate()>0) {
				JOptionPane.showMessageDialog(null, "Thêm mới khác hàng thành công");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void delete(String MaKH) {
		conn = ConnectionManager.getConnection();
		String sql = "delete from khachhang where makh = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, MaKH);
			if(ps.executeUpdate()>0) {
				JOptionPane.showMessageDialog(null, "Xóa thành công");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void update(String hoten, String DiaChi, String sdt, String ngaysinh, int DoanhSo, String MaKH) {
		conn = ConnectionManager.getConnection();
		String sql = "update KHACHHANG set HOTEN=?, DCHI=?, SODT=?, NGVL = ?, DOANHSO=? where MAKH = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, hoten);
			ps.setString(2, DiaChi);
			ps.setString(3, sdt);
			ps.setString(4, ngaysinh);
			ps.setInt(5, DoanhSo);
			ps.setString(6, MaKH);
			if(ps.executeUpdate()>0) {
				JOptionPane.showMessageDialog(null, "Sửa thành công");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
