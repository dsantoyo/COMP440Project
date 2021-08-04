package phase1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HubPage extends JFrame {

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
		
		//Need database connection here
		
		JLabel blogOne = new JLabel("TemptOne");
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
				blogOne.setText("Thanks");
			}
		});
		
		blogOne.setBounds(304, 71, 413, 20);
		contentPane.add(blogOne);
		
		JLabel blogTwo = new JLabel("TemptTwo");
		blogTwo.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		blogTwo.setBounds(304, 127, 413, 20);
		contentPane.add(blogTwo);
		
		JLabel blogThree = new JLabel("TemptThree");
		blogThree.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		blogThree.setBounds(304, 179, 413, 20);
		contentPane.add(blogThree);
		
		JLabel blogFour = new JLabel("TemptFour");
		blogFour.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		blogFour.setBounds(304, 233, 413, 20);
		contentPane.add(blogFour);
		
		JLabel blogFive = new JLabel("TemptFive");
		blogFive.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
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
	}
}
