package phase1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class ViewBlog extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField subField;
	private JLabel description;

	/**
	 * Launch the application.
	 */
	public static void blogView(int blogid, String sub, String desc, String pdate, String username, String name) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewBlog frame = new ViewBlog(blogid, sub, desc, pdate, username, name);
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
	public ViewBlog(int blogid, String sub, String desc, String pdate, String username, String name) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 784, 568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel subject = new JLabel("Subject:");
		subject.setBounds(36, 49, 69, 29);
		subject.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		contentPane.add(subject);
		
		subField = new JTextField();
		subField.setText(sub);
		subField.setEditable(false);
		subField.setBounds(120, 50, 400, 26);
		subField.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		contentPane.add(subField);
		subField.setColumns(10);
		
		description = new JLabel("Description:");
		description.setBounds(15, 119, 98, 20);
		description.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		contentPane.add(description);
		
		JTextPane txtpnBlah = new JTextPane();
		txtpnBlah.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		txtpnBlah.setText(desc);
		txtpnBlah.setEditable(false);
		txtpnBlah.setBounds(120, 119, 400, 221);
		contentPane.add(txtpnBlah);
		
		JLabel Date = new JLabel("Date: " + pdate);
		Date.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		Date.setBounds(535, 49, 179, 29);
		contentPane.add(Date);
		
		JLabel Posted_By = new JLabel("Posted by: " + username);
		Posted_By.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		Posted_By.setBounds(535, 110, 212, 29);
		contentPane.add(Posted_By);
		
		JLabel Comment = new JLabel("Comment:");
		Comment.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		Comment.setBounds(36, 377, 69, 29);
		contentPane.add(Comment);
		
		JTextPane comField = new JTextPane();
		comField.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		comField.setBounds(120, 377, 400, 83);
		contentPane.add(comField);
		
		JComboBox<String> comStatus = new JComboBox<String>();
		comStatus.addItem("Positive");
		comStatus.addItem("Negative");
		comStatus.setSelectedItem(null);
		
		comStatus.setSelectedIndex(-1);
		comStatus.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		comStatus.setBounds(535, 379, 107, 26);
		contentPane.add(comStatus);
		
		JButton postCom = new JButton("Post Comment");
		postCom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbwithusername", "comp440", "pass1234");
					Statement state = con.createStatement();
					
					boolean valid = true;
					
					String comment = comField.getText();
					String sentiment = (String)comStatus.getSelectedItem();
					
					DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDateTime now = LocalDateTime.now();
					String cdate = date.format(now);
					
					//Idea on how to prevent a user from commenting more than once
					ResultSet rs = state.executeQuery("SELECT blogid, posted_by FROM comments");
					
					while(rs.next()) {
						if(blogid == rs.getInt("blogid") && name.equals(rs.getString("posted_by")))
						{
							valid = false;
							JOptionPane.showMessageDialog(null, "You have already posted a comment on this blog");
						}
					}
					
					if(comment.length() == 0) {
						valid = false;
						JOptionPane.showMessageDialog(null, "Please enter a comment");
					}
					
					if(sentiment == null) {
						valid = false;
						JOptionPane.showMessageDialog(null, "Please select a sentiment");
					}
					
					if(username.equals(name)) {
						valid = false;
						JOptionPane.showMessageDialog(null, "Cannot comment on your own blog");
					}
					
					if(valid) {
						String insert = "INSERT INTO comments (sentiment, description, cdate, blogid, posted_by) " + "VALUES ('"+sentiment+"', '"+comment+"', '"+cdate+"', '"+blogid+"', '"+name+"');";
						state.executeUpdate(insert);
						JOptionPane.showMessageDialog(null, "Added Comment!");
					}
				}
				catch(Exception e) {
					System.out.println(e);
				}
			}
		});
		postCom.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		postCom.setBounds(262, 476, 151, 29);
		contentPane.add(postCom);
	}
}
