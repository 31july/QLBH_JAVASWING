package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import dao.DAO_Login;
import utils.ConnectionManager;

public class JFrame_Login extends JFrame {
	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField pwdField;
	private Connection conn = null;
	private Statement stmt = null;
	static Properties prop = null;
	private ResultSet rs = null;
	   private static final String PASSWORD_PATTERN =
	            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

	    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		private JPanel panelControl;
		private JLabel lblCreateAnAccount;
		private JFrame_Register frame_Register;
		DAO_Login dao_Login;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					JFrame_Login frame = new JFrame_Login();
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
	public JFrame_Login() {
		dao_Login = new DAO_Login();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 868, 499);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelIndex = new JPanel();
		panelIndex.setBackground(Color.WHITE);
		panelIndex.setBounds(0, 0, 868, 499);
		contentPane.add(panelIndex);
		panelIndex.setLayout(null);
		
		panelControl = new JPanel();
		panelControl.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelControl.setBackground(Color.WHITE);
		panelControl.setBounds(434, 0, 434, 499);
		panelIndex.add(panelControl);
		panelControl.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sign In");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 166, 54);
		panelControl.add(lblNewLabel);
		
		txtEmail = new JTextField("");		
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEmail.setBounds(31, 131, 376, 54);
		panelControl.add(txtEmail);
		txtEmail.setColumns(10);
		
		pwdField = new JPasswordField("");
		pwdField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pwdField.setBounds(31, 224, 376, 54);
		panelControl.add(pwdField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText();
				String password = pwdField.getText();
				boolean check = dao_Login.Login(email, password);
				if(doCheck()) {
					if(check) {
					JOptionPane.showMessageDialog(null, "Login successfully");
					}else {
					JOptionPane.showMessageDialog(null, "Login fail");
					}
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogin.setBounds(275, 307, 132, 45);
		panelControl.add(btnLogin);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(31, 102, 97, 29);
		panelControl.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(31, 195, 97, 29);
		panelControl.add(lblNewLabel_1_1);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Show password");
		chckbxNewCheckBox.setBackground(Color.WHITE);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				   if (chckbxNewCheckBox.isSelected()) {
					      pwdField.setEchoChar((char)0); //password = JPasswordField
					   } else {
					      pwdField.setEchoChar('●');
					   }
			}
		});
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxNewCheckBox.setBounds(31, 284, 132, 29);
		panelControl.add(chckbxNewCheckBox);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		}); 
		panel_2.setBounds(389, 0, 45, 35);
		panelControl.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("X");
		lblNewLabel_2.setBounds(0, 0, 45, 35);
		panel_2.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblGif = new JLabel("");
		lblGif.setIcon(new ImageIcon(JFrame_Login.class.getResource("/images/gif.gif")));
		lblGif.setHorizontalAlignment(SwingConstants.CENTER);
		lblGif.setBounds(0, 0, 434, 499);
		panelIndex.add(lblGif);
		
		JLabel lblForgotPassword_1 = new JLabel("Forgot password ?");
		lblForgotPassword_1.setBounds(160, 403, 123, 29);
		panelControl.add(lblForgotPassword_1);
		lblForgotPassword_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblForgotPassword_1.setForeground(new Color(50, 205, 50));
		lblForgotPassword_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblCreateAnAccount = new JLabel("Create an account?");
		lblCreateAnAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame_Register = new JFrame_Register();
				frame_Register.setVisible(true);
				exitFrame();
				
				
			}
		});
		lblCreateAnAccount.setBounds(137, 442, 179, 29);
		panelControl.add(lblCreateAnAccount);
		lblCreateAnAccount.setBackground(Color.WHITE);
		lblCreateAnAccount.setForeground(new Color(0, 0, 0));
		lblCreateAnAccount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		
	}  
	public void menuClicked(JPanel panel) {
		panel.setVisible(true);
	}

	   public boolean isValidUsername(String email) {
		      String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		      return email.matches(regex);
		   }
	    public  boolean isValidPass(final String password) {
	        Matcher matcher = pattern.matcher(password);
	        return matcher.matches();
	    }
	    public void exitFrame() {
	    	this.dispose();
	    }
	    public boolean doCheck() {
	    	if(txtEmail.getText().trim().equals("Email")) {
				JOptionPane.showMessageDialog(null, "Vui lòng không để trống Email");
				return false;
			}
			
			if(isValidUsername(txtEmail.getText().trim())== false) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng Email");
				return false;
			}
			if(pwdField.getText().trim().equals("Username")) {
				JOptionPane.showMessageDialog(null, "Vui lòng không để trống Password");
				return false;
			}
			if(isValidPass(pwdField.getText().trim())== false) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng Password");
				return false;
			}
			return true;
	    }
	   
	    
}
