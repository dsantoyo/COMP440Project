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
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ListBlogsX extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void ListBlogX(String username) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListBlogsX frame = new ListBlogsX(username);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ListBlogsX(String username) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1077, 701);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel uname = new JLabel("All Blogs Posted by " + username + " with Positive Comment(s):");
		uname.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		uname.setBounds(15, 16, 516, 20);
		contentPane.add(uname);
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(15, 52, 688, 517);
		contentPane.add(scroll);
		
		JTextArea listOfBlogs = new JTextArea();
		listOfBlogs.setEditable(false);
		listOfBlogs.setLineWrap(true);
		listOfBlogs.setWrapStyleWord(true);
		listOfBlogs.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbwithusername", "comp440", "pass1234");
			Statement state = con.createStatement();
			
			Vector<Integer> blogids = getBlogIds(username);
			
			for(int i = 1; i < blogids.size()+1; i++) {
				
				ResultSet rsSub = state.executeQuery("SELECT subject FROM blogs WHERE blogid= '"+blogids.get(i-1)+"'");
				if(rsSub.next()) {
					listOfBlogs.append("Subject: " + rsSub.getString(1));
				}
				
				ResultSet rsDate = state.executeQuery("SELECT pdate FROM blogs WHERE blogid= '"+blogids.get(i-1)+"'");
				if(rsDate.next()) {
					listOfBlogs.append("            Posted on: " + rsDate.getString(1) + "\n");
				}
				
				ResultSet rsDesc = state.executeQuery("SELECT description FROM blogs WHERE blogid= '"+blogids.get(i-1)+"'");
				if(rsDesc.next()) {
					listOfBlogs.append("Description: " + rsDesc.getString(1) + "\n");
				}
				
				ResultSet rsCom = state.executeQuery("SELECT description FROM comments WHERE blogid= '"+blogids.get(i-1)+"' AND sentiment= 'positive'");
				if(rsCom.next()) {
					listOfBlogs.append("Comment: " + rsCom.getString(1));
				}				
				listOfBlogs.append("\n" + "\n");
			}

			con.close();
		}
		catch(Exception e) {
			System.out.print(e);
		}
		
		scroll.setViewportView(listOfBlogs);
	}
	
	public static Vector<Integer> getBlogIds(String username){
		Vector<Integer> ids = new Vector<Integer>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbwithusername", "comp440", "pass1234");
			Statement state = con.createStatement();
			
			String sql = "SELECT blogid FROM blogs WHERE created_by= '"+username+"'";
			ResultSet rs = state.executeQuery(sql);
			
			if(rs.next()) {
				
				rs = state.executeQuery(sql);
				
				ResultSetMetaData rsmd = rs.getMetaData();
				int colNum = rsmd.getColumnCount();
					
				while(rs.next()) {
					for (int i = 1; i <= colNum; i++) {
						ids.addElement(rs.getInt(i));
					}
				}
			}
			con.close();
		}
		catch(Exception e) {
			System.out.print(e);
		}
		
		return ids;
	}
}