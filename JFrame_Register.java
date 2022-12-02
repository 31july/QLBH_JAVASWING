package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DAO_Register;
import entity.Register;
import utils.ConnectionManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JFrame_Register extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;
	private JLabel lblNewLabel_2;
	private JPasswordField txtPassword;
	private JPasswordField txtPasswordConfirm;
	private Connection conn = null;
	private JPanel panel;
	private JLabel lblNewLabel_3;
	   private static final String PASSWORD_PATTERN =
	            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
	   private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
	   DAO_Register dao_Register;
	   JFrame_Login frame_Login;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					JFrame_Register frame = new JFrame_Register();
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
	public JFrame_Register() {
		dao_Register = new DAO_Register();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 495, 537);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Create Account");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(101, 28, 281, 50);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 77, 102, 34);
		contentPane.add(lblNewLabel_1);
		
		txtEmail = new JTextField("");
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEmail.setBounds(10, 121, 475, 50);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(10, 181, 102, 34);
		contentPane.add(lblNewLabel_2);
		
		txtPassword = new JPasswordField("");
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPassword.setBounds(10, 225, 475, 50);
		contentPane.add(txtPassword);
		
		JLabel lblNewLabel_2_1 = new JLabel("Password Confirmation");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(10, 285, 184, 34);
		contentPane.add(lblNewLabel_2_1);
		
		txtPasswordConfirm = new JPasswordField("");
		txtPasswordConfirm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPasswordConfirm.setBounds(10, 329, 475, 50);
		contentPane.add(txtPasswordConfirm);
		
		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText();
				String password = txtPassword.getText();
				if(doCheck()) {
					dao_Register.insert(email, password);
					JFrame_Login frame_Login = new JFrame_Login();
					frame_Login.setVisible(true);
				}
				exitFrame();
				
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSignUp.setBounds(324, 442, 147, 44);
		contentPane.add(btnSignUp);
		
		JCheckBox chckPassword = new JCheckBox("Show password");
		chckPassword.setToolTipText("");
		chckPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				   if (chckPassword.isSelected()) {
					      txtPassword.setEchoChar((char)0); //password = JPasswordField
					      txtPasswordConfirm.setEchoChar((char)0); 
					   } else {
						   txtPassword.setEchoChar('●');
						   txtPasswordConfirm.setEchoChar('●');
					   }
			}
		});
		chckPassword.setBackground(Color.WHITE);
		chckPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckPassword.setBounds(10, 396, 132, 29);
		contentPane.add(chckPassword);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(450, 0, 45, 35);
		contentPane.add(panel);
		
		lblNewLabel_3 = new JLabel("X");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBounds(0, 0, 45, 35);
		panel.add(lblNewLabel_3);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);

	}

	   public boolean isValidUsername(String email) {
		      String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		      return email.matches(regex);
		   }
	    public  boolean isValidPass(final String password) {
	        Matcher matcher = pattern.matcher(password);
	        return matcher.matches();
	    }
	    public boolean doCheck() {
	    	if(txtEmail.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng không để trống Email");
				return false;
			}
			
			if(isValidUsername(txtEmail.getText().trim())== false) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng Email");
				return false;
			}
			if(txtPassword.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng không để trống Password");
				return false;
			}
			
			if(isValidPass(txtPassword.getText().trim())== false) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng Password");
				return false;
			}
			if(txtPasswordConfirm.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng không để trống PasswordConfirmation");
				return false;
			}
			if(isValidPass(txtPasswordConfirm.getText().trim())== false) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng Password");
				return false;
			}
			if(txtPassword.getText().equals(txtPasswordConfirm.getText()) == false) {
				JOptionPane.showMessageDialog(null, "Mật khẩu không trùng khớp");
				return false;
			}
			return true;
	    }
	    public void exitFrame() {
	    	this.dispose();
	    }
	

}
	    
