package phase1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

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
		
		JLabel uname = new JLabel("Username: " + username);
		uname.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		uname.setBounds(15, 16, 162, 20);
		contentPane.add(uname);
		
		JLabel subOne = new JLabel("Subject: ");
		subOne.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		subOne.setBounds(15, 52, 69, 23);
		contentPane.add(subOne);
	}

}
