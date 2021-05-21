package com.attendance_manager.pages.subject;

import com.attendance_manager.components.styles.ColorTheme;
import com.attendance_manager.components.styles.GothamFont;


import javax.swing.*;
import java.awt.*;


import static com.attendance_manager.pages.Homepage.fill;

public class SubjectStats extends JDialog {

    GridBagConstraints gbc = new GridBagConstraints();
    GridBagConstraints gbcL = new GridBagConstraints();

    public SubjectStats(String _subject,int _totalClasses,int _attendedClasses,int _missedClasses,int _safeBunks) {

        ColorTheme colorTheme = new ColorTheme();
        GothamFont gothamFont = new GothamFont();

        float attendancePercentage = 100*((float)_attendedClasses/_totalClasses);
        double roundedPercent=Math.round(attendancePercentage*100.0)/100.0;

        if (Float.isNaN(attendancePercentage)) {
            attendancePercentage = 0;
            roundedPercent=0.0;
        }
        System.out.println("Attendance percentage = " + roundedPercent);

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
        progressPane.setBackground(colorTheme.getLightTransColor());
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

        JLabel nameLabel = new JLabel("Total number of classes:");
        nameLabel.setFont(gothamFont.assignFont("GothamBook", 14f));
        nameLabel.setForeground(colorTheme.getTextColor());

        JLabel nameDataLabel = new JLabel(Integer.toString(_totalClasses));
        nameDataLabel.setFont(gothamFont.assignFont("GothamBook", 14f));
        nameDataLabel.setForeground(colorTheme.getTextColor());

        JLabel yearLabel = new JLabel("Number of classes attended:");
        yearLabel.setFont(gothamFont.assignFont("GothamBook", 14f));
        yearLabel.setForeground(colorTheme.getTextColor());

        JLabel yearDataLabel = new JLabel(Integer.toString(_attendedClasses));
        yearDataLabel.setFont(gothamFont.assignFont("GothamBook", 14f));
        yearDataLabel.setForeground(colorTheme.getTextColor());

        JLabel collegeLabel = new JLabel("Number of classes absent: ");
        collegeLabel.setFont(gothamFont.assignFont("GothamBook", 14f));
        collegeLabel.setForeground(colorTheme.getTextColor());

        JLabel collegeDataLabel = new JLabel(Integer.toString(_missedClasses));
        collegeDataLabel.setFont(gothamFont.assignFont("GothamBook", 14f));
        collegeDataLabel.setForeground(colorTheme.getTextColor());

        JLabel percentLabel = new JLabel((_safeBunks>=0)?"Number of safe bunks more: ":"Number of classes to get back on track: ");
        percentLabel.setFont(gothamFont.assignFont("GothamBook", 14f));
        percentLabel.setForeground(colorTheme.getTextColor());

        JLabel percentageDataLabel = new JLabel((_safeBunks>=0)?Integer.toString(_safeBunks):Integer.toString(_safeBunks*-1));
        percentageDataLabel.setFont(gothamFont.assignFont("GothamBook", 14f));
        percentageDataLabel.setForeground(colorTheme.getTextColor());

        JPanel dataContainer = new JPanel();
        dataContainer.setPreferredSize(new Dimension(350, 300));
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
        setSize(800, 450);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int w = getSize().width;
        int h = getSize().height;

        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;

        setLocation(x, y);

        setContentPane(new JLabel(new ImageIcon("src/resources/images/background.jpg")));

        gbc.insets = new Insets(0,0,0,0);

        setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(progressPane, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(dataContainer, gbc);

    }
}
