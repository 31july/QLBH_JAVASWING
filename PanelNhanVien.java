package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.DAO_NhanVien;
import entity.NhanVien;
import utils.ConnectionManager;

import java.awt.Color;

public class PanelNhanVien extends JPanel {
	private JTextField txtIdNhanVien;
	private JTextField txtHoTen;
	private JTextField txtSDT;
	private JTextField txtNgayVL;
	private JTable table;
	DAO_NhanVien dao_NhanVien;
	private Connection conn;
	private List<NhanVien> list = new ArrayList<NhanVien>();
	String head[] = {"Mã Nhân Viên", "Họ Và Tên", "Số Điện Thoại", "Ngày Vào Làm"};

	DefaultTableModel defaultTableModel = new DefaultTableModel(head, 0);
	int current = 0;

	/**
	 * Create the panel.
	 */
	public PanelNhanVien() {
		setBackground(Color.WHITE);
		dao_NhanVien = new DAO_NhanVien();
		
		
		
		
		setBounds(0, 0, 757, 624);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nhân Viên");
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(261, 10, 219, 57);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mã Nhân Viên:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(36, 100, 126, 38);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Họ Và Tên:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(36, 160, 126, 38);
		add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("SĐT:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(36, 220, 126, 38);
		add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Ngày Vào Làm:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_3.setBounds(36, 280, 126, 38);
		add(lblNewLabel_1_3);
		
		txtIdNhanVien = new JTextField();
		txtIdNhanVien.setBounds(205, 100, 515, 38);
		add(txtIdNhanVien);
		txtIdNhanVien.setColumns(10);
		
		txtHoTen = new JTextField();
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(205, 160, 515, 38);
		add(txtHoTen);
		
		txtSDT = new JTextField();
		txtSDT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		
		txtSDT.setColumns(10);
		txtSDT.setBounds(205, 220, 515, 38);
		add(txtSDT);
		
		txtNgayVL = new JTextField();
		txtNgayVL.setColumns(10);
		txtNgayVL.setBounds(205, 280, 515, 38);
		add(txtNgayVL);
		
		table = new JTable();
		table.addMouseListener((MouseListener) new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectRow = table.getSelectedRow();
				if (selectRow < 0) {
					return;
				}
				txtIdNhanVien.setText(table.getValueAt(selectRow, 0).toString());
				txtHoTen.setText(table.getValueAt(selectRow, 1).toString());
				txtSDT.setText(table.getValueAt(selectRow, 2).toString());
				txtNgayVL.setText(table.getValueAt(selectRow, 3).toString());
				

			}
		});
		JScrollPane jScrollPane = new JScrollPane(table);
		jScrollPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jScrollPane.setBounds(36, 348, 684, 180);
		add(jScrollPane);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String manv = txtIdNhanVien.getText();
				String hoten = txtHoTen.getText();
				String sdt = txtSDT.getText();
				String ngayvl = txtNgayVL.getText();
				if(doCheck()) {
					dao_NhanVien.insert(manv, hoten, sdt, ngayvl);
				}
				loadData();
				LoadDatatoJTable();
			}
		});
		btnThem.setBounds(252, 554, 128, 36);
		add(btnThem);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String manv = txtIdNhanVien.getText();
				String hoten = txtHoTen.getText();
				int sdt = Integer.valueOf(txtSDT.getText());
				String ngayvl = txtNgayVL.getText();
				dao_NhanVien.update(hoten, sdt, ngayvl, manv);
				loadData();
				LoadDatatoJTable();
			}
		});
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSua.setBounds(422, 554, 128, 36);
		add(btnSua);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Chưa chọn dòng nào trong bảng!");
					return;
				}
				String manv = txtIdNhanVien.getText();
				dao_NhanVien.delete(manv);
				defaultTableModel.removeRow(row);
				loadData();
				doNew();
				
				
			}
		});
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXoa.setBounds(592, 554, 128, 36);
		add(btnXoa);
		
		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtIdNhanVien.setText("");
				txtHoTen.setText("");
				txtSDT.setText("");
				txtNgayVL.setText("");
			}
		});
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLamMoi.setBounds(82, 554, 128, 36);
		add(btnLamMoi);
		
		
		loadData();
		DisPlay(current);
		LoadDatatoJTable();
	}
	public boolean doCheck() {
		if(txtIdNhanVien.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng không để trống Mã nhân viên!");
			txtIdNhanVien.isFocusable();
			return false;
		}
		if(txtHoTen.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng không để trống Họ tên!");
			txtHoTen.isFocusable();
			return false;
		}
		if(txtSDT.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng không để trống Số điện thoại!");
			txtSDT.isFocusable();
			return false;
		}
		if(isValidsdt(txtSDT.getText()) == false) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng của số điện thoại");
			return false;
		}
		if(txtNgayVL.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng không để trống Ngày vào làm!");
			txtNgayVL.isFocusable();
			return false;
		}
		if(isValidDate(txtNgayVL.getText()) == false) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng của ngày yyyy-mm-dd hoặc yyyy-m-d !");
			return false;
		}
		return true;
	}
	public boolean isValidsdt(String sdt) {
	      String regex = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
	      return sdt.matches(regex);
	   }
	public boolean isValidDate(String date) {
	      String regex = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
	      return date.matches(regex);
	   }
	
	public void loadData() {
		conn = ConnectionManager.getConnection();
		try {
			String sql = "select * from nhanvien";
			PreparedStatement prst = conn.prepareStatement(sql);
			ResultSet rs = prst.executeQuery();
			list.clear();
			while (rs.next()) {
				String Manv = rs.getString(1);
				String HoTen = rs.getString(2);
				String Sdt = rs.getString(3);
				String NgayVL = rs.getString(4);
				NhanVien nv = new NhanVien(Manv, HoTen, Sdt, NgayVL);
				list.add(nv);
			}
			
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void DisPlay(int i) {
		NhanVien nv = list.get(i);
		txtIdNhanVien.setText(nv.getMaNhanVien());
		txtHoTen.setText(nv.getHoTen());
		txtSDT.setText(nv.getSDT());
		txtNgayVL.setText(nv.getNgayVl());
	
	}

	public void LoadDatatoJTable() {
		conn = ConnectionManager.getConnection();
		try {
			defaultTableModel.setRowCount(0);
			
			String sql = "select * from nhanvien";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Vector vectordata = new Vector();
				vectordata.add(rs.getString(1));
				vectordata.add(rs.getString(2));
				vectordata.add(rs.getString(3));
				vectordata.add(rs.getString(4));
				
				defaultTableModel.addRow(vectordata);
			}
			table.setModel(defaultTableModel);
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Fail");
		}
	}
	public void doNew() {
		txtIdNhanVien.setText("");
		txtHoTen.setText("");
		txtSDT.setText("");
		txtNgayVL.setText("");
	}
}
