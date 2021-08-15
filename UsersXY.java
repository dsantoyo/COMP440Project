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

public class UsersXY extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userX;
	private JTextField userY;

	public static void UserXY() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsersXY frame = new UsersXY();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public UsersXY() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 558, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel promptUserX = new JLabel("Enter user X:");
		promptUserX.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		promptUserX.setBounds(15, 87, 138, 41);
		contentPane.add(promptUserX);
		
		userX = new JTextField();
		userX.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		userX.setBounds(140, 83, 326, 48);
		contentPane.add(userX);
		userX.setColumns(10);
		
		JLabel promptUserY = new JLabel("Enter user Y:");
		promptUserY.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		promptUserY.setBounds(15, 167, 138, 41);
		contentPane.add(promptUserY);
		
		userY = new JTextField();
		userY.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		userY.setColumns(10);
		userY.setBounds(140, 167, 326, 48);
		contentPane.add(userY);
		
		JLabel xyTitle = new JLabel("Enter X and Y to see who they follow");
		xyTitle.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		xyTitle.setBounds(124, 16, 355, 41);
		contentPane.add(xyTitle);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbwithusername", "comp440", "pass1234");
					Statement state = con.createStatement();
					
					String sql = "SELECT leadername FROM (SELECT DISTINCT leadername, followername FROM follows WHERE\r\n" + 
							"followername= '"+userX.getText()+"' OR followername= '"+userY.getText()+"') leaderCount GROUP BY leadername HAVING count(leadername) >=2;";
					
					ResultSet rs = state.executeQuery(sql);
					
					if(rs.next()) {
						ListLeaderNames.ListLNames(userX.getText(), userY.getText());
					}
					else
					{
						JOptionPane.showMessageDialog(null, "User X or Y not found!");
					}

					con.close();
				} 
				catch(Exception e) {
					System.out.print(e);
				}
			}
		});
		searchButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		searchButton.setBounds(210, 251, 115, 29);
		contentPane.add(searchButton);
	}
}
