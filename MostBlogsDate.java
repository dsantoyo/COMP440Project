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

public class MostBlogsDate extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void MostBlogDate() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostBlogsDate frame = new MostBlogsDate();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MostBlogsDate() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 513, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Users who've posted the most number of blogs on 10/10/2020:");
		title.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		title.setBounds(15, 16, 461, 35);
		contentPane.add(title);
		
		JTextArea userList = new JTextArea();
		userList.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		userList.setEditable(false);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbwithusername", "comp440", "pass1234");
			Statement state = con.createStatement();
			
			String sql = "DROP VIEW IF EXISTS `blogcount`";
			state.executeUpdate(sql);
			
			sql = "CREATE VIEW blogcount AS SELECT COUNT(created_by) AS blog_count FROM blogs WHERE pdate= '2020-10-10' "
					+ "GROUP BY created_by;";
			state.executeUpdate(sql);
			
			sql = "SELECT created_by, COUNT(created_by) as Total FROM blogs WHERE pdate= '2020-10-10' \r\n" + 
					"GROUP BY created_by HAVING COUNT(created_by) IN (SELECT MAX(blog_count) FROM blogcount);";
			
			ResultSet rs = state.executeQuery(sql);
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int colNum = rsmd.getColumnCount();
				
			while(rs.next()) {
				for (int i = 1; i <= colNum; i++) {
					String val = rs.getString(i);
					userList.append(val + " ");
				}
				userList.append("\n");
			}
			
			con.close();
		} 
		catch(Exception e) {
			System.out.print(e);
		}
		
		userList.setBounds(25, 67, 278, 383);
		contentPane.add(userList);
	}
}
