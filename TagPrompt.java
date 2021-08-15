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
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class TagPrompt extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tagField;

	public static void Prompt() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TagPrompt frame = new TagPrompt();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TagPrompt() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel prompt = new JLabel("Enter a tag:");
		prompt.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		prompt.setBounds(164, 16, 99, 20);
		contentPane.add(prompt);
		
		tagField = new JTextField();
		tagField.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		tagField.setBounds(106, 52, 200, 26);
		contentPane.add(tagField);
		tagField.setColumns(10);
		
		JButton ListButton = new JButton("List Blogs");
		ListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbwithusername", "comp440", "pass1234");
					Statement state = con.createStatement();
					
					String sql = "SELECT blogid FROM blogstags WHERE tag= '"+tagField.getText()+"'";
					
					ResultSet rs = state.executeQuery(sql);
					
					if(rs.next()) {
						
						rs = state.executeQuery(sql);
						
						ResultSetMetaData rsmd = rs.getMetaData();
						int colNum = rsmd.getColumnCount();
						Vector<Integer> ids = new Vector<Integer>();
							
						while(rs.next()) {
							for (int i = 1; i <= colNum; i++) {
								ids.addElement(rs.getInt(i));
							}
						}
						
						TagX.Tag(ids, tagField.getText());
					}
					else {
						JOptionPane.showMessageDialog(null, "No such blog for tag: " + tagField.getText());
					}

					con.close();
				} 
				catch(Exception e) {
					System.out.print(e);
				}
			}
		});
		ListButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		ListButton.setBounds(159, 109, 115, 29);
		contentPane.add(ListButton);
	}
}
