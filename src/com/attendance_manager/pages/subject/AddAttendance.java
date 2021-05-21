package com.attendance_manager.pages.subject;

import com.attendance_manager.components.UIcomponents.SubjectSlot;
import com.attendance_manager.components.styles.ColorTheme;
import com.attendance_manager.components.styles.GothamFont;
import com.attendance_manager.services.DBHandler;
import models.Subject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class AddAttendance extends JFrame {
    GridBagConstraints gbc = new GridBagConstraints();
    DBHandler db=new DBHandler();
    public AddAttendance() {

        BufferedImage logo ;
        Image resizedLogo ;
        JLabel logoLabel = new JLabel();
        GothamFont gothamFont = new GothamFont();
        ColorTheme colorTheme = new ColorTheme();

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

        ArrayList<Subject> subjectsList=db.fetchSubjects();

        ArrayList<SubjectSlot> subjectSlots= new ArrayList<>();
        for (Subject s : subjectsList) {
            subjectSlots.add(new SubjectSlot(s,this));
        }

        JPanel attendanceContainer = new JPanel();
        attendanceContainer.setBackground(colorTheme.getLightTransColor());
        attendanceContainer.setPreferredSize(new Dimension(540, 500));

        attendanceContainer.setLayout(new GridBagLayout());

        for(int i=0;i<subjectsList.size();i++){
            gbc.gridx = 0;
            gbc.gridy = i;
            attendanceContainer.add(subjectSlots.get(i).generateSlot(), gbc);
        }


        setIconImage(new ImageIcon("src/resources/images/spotify.png").getImage());
        setTitle("Add Attendance");
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
