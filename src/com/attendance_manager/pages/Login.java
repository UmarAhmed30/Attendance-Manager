package com.attendance_manager.pages;
import java.sql.*;
import com.attendance_manager.components.*;
import com.attendance_manager.components.ColorTheme;
import java.sql.DriverManager;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;

public class Login extends JFrame {

	GridBagConstraints gbc = new GridBagConstraints();
	GridBagConstraints gbcL = new GridBagConstraints();

	public Login()
	{	//JDBC

		BufferedImage logo = null;
		Image resizedLogo = null;
		JLabel logoLabel = new JLabel();

		GothamFont gothamFont = new GothamFont();

		ColorTheme colorTheme = new ColorTheme();

		CustomBorder customBorder = new CustomBorder();

		gbc.insets = new Insets(10,10,10,10);

		try{
			logo = ImageIO.read(new File("src/resources/images/spotify.png"));
			resizedLogo = logo.getScaledInstance(75, 75, Image.SCALE_DEFAULT);
			logoLabel = new JLabel(new ImageIcon(resizedLogo));
		}
		catch (Exception e) {
			System.out.print("Image not found!");
		}

		JLabel appTitle = new JLabel("Attendance Manager");
		appTitle.setFont(gothamFont.assignFont("GothamBold", 30f));
		appTitle.setForeground(colorTheme.getAccColorLight());

		JPanel logoContainer = new JPanel();
		logoContainer.setBackground(colorTheme.getDarkTransColor());
		logoContainer.setPreferredSize(new Dimension(540, 150));

		logoContainer.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		logoContainer.add(logoLabel,gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		logoContainer.add(appTitle,gbc);

		gbc.insets = new Insets(20,20,20,20);

		JLabel containerTitle = new JLabel("Login");
		containerTitle.setFont(gothamFont.assignFont("GothamMedium", 20f));
		containerTitle.setForeground(colorTheme.getTextColor());
		containerTitle.setBorder(customBorder.assignBorder(Color.black, 0 , 25, 10, 0, 10));

		JPanel fieldContainer = new JPanel();
		fieldContainer.setBackground(colorTheme.getLightTransColor());
		fieldContainer.setPreferredSize(new Dimension(500, 150));

		JLabel emailLabel = new JLabel("Email");
		emailLabel.setFont(gothamFont.assignFont("GothamBook", 14f));
		emailLabel.setForeground(colorTheme.getTextColor());

		JTextField emailField = new RoundedTextField(20);
		emailField.setBorder(customBorder.assignBorder(Color.black, 1, 5, 10, 5, 0));
		emailField.setFont(gothamFont.assignFont("GothamBook", 14f));

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(gothamFont.assignFont("GothamBook", 14f));
		passwordLabel.setForeground(colorTheme.getTextColor());

		JTextField passwordField = new RoundedTextField(20);
		passwordField.setBorder(customBorder.assignBorder(Color.black, 1, 5, 10, 5, 0));
		passwordField.setFont(gothamFont.assignFont("GothamBook", 14f));

		JPanel loginContainer = new JPanel();
		loginContainer.setBackground(colorTheme.getLightTransColor());

		JLabel forgotPasswordLabel = new JLabel("Forgot Password?");
		forgotPasswordLabel.setFont(gothamFont.assignFont("GothamBook", 14f));
		forgotPasswordLabel.setForeground(colorTheme.getTextColor());

		JButton loginButton = new JButton("Login");
		loginButton.setBorder(new RoundedBorder(20, colorTheme.getPriColor()));
		loginButton.setFont(gothamFont.assignFont("GothamBold", 15f));
		loginButton.setBackground(colorTheme.getAccColorLight());
		loginButton.setForeground(colorTheme.getTextColor());

		JLabel signUpLabel = new JLabel("Don't have an account? Sign Up");
		signUpLabel.setFont(gothamFont.assignFont("GothamBold", 15f));
		signUpLabel.setForeground(colorTheme.getTextColor());
		signUpLabel.setBorder(customBorder.assignBorder(Color.black, 0 , 0, 10, 25, 10));

		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputEmail = emailField.getText();
				String inputPassword = passwordField.getText();
				System.out.println(inputEmail+" "+inputPassword);
				if(validateUser(inputEmail,inputPassword)){
					System.out.println("VALIDATED");

				}
				else{
					System.out.println("WRONG CREDENTIALS");
				}

			}
		});

		fieldContainer.setLayout(new GridBagLayout());
		gbcL.gridx = 0;
		gbcL.gridy = 0;
		gbcL.anchor = GridBagConstraints.WEST;
		fieldContainer.add(emailLabel,gbcL);
		gbc.gridx = 1;
		gbc.gridy = 0;
		fieldContainer.add(emailField, gbc);
		gbcL.gridx = 0;
		gbcL.gridy = 1;
		gbcL.anchor = GridBagConstraints.WEST;
		fieldContainer.add(passwordLabel,gbcL);
		gbc.gridx = 1;
		gbc.gridy = 1;
		fieldContainer.add(passwordField,gbc);

		loginContainer.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		loginContainer.add(containerTitle,gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		loginContainer.add(fieldContainer, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		loginContainer.add(forgotPasswordLabel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		loginContainer.add(loginButton, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		loginContainer.add(signUpLabel, gbc);


		gbc.insets = new Insets(0,0,0,0);


		setIconImage(new ImageIcon("src/resources/images/spotify.png").getImage());
		setTitle("Attendance Manager");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		setContentPane(new JLabel(new ImageIcon("src/resources/images/background.jpg")));

		setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(logoContainer,gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(loginContainer,gbc);


		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	private boolean validateUser(String email, String password) {
		final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost/javaproject";

		//  Database credentials
		final String USER = "root";
		final String PASS = "1984cezar";


			Connection conn = null;
			Statement stmt = null;
			String hashedPwd=BCrypt.hashpw(password,BCrypt.gensalt(5));
			System.out.println(hashedPwd);
			try{
				//STEP 2: Register JDBC driver
				Class.forName("com.mysql.cj.jdbc.Driver");

				//STEP 3: Open a connection
				System.out.println("Connecting to a selected database...");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				System.out.println("Connected database successfully...");

				//STEP 4: Execute a query
				String query="select password from user where email= ?";
				PreparedStatement checkExisting=conn.prepareStatement(query);
				checkExisting.setString(1,email);
				ResultSet rs=checkExisting.executeQuery();
				//System.out.println(rs);
				while(rs.next()){
					if (BCrypt.checkpw(password,rs.getString("password"))) {

						conn.close();
						return true;
					}
				}
				return false;

			}catch(SQLException se){
				//Handle errors for JDBC
				se.printStackTrace();
			}catch(Exception e){
				//Handle errors for Class.forName
				e.printStackTrace();
			}finally{
				//finally block used to close resources
				try{
					if(stmt!=null)
						conn.close();
				}catch(SQLException se){
				}// do nothing
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException se){
					se.printStackTrace();
				}//end finally try
			}//end try
			System.out.println("Goodbye!");
		return false;
	}


}

