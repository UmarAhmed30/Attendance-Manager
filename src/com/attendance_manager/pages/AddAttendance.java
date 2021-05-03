package com.attendance_manager.pages;

import com.attendance_manager.components.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class AddAttendance extends JFrame {
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagConstraints gbcL = new GridBagConstraints();

    public AddAttendance() {

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

        SubjectSlot sub1 = new SubjectSlot("Maths");
        SubjectSlot sub2 = new SubjectSlot("Science");
        SubjectSlot sub3 = new SubjectSlot("Social");

        JPanel attendanceContainer = new JPanel();
        attendanceContainer.setBackground(colorTheme.getLightTransColor());
        attendanceContainer.setPreferredSize(new Dimension(540, 500));

        attendanceContainer.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        attendanceContainer.add(sub1.generateSlot(), gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        attendanceContainer.add(sub2.generateSlot(), gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        attendanceContainer.add(sub3.generateSlot(), gbc);

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
        add(attendanceContainer,gbc);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
