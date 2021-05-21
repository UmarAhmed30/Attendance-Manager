package com.attendance_manager.pages;

import com.attendance_manager.components.UIcomponents.CustomRadioButton;
import com.attendance_manager.components.styles.ColorTheme;
import com.attendance_manager.components.styles.CustomBorder;
import com.attendance_manager.components.styles.GothamFont;
import com.attendance_manager.components.styles.RoundedBorder;
import com.attendance_manager.pages.bio.Bio;
import com.attendance_manager.pages.holidaysAPI.PublicHolidays;
import com.attendance_manager.pages.leaveForm.GenerateLeaveForm;
import com.attendance_manager.pages.subject.AddAttendance;
import com.attendance_manager.pages.subject.AddSubject;
import com.attendance_manager.pages.subject.History;
import com.attendance_manager.pages.subject.RemoveSubject;
import com.attendance_manager.pages.timeTable.AddTimetable;
import com.attendance_manager.pages.timeTable.ViewTT;
import com.attendance_manager.services.DBHandler;
import models.BioData;
import models.Subject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Homepage extends JFrame {

    GridBagConstraints gbc = new GridBagConstraints();
    ColorTheme colorTheme = new ColorTheme();
    GothamFont gothamFont = new GothamFont();
    CustomBorder customBorder = new CustomBorder();
    DBHandler db = new DBHandler();

    public Homepage() {

        float attendancePercentage;
        double roundedPercent;
        String username;


        username=db.getBio().getName();


        BufferedImage logo;
        Image resizedLogo;
        JLabel logoLabel = new JLabel();

        gbc.insets = new Insets(0, 0, 0, 0);

        try {
            logo = ImageIO.read(new File("src/resources/images/spotify.png"));
            resizedLogo = logo.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            logoLabel = new JLabel(new ImageIcon(resizedLogo));
        } catch (Exception e) {
            System.out.print("Image not found!");
        }

        JLabel appTitle = new JLabel("Attendance Manager");
        appTitle.setFont(gothamFont.assignFont("GothamBold", 20f));
        appTitle.setForeground(new ColorTheme().getAccColorLight());

        JPanel logoContainer = new JPanel();
        logoContainer.setBackground(colorTheme.getLightTransColor());
        logoContainer.setPreferredSize(new Dimension(300, 150));

        gbc.insets = new Insets(10, 10, 10, 10);

        logoContainer.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        logoContainer.add(logoLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        logoContainer.add(appTitle, gbc);

        gbc.insets = new Insets(0, 0, 0, 0);


        JPanel greetingsContainer = new JPanel();
        greetingsContainer.setBackground(colorTheme.getDarkTransColor());
        greetingsContainer.setPreferredSize(new Dimension(850, 150));

        if(username==null) {username="";}

        JLabel greetings = new JLabel("Hey "+username+"!");
        greetings.setFont(gothamFont.assignFont("GothamMedium", 30f));
        greetings.setForeground(colorTheme.getTextColor());

        greetingsContainer.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        greetingsContainer.add(greetings, gbc);

        BufferedImage icon1 = null;
        Image resizedIcon1 = null;
        JLabel iconLabel1 = null;

        try {
            icon1 = ImageIO.read(new File("src/resources/images/AddSubject.png"));
            resizedIcon1 = icon1.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            iconLabel1 = new JLabel(new ImageIcon(resizedIcon1));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JButton addSubjectBtn = new JButton("Add Subject");
        addSubjectBtn.setFont(gothamFont.assignFont("GothamMedium", 14f));
        addSubjectBtn.setPreferredSize(new Dimension(260, 40));
        addSubjectBtn.setBackground(Color.black);
        addSubjectBtn.setForeground(colorTheme.getTextColor());
        addSubjectBtn.setBorder(BorderFactory.createLineBorder(colorTheme.getAccColorLight(), 1));

        JPanel btnIconPane1 = new JPanel();
        btnIconPane1.setBackground(Color.black);
        btnIconPane1.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        btnIconPane1.add(iconLabel1, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        btnIconPane1.add(addSubjectBtn, gbc);

        BufferedImage icon2 = null;
        Image resizedIcon2 = null;
        JLabel iconLabel2 = null;

        try {
            icon2 = ImageIO.read(new File("src/resources/images/RemoveSubject.png"));
            resizedIcon2 = icon2.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            iconLabel2 = new JLabel(new ImageIcon(resizedIcon2));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JButton removeSubjectBtn = new JButton("Remove Subject");
        removeSubjectBtn.setFont(gothamFont.assignFont("GothamMedium", 14f));
        removeSubjectBtn.setPreferredSize(new Dimension(260, 40));
        removeSubjectBtn.setBackground(Color.black);
        removeSubjectBtn.setForeground(colorTheme.getTextColor());
        removeSubjectBtn.setBorder(BorderFactory.createLineBorder(colorTheme.getAccColorLight(), 1));

        JPanel btnIconPane2 = new JPanel();
        btnIconPane2.setBackground(Color.black);
        btnIconPane2.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        btnIconPane2.add(iconLabel2, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        btnIconPane2.add(removeSubjectBtn, gbc);

        BufferedImage icon3 = null;
        Image resizedIcon3 = null;
        JLabel iconLabel3 = null;

        try {
            icon3 = ImageIO.read(new File("src/resources/images/History.png"));
            resizedIcon3 = icon3.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            iconLabel3 = new JLabel(new ImageIcon(resizedIcon3));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JButton historyBtn = new JButton("History");
        historyBtn.setFont(gothamFont.assignFont("GothamMedium", 14f));
        historyBtn.setPreferredSize(new Dimension(260, 40));
        historyBtn.setBackground(Color.black);
        historyBtn.setForeground(colorTheme.getTextColor());
        historyBtn.setBorder(BorderFactory.createLineBorder(colorTheme.getAccColorLight(), 1));

        JPanel btnIconPane3 = new JPanel();
        btnIconPane3.setBackground(Color.black);
        btnIconPane3.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        btnIconPane3.add(iconLabel3, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        btnIconPane3.add(historyBtn, gbc);

        BufferedImage icon4 = null;
        Image resizedIcon4 = null;
        JLabel iconLabel4 = null;

        try {
            icon4 = ImageIO.read(new File("src/resources/images/AddAttendance.png"));
            resizedIcon4 = icon4.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            iconLabel4 = new JLabel(new ImageIcon(resizedIcon4));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JButton addBtn = new JButton("Add Attendance");
        addBtn.setFont(gothamFont.assignFont("GothamMedium", 14f));
        addBtn.setPreferredSize(new Dimension(260, 40));
        addBtn.setBackground(Color.black);
        addBtn.setForeground(colorTheme.getTextColor());
        addBtn.setBorder(BorderFactory.createLineBorder(colorTheme.getAccColorLight(), 1));

        JPanel btnIconPane4 = new JPanel();
        btnIconPane4.setBackground(Color.black);
        btnIconPane4.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        btnIconPane4.add(iconLabel4, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        btnIconPane4.add(addBtn, gbc);

        BufferedImage icon5 = null;
        Image resizedIcon5 = null;
        JLabel iconLabel5 = null;

        try {
            icon5 = ImageIO.read(new File("src/resources/images/AddTimetable.png"));
            resizedIcon5 = icon5.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            iconLabel5 = new JLabel(new ImageIcon(resizedIcon5));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JButton addTableBtn = new JButton("Add Timetable");
        addTableBtn.setFont(gothamFont.assignFont("GothamMedium", 14f));
        addTableBtn.setPreferredSize(new Dimension(260, 40));
        addTableBtn.setBackground(Color.black);
        addTableBtn.setForeground(colorTheme.getTextColor());
        addTableBtn.setBorder(BorderFactory.createLineBorder(colorTheme.getAccColorLight(), 1));

        JPanel btnIconPane5 = new JPanel();
        btnIconPane5.setBackground(Color.black);
        btnIconPane5.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        btnIconPane5.add(iconLabel5, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        btnIconPane5.add(addTableBtn, gbc);

        BufferedImage icon6 = null;
        Image resizedIcon6 = null;
        JLabel iconLabel6 = null;

        try {
            icon6 = ImageIO.read(new File("src/resources/images/ViewTimetable.png"));
            resizedIcon6 = icon6.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            iconLabel6 = new JLabel(new ImageIcon(resizedIcon6));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JButton viewTableBtn = new JButton("View Timetable");
        viewTableBtn.setFont(gothamFont.assignFont("GothamMedium", 14f));
        viewTableBtn.setPreferredSize(new Dimension(260, 40));
        viewTableBtn.setBackground(Color.black);
        viewTableBtn.setForeground(colorTheme.getTextColor());
        viewTableBtn.setBorder(BorderFactory.createLineBorder(colorTheme.getAccColorLight(), 1));

        JPanel btnIconPane6 = new JPanel();
        btnIconPane6.setBackground(Color.black);
        btnIconPane6.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        btnIconPane6.add(iconLabel6, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        btnIconPane6.add(viewTableBtn, gbc);

        BufferedImage icon7 = null;
        Image resizedIcon7 = null;
        JLabel iconLabel7 = null;

        try {
            icon7 = ImageIO.read(new File("src/resources/images/LeaveForm.png"));
            resizedIcon7 = icon7.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            iconLabel7 = new JLabel(new ImageIcon(resizedIcon7));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JButton generateBtn = new JButton("Generate Leave Form");
        generateBtn.setFont(gothamFont.assignFont("GothamMedium", 14f));
        generateBtn.setPreferredSize(new Dimension(260, 40));
        generateBtn.setBackground(Color.black);
        generateBtn.setForeground(colorTheme.getTextColor());
        generateBtn.setBorder(BorderFactory.createLineBorder(colorTheme.getAccColorLight(), 1));

        JPanel btnIconPane7 = new JPanel();
        btnIconPane7.setBackground(Color.black);
        btnIconPane7.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        btnIconPane7.add(iconLabel7, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        btnIconPane7.add(generateBtn, gbc);

        BufferedImage icon8 = null;
        Image resizedIcon8 = null;
        JLabel iconLabel8 = null;

        try {
            icon8 = ImageIO.read(new File("src/resources/images/Holidays.png"));
            resizedIcon8 = icon8.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            iconLabel8 = new JLabel(new ImageIcon(resizedIcon8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JButton holidaysBtn = new JButton("Public Holidays");
        holidaysBtn.setFont(gothamFont.assignFont("GothamMedium", 14f));
        holidaysBtn.setPreferredSize(new Dimension(260, 40));
        holidaysBtn.setBackground(Color.black);
        holidaysBtn.setForeground(colorTheme.getTextColor());
        holidaysBtn.setBorder(BorderFactory.createLineBorder(colorTheme.getAccColorLight(), 1));

        JPanel btnIconPane8 = new JPanel();
        btnIconPane8.setBackground(Color.black);
        btnIconPane8.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        btnIconPane8.add(iconLabel8, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        btnIconPane8.add(holidaysBtn, gbc);

        BufferedImage icon9 = null;
        Image resizedIcon9 = null;
        JLabel iconLabel9 = null;

        try {
            icon9 = ImageIO.read(new File("src/resources/images/Bio.png"));
            resizedIcon9 = icon9.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            iconLabel9 = new JLabel(new ImageIcon(resizedIcon9));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JButton bioBtn = new JButton("Bio");
        bioBtn.setFont(gothamFont.assignFont("GothamMedium", 14f));
        bioBtn.setPreferredSize(new Dimension(260, 40));
        bioBtn.setBackground(Color.black);
        bioBtn.setForeground(colorTheme.getTextColor());
        bioBtn.setBorder(BorderFactory.createLineBorder(colorTheme.getAccColorLight(), 1));

        JPanel btnIconPane9 = new JPanel();
        btnIconPane9.setBackground(Color.black);
        btnIconPane9.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        btnIconPane9.add(iconLabel9, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        btnIconPane9.add(bioBtn, gbc);

        JPanel menuPane = new JPanel();
        menuPane.setBackground(colorTheme.getDarkTransColor());
        menuPane.setPreferredSize(new Dimension(300, 400));

        gbc.insets = new Insets(2, 0, 2, 0);

        menuPane.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        menuPane.add(btnIconPane1, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        menuPane.add(btnIconPane2, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        menuPane.add(btnIconPane3, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        menuPane.add(btnIconPane4, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        menuPane.add(btnIconPane5, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        menuPane.add(btnIconPane6, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        menuPane.add(btnIconPane7, gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        menuPane.add(btnIconPane8, gbc);
        gbc.gridx = 0;
        gbc.gridy = 8;
        menuPane.add(btnIconPane9, gbc);

        gbc.insets = new Insets(0, 10, 0, 10);


        ArrayList<Integer> stats = db.getStats();
        try {
            attendancePercentage = ((float) stats.get(1) / stats.get(0)) * 100;
            roundedPercent=Math.round(attendancePercentage*100.0)/100.0;
        } catch (Exception e) {
            attendancePercentage = 0;
            roundedPercent=0.0;
        }

        if (Float.isNaN(attendancePercentage)) {
            attendancePercentage = 0;
            roundedPercent=0.0;
        }
        System.out.println("Attendance percentage = " + attendancePercentage);

        JLabel attendanceTitle = new JLabel("Attendance Percentage");
        attendanceTitle.setFont(gothamFont.assignFont("GothamBold", 20f));
        attendanceTitle.setForeground(colorTheme.getTextColor());

        JLabel attendancePercentLabel = new JLabel(Double.toString(roundedPercent) + " %");
        attendancePercentLabel.setFont(gothamFont.assignFont("GothamBold", 40f));
        attendancePercentLabel.setForeground((attendancePercentage > 75) ? colorTheme.getAccColorLight() : Color.RED);

        JProgressBar attendancePercentBar = new JProgressBar();
        attendancePercentBar.setValue(0);
        attendancePercentBar.setStringPainted(true);
        attendancePercentBar.setForeground((attendancePercentage > 75) ? colorTheme.getAccColorLight() : Color.RED);
        attendancePercentBar.setPreferredSize(new Dimension(300, 30));

        JPanel progressPane = new JPanel();
        progressPane.setBackground(colorTheme.getDarkTransColor());
        progressPane.setPreferredSize(new Dimension(375, 300));

        gbc.insets = new Insets(20, 0, 30, 0);

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

        gbc.insets = new Insets(0, 0, 0, 0);

        String bunkOrAttend = "";
        int safeClasses = 0;
        int attendedClasses = 0;
        int totalClasses = 0;
        int missedClasses = 0;

        try {
            bunkOrAttend = (stats.get(2) > 0) ? "Number of safe bunks more: " : "Number of classes to get back on track: ";
            safeClasses = (stats.get(2) > 0) ? stats.get(2) : stats.get(2) * (-1);
            totalClasses = stats.get(0);
            attendedClasses = stats.get(1);
            missedClasses = (stats.get(0) - stats.get(1));
        } catch (Exception e) {
            System.out.println("First Time User !");
        }

        JLabel stat1 = new JLabel("Total number of classes: " + totalClasses);
        stat1.setFont(gothamFont.assignFont("GothamBook", 14f));
        stat1.setForeground(colorTheme.getTextColor());
        JLabel stat2 = new JLabel("Number of classes attended: " + attendedClasses);
        stat2.setFont(gothamFont.assignFont("GothamBook", 14f));
        stat2.setForeground(colorTheme.getTextColor());
        JLabel stat3 = new JLabel("Number of classes absent: " + missedClasses);
        stat3.setFont(gothamFont.assignFont("GothamBook", 14f));
        stat3.setForeground(colorTheme.getTextColor());
        JLabel stat4 = new JLabel(bunkOrAttend + " " + safeClasses);
        stat4.setFont(gothamFont.assignFont("GothamBook", 14f));
        stat4.setForeground(colorTheme.getTextColor());

        JPanel statPane = new JPanel();
        statPane.setBackground(colorTheme.getDarkTransColor());
        statPane.setPreferredSize(new Dimension(475, 300));

        gbc.insets = new Insets(10, 0, 10, 0);

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

        gbc.insets = new Insets(0, 0, 0, 0);

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

        ArrayList<Subject> subjectsList = db.fetchSubjects();
        ArrayList<CustomRadioButton> radioBtns = new ArrayList<>();

        for (Subject subject: subjectsList) {
            radioBtns.add(new CustomRadioButton(subject.getSubjectName()));
        }

        JPanel rbtnPane = new JPanel();
        rbtnPane.setBackground(colorTheme.getLightTransColor());
        rbtnPane.setPreferredSize(new Dimension(600, 100));

        gbc.insets = new Insets(0, 10, 0, 10);

        rbtnPane.setLayout(new GridBagLayout());

        for(int i=0;i<subjectsList.size();i++){
            gbc.gridx = i;
            gbc.gridy = 0;
            rbtnPane.add(radioBtns.get(i).generateButton(), gbc);
        }

        gbc.insets = new Insets(0, 0, 0, 0);


        JButton resultBtn = new JButton("Sign out");
        resultBtn.setBorder(new RoundedBorder(20, colorTheme.getRounderCornerColor()));
        resultBtn.setFont(gothamFont.assignFont("GothamBold", 15f));
        resultBtn.setBackground(colorTheme.getAccColorLight());
        resultBtn.setForeground(colorTheme.getTextColor());

        JPanel resultBtnPane = new JPanel();
        resultBtnPane.setBackground(colorTheme.getLightTransColor());
        resultBtnPane.setPreferredSize(new Dimension(250, 100));
        resultBtnPane.setBorder(customBorder.assignBorder(Color.black, 0, 20, 10, 0, 10));

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
        add(logoContainer, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(greetingsContainer, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(menuPane, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(contentPane, gbc);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        addSubjectBtn.addActionListener(e -> {
            System.out.println("Adding subject !");
            new AddSubject();
            dispose();
        });

        removeSubjectBtn.addActionListener(e -> {
            System.out.println("Removing subject !");
            new RemoveSubject();
            dispose();
        });

        historyBtn.addActionListener(e -> {
            System.out.println("Displaying History !");
            new History();
        });

        addBtn.addActionListener(e -> {
            System.out.println("Adding Attendance !");
            new AddAttendance();
            dispose();
        });

        generateBtn.addActionListener(e -> {
            System.out.println("Generating leave form !");
            new GenerateLeaveForm();
        });

        holidaysBtn.addActionListener(e -> new PublicHolidays());

        bioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Displaying Bio !");
                new Bio();
                dispose();
            }
        });

        addTableBtn.addActionListener(e -> {
            System.out.println("Add TT !");
            new AddTimetable();
        });

        viewTableBtn.addActionListener(e -> {
            System.out.println("View TT !");
            new ViewTT();
        });



    }

    public static void fill(JProgressBar attendancePercentBar, float attendancePercentage) {
        int i = 0;
        try {
            while (i <= 100) {
                attendancePercentBar.setValue((int) attendancePercentage);
                Thread.sleep(1000);
                i += 20;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
