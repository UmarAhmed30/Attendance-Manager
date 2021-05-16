package com.attendance_manager.components;

import com.attendance_manager.pages.History;
import com.attendance_manager.pages.SubjectStats;
import com.attendance_manager.services.DBHandler;

import javax.swing.*;
import java.util.ArrayList;

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
                ArrayList<Integer> stats= new DBHandler().getStatsForSub(this.subject);


                int totalClasses=stats.get(0);
                int attendedClasses=stats.get(1);
                int missedClasses=stats.get(0)-stats.get(1);
                int safeBunks=stats.get(2);



                new SubjectStats(this.subject,totalClasses,attendedClasses,missedClasses,safeBunks);

            }


        });

        return button;
    }
}
