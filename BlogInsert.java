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
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class BlogInsert extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField subField;
	private JTextField tagField;

	public static void blogInsert(String username) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlogInsert frame = new BlogInsert(username);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BlogInsert(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 541);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel mainLabel = new JLabel("Insert a Blog");
		mainLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		mainLabel.setBounds(199, 0, 175, 45);
		contentPane.add(mainLabel);
		
		JLabel subject = new JLabel("Subject:");
		subject.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		subject.setBounds(15, 98, 71, 33);
		contentPane.add(subject);
		
		subField = new JTextField();
		subField.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		subField.setBounds(101, 92, 313, 45);
		contentPane.add(subField);
		subField.setColumns(10);
		
		JLabel description = new JLabel("Description:");
		description.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		description.setBounds(15, 168, 109, 33);
		contentPane.add(description);
		
		JTextPane descField = new JTextPane();
		descField.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		descField.setBounds(123, 175, 291, 154);
		contentPane.add(descField);
		
		JLabel tags = new JLabel("Tags:");
		tags.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		tags.setBounds(15, 350, 71, 33);
		contentPane.add(tags);
		
		tagField = new JTextField();
		tagField.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		tagField.setColumns(10);
		tagField.setBounds(101, 345, 313, 45);
		contentPane.add(tagField);
		
		JButton insertButton = new JButton("Insert");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbwithusername", "root", "rock");
					Statement state = con.createStatement();
					
					boolean valid = true;
					
					String sub = subField.getText();
					String desc = descField.getText();
					String tag = tagField.getText();
					
					DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDateTime now = LocalDateTime.now();
					String pdate = date.format(now);
					
					String call = "call Insert_Blog_Procedure('"+sub+"', '"+desc+"', '"+pdate+"', '"+username+"', '"+tag+"');";
					
					if(sub.length() == 0) {
						JOptionPane.showMessageDialog(null, "Please enter a subject");
						valid = false;
					}
					
					if(desc.length() == 0) {
						JOptionPane.showMessageDialog(null, "Please enter a description");
						valid = false;
					}
					
					if(tag.length() == 0) {
						JOptionPane.showMessageDialog(null, "Please enter your tag(s)");
						valid = false;
					}
					
					if(valid) {
						state.executeUpdate(call);
						JOptionPane.showMessageDialog(null, "Blog inserted!");
						
					}
					con.close();
				}
				catch(Exception e) {
					System.out.println(e);
				}
			}
		});
		insertButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		insertButton.setBounds(199, 420, 115, 49);
		contentPane.add(insertButton);
	}
}
