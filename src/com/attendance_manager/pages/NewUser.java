package com.attendance_manager.pages;
import java.sql.*;
import com.attendance_manager.components.*;
import com.attendance_manager.components.ColorTheme;
import java.sql.DriverManager;
import javax.imageio.ImageIO;
import javax.swing.*;

import com.attendance_manager.services.DBHandler;
import org.springframework.security.crypto.bcrypt.BCrypt;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class NewUser extends JFrame {

    GridBagConstraints gbc = new GridBagConstraints();
    GridBagConstraints gbcL = new GridBagConstraints();
    ColorTheme colorTheme=new ColorTheme();

    public NewUser()
    {
        DBHandler db=new DBHandler();


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
        appTitle.setForeground(new ColorTheme().getAccColorLight());

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

        JLabel containerTitle = new JLabel("Register");
        containerTitle.setFont(gothamFont.assignFont("GothamMedium", 20f));
        containerTitle.setForeground(colorTheme.getTextColor());
        containerTitle.setBorder(customBorder.assignBorder(Color.black, 0 , 25, 10, 0, 10));

        JPanel fieldContainer = new JPanel();
        fieldContainer.setBackground(colorTheme.getLightTransColor());
        fieldContainer.setPreferredSize(new Dimension(500, 250));

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

        JLabel confirmpasswordLabel = new JLabel("Confirm Password");
        confirmpasswordLabel.setFont(gothamFont.assignFont("GothamBook", 14f));
        confirmpasswordLabel.setForeground(colorTheme.getTextColor());

        JTextField confirmpasswordField = new RoundedTextField(20);
        confirmpasswordField.setBorder(customBorder.assignBorder(Color.black, 1, 5, 10, 5, 0));
        confirmpasswordField.setFont(gothamFont.assignFont("GothamBook", 14f));

        JPanel loginContainer = new JPanel();
        loginContainer.setBackground(colorTheme.getLightTransColor());

        JLabel forgotPasswordLabel = new JLabel("Forgot Password?");
        forgotPasswordLabel.setFont(gothamFont.assignFont("GothamBook", 14f));
        forgotPasswordLabel.setForeground(colorTheme.getTextColor());

        JButton loginButton = new JButton("Register");
        loginButton.setBorder(new RoundedBorder(20, colorTheme.getRounderCornerColor()));
        loginButton.setFont(gothamFont.assignFont("GothamBold", 15f));
        loginButton.setBackground(colorTheme.getAccColorLight());
        loginButton.setForeground(colorTheme.getTextColor());

        JLabel signUpLabel = new JLabel("Already have an account? Login");
        signUpLabel.setFont(gothamFont.assignFont("GothamBold", 15f));
        signUpLabel.setForeground(colorTheme.getTextColor());
        signUpLabel.setBorder(customBorder.assignBorder(Color.black, 0 , 0, 10, 25, 10));

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputEmail = emailField.getText();
                String inputPassword = passwordField.getText();
                String inputConfirmPassword=confirmpasswordField.getText();
                if(inputPassword.equals(inputConfirmPassword) && isValid(inputEmail)){
                    System.out.println("Email is correct and passwords match!");

                    db.insertToDB(inputEmail,inputPassword);

                }
                else{
                    System.out.println("no match");
                }
                System.out.println(inputEmail+" "+inputPassword);

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
        gbcL.gridx = 0;
        gbcL.gridy = 2;
        gbcL.anchor = GridBagConstraints.WEST;
        fieldContainer.add(confirmpasswordLabel,gbcL);
        gbc.gridx=1;
        gbc.gridy=2;
        fieldContainer.add(confirmpasswordField,gbc);

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

    static boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}

