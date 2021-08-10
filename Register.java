package phase1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Register extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField uname;
	private JPasswordField pass;
	private JPasswordField passConfirm;
	private JTextField email;


	public static void register() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Register() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 521, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New User");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(158, 16, 171, 41);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username: ");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(15, 81, 91, 35);
		contentPane.add(lblNewLabel_1);
		
		uname = new JTextField();
		uname.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		uname.setBounds(95, 86, 223, 26);
		contentPane.add(uname);
		uname.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password: ");
		lblNewLabel_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(15, 129, 91, 35);
		contentPane.add(lblNewLabel_1_1);
		
		pass = new JPasswordField();
		pass.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		pass.setBounds(95, 134, 223, 26);
		contentPane.add(pass);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Confirm Password: ");
		lblNewLabel_1_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(15, 176, 150, 35);
		contentPane.add(lblNewLabel_1_1_1);
		
		passConfirm = new JPasswordField();
		passConfirm.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		passConfirm.setBounds(158, 181, 210, 26);
		contentPane.add(passConfirm);
		
		JLabel lblNewLabel_1_4 = new JLabel("Email: ");
		lblNewLabel_1_4.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblNewLabel_1_4.setBounds(15, 227, 91, 35);
		contentPane.add(lblNewLabel_1_4);
		
		email = new JTextField();
		email.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		email.setBounds(72, 231, 296, 26);
		contentPane.add(email);
		email.setColumns(10);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbwithusername", "comp440", "pass1234");
					Statement state = con.createStatement();
					
					boolean valid = true;
					
					String checkUsername = "SELECT * FROM users where username='"+uname.getText()+"'";
					String checkEmail = "SELECT * FROM users where email='"+email.getText()+"'";
					String username = uname.getText();
					String password = String.valueOf(pass.getPassword());
					String passwordC = String.valueOf(passConfirm.getPassword());
					String Email = email.getText();
					String insertBackup = "INSERT INTO backup (username, password, email) " + "VALUES ('"+uname.getText()+"', '"+password+"', '"+email.getText()+"');";
					String insert = "INSERT INTO users (username, password, email) " + "VALUES ('"+uname.getText()+"', '"+password+"', '"+email.getText()+"');";
					ResultSet rs = state.executeQuery(checkUsername);
					
					if(username.length() == 0) {
						JOptionPane.showMessageDialog(null, "Please enter a username");
						valid = false;
					}
					
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "This username is already taken");
						valid = false;
					}
					
					if(password.length() == 0) {
						JOptionPane.showMessageDialog(null, "Please enter a password");
						valid = false;
					}
					
					if(!(password.equals(passwordC))) {
						JOptionPane.showMessageDialog(null, "Passwords do not match. Please retry");
						valid = false;
					}
					
					if(Email.length() == 0) {
						JOptionPane.showMessageDialog(null, "Please enter an email");
						valid = false;
					}
					
					rs = state.executeQuery(checkEmail);
					
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "This email is already in use");
						valid = false;
					}
					
					if(valid == true) {
						state.executeUpdate(insert);
						state.executeUpdate(insertBackup);						
						JOptionPane.showMessageDialog(null, "Account created!");
						dispose();
					}
					
					con.close();
				} 
				catch(Exception e) {
					System.out.print(e);
				}
			}
		});
		btnNewButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnNewButton.setBounds(179, 302, 138, 38);
		contentPane.add(btnNewButton);
	}

}
