package phase1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class rl_interface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField user;
	private JPasswordField pass;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rl_interface frame = new rl_interface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public rl_interface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Project Login Page");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel.setBounds(50, 43, 255, 43);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(15, 116, 83, 33);
		contentPane.add(lblNewLabel_1);
		
		user = new JTextField();
		user.setBounds(94, 120, 189, 26);
		contentPane.add(user);
		user.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password: ");
		lblNewLabel_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(15, 173, 83, 33);
		contentPane.add(lblNewLabel_1_1);
		
		pass = new JPasswordField();
		pass.setBounds(94, 177, 189, 26);
		contentPane.add(pass);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbwithusername", "comp440", "pass1234");
					Statement state = con.createStatement();
					
					String password = String.valueOf(pass.getPassword());
					String sql = "SELECT * FROM users where username='"+user.getText()+"' and password='"+password+"'";
					
					ResultSet rs = state.executeQuery(sql);
					
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Logged in");
						dispose();
						HubPage.hub(user.getText());
					}
					else {
						JOptionPane.showMessageDialog(null, "Incorrect username or password");
					}
					con.close();
				} 
				catch(Exception e) {
					System.out.print(e);
				}
			}
		});
		btnNewButton.setBounds(15, 235, 115, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Register");
		btnNewButton_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register.register();
			}
		});
		btnNewButton_1.setBounds(190, 235, 115, 29);
		contentPane.add(btnNewButton_1);
		
		JButton iniDbButton = new JButton("Initialize Database");
		iniDbButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbwithusername", "comp440", "pass1234");
					Statement state = con.createStatement();
					
					if(JOptionPane.showConfirmDialog(null, "Your account will remain, but your blogs, tags, and comments will be deleted. Continue?", "Initialize Database", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						
						String sql = "SET FOREIGN_KEY_CHECKS = 0";
						state.executeUpdate(sql);
						
						sql = "DROP TABLE IF EXISTS `users`";
						state.executeUpdate(sql);
						
						sql = "DROP TABLE IF EXISTS `blogs`";
						state.executeUpdate(sql);
						
						sql = "DROP TABLE IF EXISTS `blogstags`";
						state.executeUpdate(sql);
						
						sql = "DROP TABLE IF EXISTS `comments`";
						state.executeUpdate(sql);
						
						sql = "DROP TABLE IF EXISTS `follows`";
						state.executeUpdate(sql);
						
						sql = "DROP TABLE IF EXISTS `hobbies`";
						state.executeUpdate(sql);
						
						sql = "SET FOREIGN_KEY_CHECKS = 1";
						state.executeUpdate(sql);
						
						
						sql = "CREATE TABLE `users` (\r\n" + 
								"  `username` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,\r\n" + 
								"  `password` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,\r\n" + 
								"  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,\r\n" + 
								"  PRIMARY KEY (`username`)\r\n" + 
								")";
						
						state.executeUpdate(sql);
						
						sql = "CREATE TABLE `blogs` (\r\n" + 
								"  `blogid` int(10) unsigned NOT NULL AUTO_INCREMENT,\r\n" + 
								"  `subject` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,\r\n" + 
								"  `description` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,\r\n" + 
								"  `pdate` date DEFAULT NULL,\r\n" + 
								"  `created_by` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,\r\n" + 
								"  PRIMARY KEY (`blogid`),\r\n" + 
								"  KEY `FK1_idx` (`description`),\r\n" + 
								"  KEY `FK1` (`created_by`),\r\n" + 
								"  CONSTRAINT `FK1` FOREIGN KEY (`created_by`) REFERENCES `users` (`username`)\r\n" + 
								")";
						
						state.executeUpdate(sql);
						
						sql = "CREATE TABLE `blogstags` (\r\n" + 
								"  `blogid` int(10) unsigned NOT NULL AUTO_INCREMENT,\r\n" + 
								"  `tag` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,\r\n" + 
								"  PRIMARY KEY (`blogid`,`tag`),\r\n" + 
								"  CONSTRAINT `blogstags_ibfk_1` FOREIGN KEY (`blogid`) REFERENCES `blogs` (`blogid`)\r\n" + 
								")";
						
						state.executeUpdate(sql);
						
						sql = "CREATE TABLE `comments` (\r\n" + 
								"  `commentid` int(10) NOT NULL AUTO_INCREMENT,\r\n" + 
								"  `sentiment` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,\r\n" + 
								"  `description` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,\r\n" + 
								"  `cdate` date DEFAULT NULL,\r\n" + 
								"  `blogid` int(10) unsigned DEFAULT NULL,\r\n" + 
								"  `posted_by` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,\r\n" + 
								"  PRIMARY KEY (`commentid`),\r\n" + 
								"  KEY `comments_ibfk_1` (`blogid`),\r\n" + 
								"  KEY `comments_ibfk_2` (`posted_by`),\r\n" + 
								"  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`blogid`) REFERENCES `blogs` (`blogid`),\r\n" + 
								"  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`posted_by`) REFERENCES `users` (`username`),\r\n" + 
								"  CONSTRAINT `sentiment_types` CHECK ((`sentiment` in (_utf8mb4'negative',_utf8mb4'positive')))\r\n" + 
								")";
						
						state.executeUpdate(sql);
						
						sql = "CREATE TABLE `follows` (\r\n" + 
								"  `leadername` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,\r\n" + 
								"  `followername` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,\r\n" + 
								"  PRIMARY KEY (`leadername`,`followername`),\r\n" + 
								"  KEY `follows_ibfk_2` (`followername`),\r\n" + 
								"  CONSTRAINT `follows_ibfk_1` FOREIGN KEY (`leadername`) REFERENCES `users` (`username`),\r\n" + 
								"  CONSTRAINT `follows_ibfk_2` FOREIGN KEY (`followername`) REFERENCES `users` (`username`)\r\n" + 
								")";
						
						state.executeUpdate(sql);
						
						sql = "CREATE TABLE `hobbies` (\r\n" + 
								"  `username` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,\r\n" + 
								"  `hobby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,\r\n" + 
								"  PRIMARY KEY (`hobby`,`username`),\r\n" + 
								"  KEY `hobbies_ibfk_1` (`username`),\r\n" + 
								"  CONSTRAINT `hobbies_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`),\r\n" + 
								"  CONSTRAINT `hobby_types` CHECK ((`hobby` in (_utf8mb4'hiking',_utf8mb4'swimming',_utf8mb4'calligraphy',_utf8mb4'bowling',_utf8mb4'movie',_utf8mb4'cooking',_utf8mb4'dancing')))\r\n" + 
								")";
						
						state.executeUpdate(sql);
						
						
						sql = "INSERT INTO `users` VALUES ('batman','1234','nananana@batman.com'),('bob','12345','bobthatsme@yahoo.com'),"
								+ "('catlover','abcd','catlover@whiskers.com'),('doglover','efds','doglover@bark.net'),"
								+ "('jdoe','25478','jane@doe.com'),('jsmith','1111','jsmith@gmail.com'),('matty','2222','matty@csun.edu'),"
								+ "('notbob','5555','stopcallingmebob@yahoo.com'),('pacman','9999','pacman@gmail.com'),"
								+ "('scooby','8888','scooby@doo.net')";
						
						state.executeUpdate(sql);
						
						sql = "INSERT INTO `blogs` VALUES (1,'Hello World','Hey everyone, this is my first blog. "
								+ "Hello world and all who inhabit it!','2020-10-10','jsmith'),"
								+ "(2,'I love cats!','Cats are amazing. They\\'re awesome, and fuzzy, and cute. "
								+ "Who DOESN\\'T love cats?','2020-03-17','catlover'),(3,'Dogs are the best.',"
								+ "'So I saw a post the other day talking about cats. Now, I love cats. They\\'re great. "
								+ "But here\\'s the thing: dogs are just the best, okay? There\\'s no question about it. "
								+ "That is all.','2020-03-19','doglover'),(4,'I am the night.','To all you lowly criminals "
								+ "out there, this is a warning to know I am watching. I am justice. I am righteousness. "
								+ "I am the NIGHT.','2020-03-24','batman'),(5,'Waka waka','waka waka waka waka waka waka "
								+ "waka waka waka waka waka waka waka waka waka waka','2020-03-31','pacman'),"
								+ "(6,'Who is this Bob guy?','Decided to start tracking down this mysterious "
								+ "human known as \\'Bob.\\' Who is Bob? What does he do? WHY does he do it? "
								+ "There is a lot of mystery surrounding this person, and I will be going into detail "
								+ "in future posts. Stay tuned!','2020-04-02','notbob'),"
								+ "(7,'Re: I love cats.','A reader recently reached out to me about my last post. "
								+ "To be clear, I\\'m not dissing our canine companions! But we\\'ve got to be honest "
								+ "here, why are cats better? They\\'re smart, affectionate, and great cuddle partners. "
								+ "Cats are better. It\\'s just fact.','2020-04-04','catlover'),"
								+ "(8,'Scooby Dooby Doo!','The search for scooby snacks: "
								+ "Where did they go? I know this whole quarantine thing is affecting businesses, "
								+ "but aren\\'t scooby snacks counted as an essential service? "
								+ "Please post below if you find anything! I\\'m going crazy here!','2020-04-05','scooby'),"
								+ "(9,'Bob Update','Dear readers, I know you have been waiting anxiously for "
								+ "an update on Bob, but there is not much to share so far. He appears to have little "
								+ "to no online presence. Just a clarification: I am decidedly NOT Bob. Thanks all. "
								+ "Stay tuned for more!','2020-04-06','notbob'),"
								+ "(10,'Lizard People.','What are your guys\\' thoughts on them? I, for one, welcome "
								+ "out reptitlian overlords.','2020-04-12','jdoe'), "
								+ "(11,'Introduction','Hey everyone, my name is Smith.','2020-10-10','jsmith'), "
								+ "(12,'Big cats are scary','Big cats used to hunt humans!','2020-10-10','catlover'), "
								+ "(13,'Kitties','Cats grow up so fast!','2020-10-10','catlover'), "
								+ "(14,'The Best Friend of Humanity',"
								+ "'Dogs truly are your best friend!','2020-10-10','doglover');\r\n" + 
								"";
						
						state.executeUpdate(sql);
						
						sql = "INSERT INTO `blogstags` VALUES (1,'hello world'),(2,'animals'),(2,'cats'),(3,'animals'),"
								+ "(3,'dogs'),(4,'crime'),(4,'justice'),(5,'cartoon'),(5,'waka'),(6,'bob'),(6,'update'),"
								+ "(7,'cats'),(7,'dogs'),(8,'dogs'),(8,'quarantine'),(8,'scooby snacks'),(9,'bob'),"
								+ "(9,'update'),(10,'lizards');";
						
						state.executeUpdate(sql);
						
						sql = "INSERT INTO `comments` VALUES (1,'negative','Cats are cool and all, but dogs are where it\\'s at.',"
								+ "'2020-03-17',2,'doglover'),(2,'negative','What\\'s all the hype about? Cats are clearly "
								+ "superior.','2020-03-20',3,'catlover'),(3,'positive','Nice.','2020-03-20',4,'scooby'),"
								+ "(4,'positive','Who IS Bob? I can\\'t wait to find out.','2020-04-02',6,'jsmith'),"
								+ "(5,'negative','I guess cat people just don\\'t know what they\\'re missing.','2020-04-05',"
								+ "7,'doglover'),(6,'positive','This is totally unrelated, but I just wanted to say I am a HUGE "
								+ "fan of yours. I love your work!','2020-04-05',8,'doglover'),(7,'positive','Have you checked "
								+ "out Dog-Mart? They\\'ve got everything.','2020-04-06',8,'matty'),(8,'negative','I was hoping"
								+ " there\\'d be more of an update. Sorry, Bob.','2020-04-07',9,'jsmith'),(9,'positive','I "
								+ "think they\\'re all secretly cats. Open your eyes, sheeple!','2020-04-13',10,'doglover'),"
								+ "(10,'negative','Who? Me? Multimillionaire philanthropist of Arkham? A lizard person? Nope. "
								+ "Nothing to see here!','2020-04-15',10,'batman');\r\n" + "";
						
						state.executeUpdate(sql);
						
						sql = "INSERT INTO `follows` VALUES ('jsmith','bob'),('batman','catlover'),('doglover','catlover'),"
								+ "('catlover','doglover'),('jsmith','jdoe'),('pacman','matty'),('bob','notbob'),"
								+ "('jdoe','notbob'),('batman','pacman'),('scooby','pacman'),('doglover','scooby'),"
								+ "('pacman','scooby'), ('bob', 'scooby'), ('bob', 'catlover'), ('batman', 'scooby');\r\n" + 
								"";
						
						state.executeUpdate(sql);
						
						sql = "INSERT INTO `hobbies` VALUES ('batman','movie'),('bob','movie'),('catlover','movie'),"
								+ "('doglover','hiking'),('jdoe','dancing'),('jdoe','movie'),('jsmith','hiking'),"
								+ "('matty','bowling'),('notbob','calligraphy'),('pacman','dancing'),('pacman','movie'),"
								+ "('scooby','cooking');\r\n" + "";
						
						state.executeUpdate(sql);
						
						sql = "INSERT INTO `users` (username, password, email) "
								+ "SELECT username, password, email FROM `backup`";
						
						state.executeUpdate(sql);
					}
					
					con.close();
				} 
				catch(Exception e) {
					System.out.print(e);
				}
			}
		});
		iniDbButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		iniDbButton.setBounds(94, 290, 176, 29);
		contentPane.add(iniDbButton);
	}
}
