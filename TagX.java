package phase1;

import java.awt.EventQueue;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTextArea;

public class TagX extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void Tag(Vector<Integer> ids, String tag) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TagX frame = new TagX(ids, tag);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TagX(Vector<Integer> ids, String tag) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 684, 614);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Blogs that contain the tag " + tag + ":");
		title.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		title.setBounds(15, 16, 296, 20);
		contentPane.add(title);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 52, 577, 426);
		contentPane.add(scrollPane);
		
		JTextArea list = new JTextArea();		
		scrollPane.setViewportView(list);
		list.setEditable(false);
		list.setLineWrap(true);
		list.setWrapStyleWord(true);
		list.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbwithusername", "comp440", "pass1234");
			Statement state = con.createStatement();
			
			for(int i = 0; i < ids.size(); i++) {
				
				ResultSet rsDate = state.executeQuery("SELECT pdate FROM blogs WHERE blogid= '"+ids.get(i)+"'");
				if(rsDate.next()) {
					list.append("| Posted On: " + rsDate.getString(1));
				}
				
				ResultSet rsUser = state.executeQuery("SELECT created_by FROM blogs WHERE blogid= '"+ids.get(i)+"'");
				if(rsUser.next()) {
					list.append(" By: " + rsUser.getString(1) + " |\n");
				}
				
				ResultSet rsSub = state.executeQuery("SELECT subject FROM blogs WHERE blogid= '"+ids.get(i)+"'");
				if(rsSub.next()) {
					list.append("Subject: " + rsSub.getString(1) + "\n");
				}
				
				ResultSet rsDesc = state.executeQuery("SELECT description FROM blogs WHERE blogid= '"+ids.get(i)+"'");
				if(rsDesc.next()) {
					list.append("Description: " + rsDesc.getString(1));
				}
				list.append("\n" + "\n");
			}

			con.close();
		} 
		catch(Exception e) {
			System.out.print(e);
		}
		
	}
}
