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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class NeverCommented extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void NeverComm() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NeverCommented frame = new NeverCommented();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NeverCommented() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 593, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Those who've never posted a comment:");
		title.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		title.setBounds(15, 16, 490, 40);
		contentPane.add(title);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 72, 385, 378);
		contentPane.add(scrollPane);
		
		JTextArea list = new JTextArea();
		list.setWrapStyleWord(true);
		list.setLineWrap(true);
		list.setEditable(false);
		list.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		scrollPane.setViewportView(list);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbwithusername", "comp440", "pass1234");
			Statement state = con.createStatement();
			
			ResultSet rs = state.executeQuery("SELECT username FROM users WHERE username NOT IN \r\n" + 
					"(SELECT posted_by FROM comments);");
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int colNum = rsmd.getColumnCount();
			
			while(rs.next()) {
				for (int i = 1; i <= colNum; i++) {
					String val = rs.getString(i);
					list.append(val + "\n");
				}
			}
			con.close();
		}
		catch(Exception e) {
			System.out.print(e);
		}
	}
}
