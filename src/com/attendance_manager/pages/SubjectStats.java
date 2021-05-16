package com.attendance_manager.pages;

import com.attendance_manager.components.ColorTheme;
import com.attendance_manager.components.GothamFont;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class SubjectStats extends JDialog {

    GridBagConstraints gbc = new GridBagConstraints();
    GridBagConstraints gbcL = new GridBagConstraints();
//    int totalClasses,attendedClasses,missedClasses,safeBunks;

    public SubjectStats(String _subject,int _totalClasses,int _attendedClasses,int _missedClasses,int _safeBunks) {

        ColorTheme colorTheme = new ColorTheme();
        GothamFont gothamFont = new GothamFont();

//        totalClasses=_totalClasses;
//        attendedClasses=_attendedClasses;
//        missedClasses=_missedClasses;
//        safeBunks=_safeBunks;
        float attendance=100*((float)_attendedClasses/_totalClasses);
        System.out.println(attendance);

        BufferedImage profile = null;
        Image resizedProfile = null;
        JLabel profileLabel = new JLabel();



        JLabel nameLabel = new JLabel("Total Classes:");
        nameLabel.setFont(gothamFont.assignFont("GothamBold", 18f));
        nameLabel.setForeground(colorTheme.getTextColor());

        JLabel nameDataLabel = new JLabel(Integer.toString(_totalClasses));
        nameDataLabel.setFont(gothamFont.assignFont("GothamBook", 18f));
        nameDataLabel.setForeground(colorTheme.getTextColor());

        JLabel yearLabel = new JLabel("Attended Classes:");
        yearLabel.setFont(gothamFont.assignFont("GothamBold", 18f));
        yearLabel.setForeground(colorTheme.getTextColor());

        JLabel yearDataLabel = new JLabel(Integer.toString(_attendedClasses));
        yearDataLabel.setFont(gothamFont.assignFont("GothamBook", 18f));
        yearDataLabel.setForeground(colorTheme.getTextColor());

        JLabel collegeLabel = new JLabel("Missed Classes:");
        collegeLabel.setFont(gothamFont.assignFont("GothamBold", 18f));
        collegeLabel.setForeground(colorTheme.getTextColor());

        JLabel collegeDataLabel = new JLabel(Integer.toString(_missedClasses));
        collegeDataLabel.setFont(gothamFont.assignFont("GothamBook", 18f));
        collegeDataLabel.setForeground(colorTheme.getTextColor());

        JLabel percentLabel = new JLabel((_safeBunks>=0)?"Safebunks Available: ":"Attend Next: ");
        percentLabel.setFont(gothamFont.assignFont("GothamBold", 18f));
        percentLabel.setForeground(colorTheme.getTextColor());


        JLabel percentageDataLabel = new JLabel((_safeBunks>=0)?Integer.toString(_safeBunks):Integer.toString(_safeBunks*-1));
        percentageDataLabel.setFont(gothamFont.assignFont("GothamBook", 18f));
        percentageDataLabel.setForeground(colorTheme.getTextColor());

        JPanel dataContainer = new JPanel();
        dataContainer.setPreferredSize(new Dimension(350, 350));
        dataContainer.setBackground(colorTheme.getLightTransColor());

        gbcL.insets = new Insets(10,10,10,10);

        dataContainer.setLayout(new GridBagLayout());
        gbcL.gridx = 0;
        gbcL.gridy = 0;
        gbcL.anchor = GridBagConstraints.WEST;
        dataContainer.add(nameLabel, gbcL);
        gbcL.gridx = 1;
        gbcL.gridy = 0;
        gbcL.anchor = GridBagConstraints.WEST;
        dataContainer.add(nameDataLabel, gbcL);
        gbcL.gridx = 0;
        gbcL.gridy = 1;
        gbcL.anchor = GridBagConstraints.WEST;
        dataContainer.add(yearLabel, gbcL);
        gbcL.gridx = 1;
        gbcL.gridy = 1;
        gbcL.anchor = GridBagConstraints.WEST;
        dataContainer.add(yearDataLabel, gbcL);
        gbcL.gridx = 0;
        gbcL.gridy = 2;
        gbcL.anchor = GridBagConstraints.WEST;
        dataContainer.add(collegeLabel, gbcL);
        gbcL.gridx = 1;
        gbcL.gridy = 2;
        gbcL.anchor = GridBagConstraints.WEST;
        dataContainer.add(collegeDataLabel, gbcL);


        gbcL.gridx = 0;
        gbcL.gridy = 3;
        gbcL.anchor = GridBagConstraints.WEST;
        dataContainer.add(percentLabel, gbcL);
        gbcL.gridx = 1;
        gbcL.gridy = 3;
        gbcL.anchor = GridBagConstraints.WEST;
        dataContainer.add(percentageDataLabel, gbcL);



        setIconImage(new ImageIcon("src/resources/images/spotify.png").getImage());
        setTitle(_subject);
        setVisible(true);
        setSize(700, 350);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int w = getSize().width;
        int h = getSize().height;

        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;

        setLocation(x, y);

        setContentPane(new JLabel(new ImageIcon("src/resources/images/background.jpg")));

        gbc.insets = new Insets(10,10,10,10);

        setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(profileLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(dataContainer, gbc);

    }
}
