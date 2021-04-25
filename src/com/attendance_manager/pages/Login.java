package com.attendance_manager.pages;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Login extends JFrame {

	GridBagConstraints gbc = new GridBagConstraints();

	public Login()
	{
		BufferedImage logo = null;
		Image resizedLogo = null;
		JLabel logoLabel = new JLabel();

		gbc.insets = new Insets(10,10,10,10);

		try{
			logo = ImageIO.read(new File("src/resources/images/logo_1.png"));
			resizedLogo = logo.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
			logoLabel = new JLabel(new ImageIcon(resizedLogo));
		}
		catch (Exception e) {
			System.out.print("Image not found!");
		}

		JLabel appTitle = new JLabel("Attendance Manager");

		JPanel logoContainer = new JPanel();
		logoContainer.setBackground(new Color(255, 255, 255, 10));
		logoContainer.setPreferredSize(new Dimension(500, 150));

		logoContainer.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		logoContainer.add(logoLabel,gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		logoContainer.add(appTitle,gbc);

		gbc.insets = new Insets(20,20,20,20);

		JLabel containerTitle = new JLabel("Login");

		JPanel fieldContainer = new JPanel();
		fieldContainer.setBackground(new Color(255, 255, 255, 5));
		fieldContainer.setPreferredSize(new Dimension(400, 150));

		JLabel emailLabel = new JLabel("Email");
		JTextField emailField = new JTextField();
		emailField.setColumns(20);
		JLabel passwordLabel = new JLabel("Password");
		JTextField passwordField = new JTextField();
		passwordField.setColumns(20);

		JPanel loginContainer = new JPanel();
		loginContainer.setBackground(new Color(255, 255, 255, 5));
		loginContainer.setPreferredSize(new Dimension(500,450));

		JButton loginButton = new JButton("Login");

		fieldContainer.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		fieldContainer.add(emailLabel,gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		fieldContainer.add(emailField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		fieldContainer.add(passwordLabel,gbc);
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
		loginContainer.add(loginButton, gbc);


		gbc.insets = new Insets(0,0,0,0);


		setIconImage(new ImageIcon("src/resources/images/logo_1.png").getImage());
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

}

