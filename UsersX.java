package phase1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class UsersX extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userX;

	/**
	 * Launch the application.
	 */
	public static void UserX() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsersX frame = new UsersX();
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
	public UsersX() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel promptUserX = new JLabel("Enter the username to view their blogs:");
		promptUserX.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		promptUserX.setBounds(103, 16, 344, 41);
		contentPane.add(promptUserX);
		
		userX = new JTextField();
		userX.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		userX.setBounds(103, 73, 326, 48);
		contentPane.add(userX);
		userX.setColumns(10);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbwithusername", "comp440", "pass1234");
					Statement state = con.createStatement();
					
					String sql = "SELECT * FROM blogs where created_by='"+userX.getText()+"'";
					
					ResultSet rs = state.executeQuery(sql);
					
					if(rs.next()) {
						dispose();
						ListBlogsX.ListBlogX(userX.getText());
					}
					else {
						JOptionPane.showMessageDialog(null, "Username not found or did not post blogs");
					}
					con.close();
				} 
				catch(Exception e) {
					System.out.print(e);
				}
			}
		});
		searchButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		searchButton.setBounds(212, 137, 115, 29);
		contentPane.add(searchButton);
	}
}
