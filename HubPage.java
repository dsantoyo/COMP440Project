package phase1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HubPage extends JFrame {

	private static final long serialVersionUID = 1L;
	int [] blogids = {1, 2, 3, 4, 5};
	String [] subs = {"", "", "", "", ""};
	String [] descs = {"", "", "", "", ""};
	String [] pdates = {"", "", "", "", ""};
	String [] names = {"", "", "", "", ""};

	private JPanel contentPane;

	public static void hub(String username) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HubPage frame = new HubPage(username);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HubPage(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1065, 707);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbwithusername", "comp440", "pass1234");
			Statement state = con.createStatement();
			
			ResultSet rs = state.executeQuery("SELECT * FROM blogs ORDER BY RAND() LIMIT 1");
			
			if(rs.next()) {
				blogids[0] = rs.getInt(1);
				subs[0] = rs.getString(2);
				descs[0] = rs.getString(3);
				pdates[0] = rs.getString(4);
				names[0] = rs.getString(5);
			}
		} 
		catch(Exception ex) {
			System.out.print(ex);
		}
		
		JLabel blogOne = new JLabel(subs[0]);
		blogOne.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		
		blogOne.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				
			}
			
			public void mousePressed(MouseEvent e) {
				
			}
			
			public void mouseExited(MouseEvent e) {
				
			}
			
			public void mouseEntered(MouseEvent e) {
				
			}
			
			public void mouseClicked(MouseEvent e) {
				ViewBlog.blogView(blogids[0], subs[0], descs[0], pdates[0], names[0], username);
			}
		});
		
		blogOne.setBounds(304, 71, 413, 20);
		contentPane.add(blogOne);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbwithusername", "comp440", "pass1234");
			Statement state = con.createStatement();
			
			ResultSet rs = state.executeQuery("SELECT * FROM blogs ORDER BY RAND() LIMIT 1");
			
			if(rs.next()) {
				blogids[1] = rs.getInt(1);
				subs[1] = rs.getString(2);
				descs[1] = rs.getString(3);
				pdates[1] = rs.getString(4);
				names[1] = rs.getString(5);
			}
		} 
		catch(Exception ex) {
			System.out.print(ex);
		}
		
		JLabel blogTwo = new JLabel(subs[1]);
		blogTwo.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		
		blogTwo.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				
			}
			
			public void mousePressed(MouseEvent e) {
				
			}
			
			public void mouseExited(MouseEvent e) {
				
			}
			
			public void mouseEntered(MouseEvent e) {
				
			}
			
			public void mouseClicked(MouseEvent e) {
				ViewBlog.blogView(blogids[1], subs[1], descs[1], pdates[1], names[1], username);
			}
		});
		
		blogTwo.setBounds(304, 127, 413, 20);
		contentPane.add(blogTwo);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbwithusername", "comp440", "pass1234");
			Statement state = con.createStatement();
			
			ResultSet rs = state.executeQuery("SELECT * FROM blogs ORDER BY RAND() LIMIT 1");
			
			if(rs.next()) {
				blogids[2] = rs.getInt(1);
				subs[2] = rs.getString(2);
				descs[2] = rs.getString(3);
				pdates[2] = rs.getString(4);
				names[2] = rs.getString(5);
			}
		} 
		catch(Exception ex) {
			System.out.print(ex);
		}
		
		JLabel blogThree = new JLabel(subs[2]);
		blogThree.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		
		blogThree.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				
			}
			
			public void mousePressed(MouseEvent e) {
				
			}
			
			public void mouseExited(MouseEvent e) {
				
			}
			
			public void mouseEntered(MouseEvent e) {
				
			}
			
			public void mouseClicked(MouseEvent e) {
				ViewBlog.blogView(blogids[2], subs[2], descs[2], pdates[2], names[2], username);
			}
		});
		
		blogThree.setBounds(304, 179, 413, 20);
		contentPane.add(blogThree);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbwithusername", "comp440", "pass1234");
			Statement state = con.createStatement();
			
			ResultSet rs = state.executeQuery("SELECT * FROM blogs ORDER BY RAND() LIMIT 1");
			
			if(rs.next()) {
				blogids[3] = rs.getInt(1);
				subs[3] = rs.getString(2);
				descs[3] = rs.getString(3);
				pdates[3] = rs.getString(4);
				names[3] = rs.getString(5);
			}
		} 
		catch(Exception ex) {
			System.out.print(ex);
		}
		
		JLabel blogFour = new JLabel(subs[3]);
		blogFour.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		
		blogFour.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				
			}
			
			public void mousePressed(MouseEvent e) {
				
			}
			
			public void mouseExited(MouseEvent e) {
				
			}
			
			public void mouseEntered(MouseEvent e) {
				
			}
			
			public void mouseClicked(MouseEvent e) {
				ViewBlog.blogView(blogids[3], subs[3], descs[3], pdates[3], names[3], username);
			}
		});
		
		blogFour.setBounds(304, 233, 413, 20);
		contentPane.add(blogFour);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbwithusername", "comp440", "pass1234");
			Statement state = con.createStatement();
			
			ResultSet rs = state.executeQuery("SELECT * FROM blogs ORDER BY RAND() LIMIT 1");
			
			if(rs.next()) {
				blogids[4] = rs.getInt(1);
				subs[4] = rs.getString(2);
				descs[4] = rs.getString(3);
				pdates[4] = rs.getString(4);
				names[4] = rs.getString(5);
			}
		} 
		catch(Exception ex) {
			System.out.print(ex);
		}
		
		JLabel blogFive = new JLabel(subs[4]);
		blogFive.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		
		blogFive.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				
			}
			
			public void mousePressed(MouseEvent e) {
				
			}
			
			public void mouseExited(MouseEvent e) {
				
			}
			
			public void mouseEntered(MouseEvent e) {
				
			}
			
			public void mouseClicked(MouseEvent e) {
				ViewBlog.blogView(blogids[4], subs[4], descs[4], pdates[4], names[4], username);
			}
		});
		
		blogFive.setBounds(304, 288, 413, 20);
		contentPane.add(blogFive);
		
		JButton insertBButton = new JButton("Insert a blog");
		insertBButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				BlogInsert.blogInsert(username);
			}
		});
		insertBButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		insertBButton.setBounds(399, 430, 247, 55);
		contentPane.add(insertBButton);
		
		JLabel Title = new JLabel("Click a Blog Subject to View a Blog!");
		Title.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		Title.setBounds(399, 0, 271, 36);
		contentPane.add(Title);
		
		JButton btnViewBlogsOf = new JButton("View Blogs of User X");
		btnViewBlogsOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UsersX.UserX();
			}
		});
		btnViewBlogsOf.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnViewBlogsOf.setBounds(66, 430, 247, 55);
		contentPane.add(btnViewBlogsOf);
		
		JButton mostBlogsButton = new JButton("User list of most blogs");
		mostBlogsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbwithusername", "comp440", "pass1234");
					Statement state = con.createStatement();
					
					String sql = "SELECT pdate FROM blogs WHERE pdate= '2020-10-10';";
					ResultSet rs = state.executeQuery(sql);
					
					if(rs.next()) {
						MostBlogsDate.MostBlogDate();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "No blogs were posted on that date");
					}
					
					con.close();
				} 
				catch(Exception e) {
					System.out.print(e);
				}
			}
		});
		mostBlogsButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		mostBlogsButton.setBounds(729, 430, 247, 55);
		contentPane.add(mostBlogsButton);
		
		JLabel dateLabel = new JLabel("10/10/2020");
		dateLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		dateLabel.setBounds(804, 394, 109, 20);
		contentPane.add(dateLabel);
		
		JButton followed_by_XY = new JButton("Followed by X and Y");
		followed_by_XY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UsersXY.UserXY();
			}
		});
		followed_by_XY.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		followed_by_XY.setBounds(66, 525, 247, 55);
		contentPane.add(followed_by_XY);
		
		JButton tagX = new JButton("Tag X");
		tagX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TagPrompt.Prompt();
			}
		});
		tagX.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		tagX.setBounds(399, 525, 247, 55);
		contentPane.add(tagX);
		
		JButton noComm = new JButton("Who've Never Commented");
		noComm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NeverCommented.NeverComm();
			}
		});
		noComm.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		noComm.setBounds(729, 525, 247, 55);
		contentPane.add(noComm);
	}
}
