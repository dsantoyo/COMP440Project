package phase1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.JTextArea;

public class ListLeaderNames extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public static void ListLNames(String userX, String userY) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListLeaderNames frame = new ListLeaderNames(userX, userY);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ListLeaderNames(String userX, String userY) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 461, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel prompt = new JLabel("Those followed by " + userX + " and " + userY + ":");
		prompt.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		prompt.setBounds(15, 16, 409, 20);
		contentPane.add(prompt);
		
		JTextArea userList = new JTextArea();
		userList.setEditable(false);
		userList.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbwithusername", "comp440", "pass1234");
			Statement state = con.createStatement();
			
			String sql = "SELECT leadername FROM (SELECT DISTINCT leadername, followername FROM follows WHERE\r\n" + 
					"followername= '"+userX+"' OR followername= '"+userY+"') leaderCount GROUP BY leadername HAVING count(leadername) >=2;";
			
			ResultSet rs = state.executeQuery(sql);
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int colNum = rsmd.getColumnCount();
				
			while(rs.next()) {
				for (int i = 1; i <= colNum; i++) {
					String val = rs.getString(i);
					userList.append(val + "\n");
				}
			}
			con.close();
		} 
		catch(Exception e) {
			System.out.print(e);
		}
		userList.setBounds(15, 52, 223, 367);
		contentPane.add(userList);
	}
}
