package com.attendance_manager.pages;

import com.attendance_manager.components.ColorTheme;
import com.attendance_manager.components.CustomBorder;
import com.attendance_manager.components.GothamFont;
import com.attendance_manager.components.RoundedBorder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Homepage extends JFrame {

    GridBagConstraints gbc = new GridBagConstraints();
    ColorTheme colorTheme=new ColorTheme();
    GothamFont gothamFont = new GothamFont();
    CustomBorder customBorder = new CustomBorder();


    public Homepage() {

        float attendancePercentage = 90;

        BufferedImage logo = null;
        Image resizedLogo = null;
        JLabel logoLabel = new JLabel();

        gbc.insets = new Insets(0,0,0,0);

        try{
            logo = ImageIO.read(new File("src/resources/images/spotify.png"));
            resizedLogo = logo.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            logoLabel = new JLabel(new ImageIcon(resizedLogo));
        }
        catch (Exception e) {
            System.out.print("Image not found!");
        }

        JLabel appTitle = new JLabel("Attendance Manager");
        appTitle.setFont(gothamFont.assignFont("GothamBold", 20f));
        appTitle.setForeground(new ColorTheme().getAccColorLight());

        JPanel logoContainer = new JPanel();
        logoContainer.setBackground(colorTheme.getLightTransColor());
        logoContainer.setPreferredSize(new Dimension(300, 150));

        gbc.insets = new Insets(10,10,10,10);


        logoContainer.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        logoContainer.add(logoLabel,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        logoContainer.add(appTitle,gbc);

        gbc.insets = new Insets(0,0,0,0);


        JPanel greetingsContainer = new JPanel();
        greetingsContainer.setBackground(colorTheme.getDarkTransColor());
        greetingsContainer.setPreferredSize(new Dimension(850, 150));

        JLabel greetings = new JLabel("Hi Umar!");
        greetings.setFont(gothamFont.assignFont("GothamMedium", 30f));
        greetings.setForeground(colorTheme.getTextColor());

        greetingsContainer.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        greetingsContainer.add(greetings, gbc);

        JButton addSubjectBtn = new JButton("Add Subject");
        addSubjectBtn.setFont(gothamFont.assignFont("GothamMedium", 14f));
        addSubjectBtn.setPreferredSize(new Dimension(300, 40));
        addSubjectBtn.setBackground(Color.black);
        addSubjectBtn.setForeground(colorTheme.getTextColor());
        addSubjectBtn.setBorder(BorderFactory.createLineBorder(colorTheme.getAccColorLight(), 1));

        JButton removeSubjectBtn = new JButton("Remove Subject");
        removeSubjectBtn.setFont(gothamFont.assignFont("GothamMedium", 14f));
        removeSubjectBtn.setPreferredSize(new Dimension(300, 40));
        removeSubjectBtn.setBackground(Color.black);
        removeSubjectBtn.setForeground(colorTheme.getTextColor());
        removeSubjectBtn.setBorder(BorderFactory.createLineBorder(colorTheme.getAccColorLight(), 1));

        JButton historyBtn = new JButton("History");
        historyBtn.setFont(gothamFont.assignFont("GothamMedium", 14f));
        historyBtn.setPreferredSize(new Dimension(300,40));
        historyBtn.setBackground(Color.black);
        historyBtn.setForeground(colorTheme.getTextColor());
        historyBtn.setBorder(BorderFactory.createLineBorder(colorTheme.getAccColorLight(), 1));

        JButton addBtn = new JButton("Add Attendance");
        addBtn.setFont(gothamFont.assignFont("GothamMedium", 14f));
        addBtn.setPreferredSize(new Dimension(300,40));
        addBtn.setBackground(Color.black);
        addBtn.setForeground(colorTheme.getTextColor());
        addBtn.setBorder(BorderFactory.createLineBorder(colorTheme.getAccColorLight(), 1));

        JButton addTableBtn = new JButton("Add Timetable");
        addTableBtn.setFont(gothamFont.assignFont("GothamMedium", 14f));
        addTableBtn.setPreferredSize(new Dimension(300,40));
        addTableBtn.setBackground(Color.black);
        addTableBtn.setForeground(colorTheme.getTextColor());
        addTableBtn.setBorder(BorderFactory.createLineBorder(colorTheme.getAccColorLight(), 1));

        JButton viewTableBtn = new JButton("View Timetable");
        viewTableBtn.setFont(gothamFont.assignFont("GothamMedium", 14f));
        viewTableBtn.setPreferredSize(new Dimension(300,40));
        viewTableBtn.setBackground(Color.black);
        viewTableBtn.setForeground(colorTheme.getTextColor());
        viewTableBtn.setBorder(BorderFactory.createLineBorder(colorTheme.getAccColorLight(), 1));


        JButton generateBtn = new JButton("Generate Leave Form");
        generateBtn.setFont(gothamFont.assignFont("GothamMedium", 14f));
        generateBtn.setPreferredSize(new Dimension(300,40));
        generateBtn.setBackground(Color.black);
        generateBtn.setForeground(colorTheme.getTextColor());
        generateBtn.setBorder(BorderFactory.createLineBorder(colorTheme.getAccColorLight(), 1));

        JButton holidaysBtn = new JButton("Public Holidays");
        holidaysBtn.setFont(gothamFont.assignFont("GothamMedium", 14f));
        holidaysBtn.setPreferredSize(new Dimension(300,40));
        holidaysBtn.setBackground(Color.black);
        holidaysBtn.setForeground(colorTheme.getTextColor());
        holidaysBtn.setBorder(BorderFactory.createLineBorder(colorTheme.getAccColorLight(), 1));

        JButton bioBtn = new JButton("Bio");
        bioBtn.setFont(gothamFont.assignFont("GothamMedium", 14f));
        bioBtn.setPreferredSize(new Dimension(300,40));
        bioBtn.setBackground(Color.black);
        bioBtn.setForeground(colorTheme.getTextColor());
        bioBtn.setBorder(BorderFactory.createLineBorder(colorTheme.getAccColorLight(), 1));

        JPanel menuPane = new JPanel();
        menuPane.setBackground(colorTheme.getDarkTransColor());
        menuPane.setPreferredSize(new Dimension(300, 400));

        menuPane.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        menuPane.add(addSubjectBtn, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        menuPane.add(removeSubjectBtn, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        menuPane.add(historyBtn,gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        menuPane.add(addBtn,gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        menuPane.add(addTableBtn,gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        menuPane.add(viewTableBtn, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        menuPane.add(generateBtn, gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        menuPane.add(holidaysBtn, gbc);
        gbc.gridx = 0;
        gbc.gridy = 8;
        menuPane.add(bioBtn, gbc);

        JLabel attendanceTitle = new JLabel("Attendance Percentage");
        attendanceTitle.setFont(gothamFont.assignFont("GothamBold", 20f));
        attendanceTitle.setForeground(colorTheme.getTextColor());

        JLabel attendancePercentLabel = new JLabel(Float.toString(attendancePercentage)+" %");
        attendancePercentLabel.setFont(gothamFont.assignFont("GothamBold", 40f));
        attendancePercentLabel.setForeground(colorTheme.getAccColorLight());

        JProgressBar attendancePercentBar = new JProgressBar();
        attendancePercentBar.setValue(0);
        attendancePercentBar.setStringPainted(true);
        attendancePercentBar.setForeground(colorTheme.getAccColorLight());
        attendancePercentBar.setPreferredSize(new Dimension(300, 30));


        JPanel progressPane = new JPanel();
        progressPane.setBackground(colorTheme.getDarkTransColor());
        progressPane.setPreferredSize(new Dimension(375, 300));

        gbc.insets = new Insets(20,0,30,0);

        progressPane.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        progressPane.add(attendanceTitle, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        progressPane.add(attendancePercentLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        progressPane.add(attendancePercentBar, gbc);

        fill(attendancePercentBar, attendancePercentage);

        gbc.insets = new Insets(0,0,0,0);

        JLabel stat1 = new JLabel("Total number of classes: ");
        stat1.setFont(gothamFont.assignFont("GothamBook", 14f));
        stat1.setForeground(colorTheme.getTextColor());
        JLabel stat2 = new JLabel("Number of classes attended: ");
        stat2.setFont(gothamFont.assignFont("GothamBook", 14f));
        stat2.setForeground(colorTheme.getTextColor());
        JLabel stat3 = new JLabel("Number of classes absent: ");
        stat3.setFont(gothamFont.assignFont("GothamBook", 14f));
        stat3.setForeground(colorTheme.getTextColor());
        JLabel stat4 = new JLabel("Number of safe bunks more: ");
        stat4.setFont(gothamFont.assignFont("GothamBook", 14f));
        stat4.setForeground(colorTheme.getTextColor());

        JPanel statPane = new JPanel();
        statPane.setBackground(colorTheme.getDarkTransColor());
        statPane.setPreferredSize(new Dimension(475, 300));

        gbc.insets = new Insets(10,0,10,0);


        statPane.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        statPane.add(stat1, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        statPane.add(stat2, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        statPane.add(stat3, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        statPane.add(stat4, gbc);

        gbc.insets = new Insets(0,0,0,0);


        JPanel mainContentPane = new JPanel();
        mainContentPane.setBackground(colorTheme.getDarkTransColor());
        mainContentPane.setPreferredSize(new Dimension(850, 300));

        mainContentPane.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainContentPane.add(progressPane, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainContentPane.add(statPane, gbc);

        JRadioButton rbtn1 = new JRadioButton("Maths");
        rbtn1.setBackground(colorTheme.getRounderCornerColor());
        rbtn1.setForeground(colorTheme.getTextColor());
        rbtn1.setFont(gothamFont.assignFont("GothamMedium", 14f));

        JRadioButton rbtn2 = new JRadioButton("Science");
        rbtn2.setBackground(colorTheme.getRounderCornerColor());
        rbtn2.setForeground(colorTheme.getTextColor());
        rbtn2.setFont(gothamFont.assignFont("GothamMedium", 14f));

        JPanel rbtnPane = new JPanel();
        rbtnPane.setBackground(colorTheme.getLightTransColor());
        rbtnPane.setPreferredSize(new Dimension(600, 100));

        gbc.insets = new Insets(0,10,0,10);

        rbtnPane.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        rbtnPane.add(rbtn1, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        rbtnPane.add(rbtn2, gbc);

        gbc.insets = new Insets(0,0,0,0);


        JButton resultBtn = new JButton("Get Result");
        resultBtn.setBorder(new RoundedBorder(20, colorTheme.getRounderCornerColor()));
        resultBtn.setFont(gothamFont.assignFont("GothamBold", 15f));
        resultBtn.setBackground(colorTheme.getAccColorLight());
        resultBtn.setForeground(colorTheme.getTextColor());

        JPanel resultBtnPane = new JPanel();
        resultBtnPane.setBackground(colorTheme.getLightTransColor());
        resultBtnPane.setPreferredSize(new Dimension(250, 100));
        resultBtnPane.setBorder(customBorder.assignBorder(Color.black, 0 , 20, 10, 0, 10));


        resultBtnPane.add(resultBtn);

        JPanel radioBtnPane = new JPanel();
        radioBtnPane.setBackground(colorTheme.getDarkTransColor());
        radioBtnPane.setPreferredSize(new Dimension(850, 100));

        radioBtnPane.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        radioBtnPane.add(rbtnPane);
        gbc.gridx = 1;
        gbc.gridy = 0;
        radioBtnPane.add(resultBtnPane);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(colorTheme.getLightTransColor());
        contentPane.setPreferredSize(new Dimension(850, 400));

        contentPane.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(mainContentPane, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(radioBtnPane, gbc);

        setIconImage(new ImageIcon("src/resources/images/spotify.png").getImage());
        setTitle("Attendance Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(new JLabel(new ImageIcon("src/resources/images/background.jpg")));

        setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(logoContainer,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(greetingsContainer,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(menuPane,gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(contentPane,gbc);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    public static void fill(JProgressBar attendancePercentBar, float attendancePercentage)
    {
        int i = 0;
        try {
            while (i <= 100) {
                attendancePercentBar.setValue((int)attendancePercentage);
                Thread.sleep(1000);
                i += 20;
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
