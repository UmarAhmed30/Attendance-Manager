package com.attendance_manager.components.UIcomponents;

import com.attendance_manager.components.styles.GothamFont;
import com.attendance_manager.components.styles.ColorTheme;
import com.attendance_manager.pages.subject.SubjectStats;
import com.attendance_manager.services.DBHandler;
import models.Subject;

import javax.swing.*;


public class CustomRadioButton {

    public String subject;

    ColorTheme colorTheme = new ColorTheme();
    GothamFont gothamFont = new GothamFont();

    public CustomRadioButton(String subject) {
        this.subject = subject;
    }

    public JRadioButton generateButton() {

        JRadioButton button = new JRadioButton();
        button.setText(this.subject);
        button.setBackground(colorTheme.getRounderCornerColor());
        button.setForeground(colorTheme.getTextColor());
        button.setFont(gothamFont.assignFont("GothamMedium", 14f));

        button.addActionListener(e -> {
            if(button.isSelected()) {
                System.out.println("Calculating results for " + this.subject);
                Subject stats= new DBHandler().getStatsForSub(this.subject);

                int totalClasses=stats.getTotalClasses();
                int attendedClasses=stats.getAttendedClasses();
                int missedClasses=stats.getMissedClasses();
                int safeBunks=stats.getSafebunks();

                new SubjectStats(this.subject,totalClasses,attendedClasses,missedClasses,safeBunks);

            }
        });
        return button;
    }
}
