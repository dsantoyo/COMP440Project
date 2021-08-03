package phase1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class rl_interface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField user;
	private JPasswordField pass;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rl_interface frame = new rl_interface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public rl_interface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Project Login Page");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel.setBounds(50, 43, 255, 43);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(15, 116, 83, 33);
		contentPane.add(lblNewLabel_1);
		
		user = new JTextField();
		user.setBounds(94, 120, 189, 26);
		contentPane.add(user);
		user.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password: ");
		lblNewLabel_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(15, 173, 83, 33);
		contentPane.add(lblNewLabel_1_1);
		
		pass = new JPasswordField();
		pass.setBounds(94, 177, 189, 26);
		contentPane.add(pass);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbwithusername", "root", "rock");
					Statement state = con.createStatement();
					
					String password = String.valueOf(pass.getPassword());
					String sql = "SELECT * FROM users where username='"+user.getText()+"' and password='"+password+"'";
					
					ResultSet rs = state.executeQuery(sql);
					
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Logged in");
						dispose();
						HubPage.hub();
					}
					else {
						JOptionPane.showMessageDialog(null, "Incorrect username or password");
					}
					con.close();
				} 
				catch(Exception e) {
					System.out.print(e);
				}
			}
		});
		btnNewButton.setBounds(15, 235, 115, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Register");
		btnNewButton_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register.register();
			}
		});
		btnNewButton_1.setBounds(190, 235, 115, 29);
		contentPane.add(btnNewButton_1);
		
		JButton iniDbButton = new JButton("Initialize Database");
		iniDbButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbwithusername", "root", "rock");
					Statement state = con.createStatement();
					
					String sql = "SET FOREIGN_KEY_CHECKS = 0";
					
					//ResultSet rs = state.executeQuery(sql);
					
					state.executeUpdate(sql);
					
					sql = "DROP TABLE IF EXISTS `users`";
					
					state.executeUpdate(sql);
					
					sql = "SET FOREIGN_KEY_CHECKS = 1";
					
					state.executeUpdate(sql);
					
					sql = "CREATE TABLE `users` (\r\n" + 
							"  `username` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,\r\n" + 
							"  `password` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,\r\n" + 
							"  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,\r\n" + 
							"  PRIMARY KEY (`username`)\r\n" + 
							")";
					
					state.executeUpdate(sql);
					
					sql = "INSERT INTO `users` VALUES ('batman','1234','nananana@batman.com'),('bob','12345','bobthatsme@yahoo.com'),"
							+ "('catlover','abcd','catlover@whiskers.com'),('doglover','efds','doglover@bark.net'),"
							+ "('jdoe','25478','jane@doe.com'),('jsmith','1111','jsmith@gmail.com'),('matty','2222','matty@csun.edu'),"
							+ "('notbob','5555','stopcallingmebob@yahoo.com'),('pacman','9999','pacman@gmail.com'),"
							+ "('scooby','8888','scooby@doo.net')";
					
					state.executeUpdate(sql);
					
					con.close();
				} 
				catch(Exception e) {
					System.out.print(e);
				}
			}
		});
		iniDbButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		iniDbButton.setBounds(94, 290, 176, 29);
		contentPane.add(iniDbButton);
	}
}
