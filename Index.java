package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.FlowLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class Index extends JFrame {

	private JPanel contentPane;
	private PanelNhanVien panelNhanVien;
	private PanelKhachHang panelKhachHang;
	private PanelSanPham panelSanPham;
	private PanelHoaDon panelHoaDon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Index frame = new Index();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Index() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1022, 661);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		panelNhanVien = new PanelNhanVien();
		panelKhachHang = new PanelKhachHang(); 
		panelSanPham = new PanelSanPham();
		panelHoaDon = new PanelHoaDon();
		
		
		JPanel PanelMenu = new JPanel();
		PanelMenu.setBackground(new Color(245, 245, 245));
		PanelMenu.setBorder(null);
		PanelMenu.setBounds(0, 0, 251, 624);
		contentPane.add(PanelMenu);
		PanelMenu.setLayout(null);
		
		JPanel paneNhanVien = new JPanel();
		paneNhanVien.addMouseListener(new PanelButtonMouseAdapter(paneNhanVien));
		paneNhanVien.setBackground(new Color(245, 245, 245));
		paneNhanVien.setBounds(3, 207, 250, 50);
		paneNhanVien.addMouseListener(new PanelButtonMouseAdapter(paneNhanVien){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(panelNhanVien);
			}
			
		});
		PanelMenu.add(paneNhanVien);
		paneNhanVien.setLayout(null);
		
		JLabel lblInsert = new JLabel("Nhân viên");
		lblInsert.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblInsert.setBounds(89, 0, 101, 50);
		paneNhanVien.add(lblInsert);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Index.class.getResource("/images/Insert.png")));
		lblNewLabel_1.setBounds(39, 0, 40, 50);
		paneNhanVien.add(lblNewLabel_1);
		
		JPanel paneKhachHang = new JPanel();
		paneKhachHang.addMouseListener(new PanelButtonMouseAdapter(paneKhachHang));
		paneKhachHang.setBackground(new Color(245, 245, 245));
		paneKhachHang.setBounds(3, 259, 250, 50);
		paneKhachHang.addMouseListener(new PanelButtonMouseAdapter(paneKhachHang){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(panelKhachHang);
			}
			
		});
		PanelMenu.add(paneKhachHang);
		paneKhachHang.setLayout(null);
		
		JLabel lblCalendar = new JLabel("Khách hàng");
		lblCalendar.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCalendar.setBounds(84, 0, 118, 50);
		paneKhachHang.add(lblCalendar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(39, 0, 40, 50);
		paneKhachHang.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("E:\\FPT Polytechnic\\hoc_ki_4_block2\\source_code_duan\\ps24904_duan1\\src\\images\\calendar.png"));
		
		JPanel paneSanPham = new JPanel();
		paneSanPham.addMouseListener(new PanelButtonMouseAdapter(paneSanPham){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(panelSanPham);
			}
			
		});
		paneSanPham.addMouseListener(new PanelButtonMouseAdapter(paneSanPham));
		paneSanPham.setBackground(new Color(245, 245, 245));
		paneSanPham.setBounds(3, 311, 250, 50);
		PanelMenu.add(paneSanPham);
		paneSanPham.setLayout(null);
		
		JLabel lblReport = new JLabel("Sản phẩm");
		lblReport.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblReport.setBounds(91, 0, 100, 50);
		paneSanPham.add(lblReport);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Index.class.getResource("/images/report.png")));
		lblNewLabel_2.setBounds(38, 0, 40, 50);
		paneSanPham.add(lblNewLabel_2);
		
		JPanel paneHoaDon = new JPanel();
		paneHoaDon.addMouseListener(new PanelButtonMouseAdapter(paneHoaDon){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(panelHoaDon);
			}
			
		});
		paneHoaDon.addMouseListener(new PanelButtonMouseAdapter(paneHoaDon));
		paneHoaDon.setBackground(new Color(245, 245, 245));
		paneHoaDon.setBounds(3, 363, 250, 50);
		PanelMenu.add(paneHoaDon);
		paneHoaDon.setLayout(null);
		
		JLabel lblSearch = new JLabel("Hóa đơn");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSearch.setBounds(93, 0, 87, 50);
		paneHoaDon.add(lblSearch);
		
		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setIcon(new ImageIcon(Index.class.getResource("/images/search.png")));
		lblNewLabel_2_1.setBounds(41, 0, 40, 50);
		paneHoaDon.add(lblNewLabel_2_1);
		
		JPanel paneThoat = new JPanel();
		paneThoat.addMouseListener(new PanelButtonMouseAdapter(paneThoat){
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			
		});
		paneThoat.addMouseListener(new PanelButtonMouseAdapter(paneThoat));
		paneThoat.setBackground(new Color(245, 245, 245));
		paneThoat.setBounds(3, 415, 250, 50);
		PanelMenu.add(paneThoat);
		paneThoat.setLayout(null);
		
		JLabel lblInformation = new JLabel("Thoát");
		lblInformation.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblInformation.setBounds(105, 0, 72, 50);
		paneThoat.add(lblInformation);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("");
		lblNewLabel_2_1_1.setIcon(new ImageIcon(Index.class.getResource("/images/information.png")));
		lblNewLabel_2_1_1.setBounds(41, 0, 40, 50);
		paneThoat.add(lblNewLabel_2_1_1);
		
		JLabel lblIconLogo = new JLabel("");
		lblIconLogo.setBounds(0, 0, 250, 197);
		PanelMenu.add(lblIconLogo);
		lblIconLogo.setIcon(new ImageIcon(Index.class.getResource("/images/QT_free-file (1).png")));
		
		JPanel panelMainContend = new JPanel();
		panelMainContend.setBackground(Color.WHITE);
		panelMainContend.setBounds(251, 0, 757, 624);
		contentPane.add(panelMainContend);
		panelMainContend.setLayout(null);
		
		panelMainContend.add(panelNhanVien);
		panelMainContend.add(panelKhachHang);
		panelMainContend.add(panelSanPham);
		panelMainContend.add(panelHoaDon);
		
		menuClicked(paneNhanVien);
		this.setLocationRelativeTo(null);
		
	}
	public void menuClicked(JPanel panel) {
		panelNhanVien.setVisible(false);
		panelKhachHang.setVisible(false);
		panelSanPham.setVisible(false);
		panelHoaDon.setVisible(false);
		panel.setVisible(true);
	}
	private class PanelButtonMouseAdapter extends MouseAdapter {
		JPanel panel;
		public PanelButtonMouseAdapter (JPanel panel) {
			this.panel = panel;
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(new Color(200, 200, 200));
		}
		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground(new Color(245, 245, 245));        
		}
		@Override
		public void mousePressed(MouseEvent e) {
			panel.setBackground(new Color(200, 200, 200));
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			panel.setBackground(new Color(240, 248, 255));
		}
	}
}

