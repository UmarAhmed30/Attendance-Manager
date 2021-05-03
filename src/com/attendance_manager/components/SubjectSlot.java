package com.attendance_manager.components;

import com.attendance_manager.services.DBHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubjectSlot {
    GridBagConstraints gbc = new GridBagConstraints();
    GothamFont gothamFont = new GothamFont();
    ColorTheme colorTheme = new ColorTheme();
    CustomBorder customBorder = new CustomBorder();
    String subject;
    DBHandler db=new DBHandler();
    public SubjectSlot(String subject) {
        this.subject = subject;
    }

    public JPanel generateSlot () {
        gbc.insets = new Insets(0,0,0,0);

        JLabel sub = new JLabel(this.subject);
        sub.setFont(gothamFont.assignFont("GothamMedium", 14f));
        sub.setForeground(colorTheme.getTextColor());

        JButton subPButton = new JButton("Present");
        subPButton.setFont(gothamFont.assignFont("GothamBold", 15f));
        subPButton.setBackground(colorTheme.getAccColorDark());
        subPButton.setForeground(Color.black);
        subPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(subject+" PRESENT");
                db.updateAttendance(subject,1);
                db.updateHistory(subject,1);

            }
        });

        JButton subAButton = new JButton("Absent");
        subAButton.setFont(gothamFont.assignFont("GothamBold", 15f));
        subAButton.setBackground(colorTheme.getAccColorDark());
        subAButton.setForeground(Color.black);

        subAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(subject+" ABSENT");
                db.updateAttendance(subject,0);
                db.updateHistory(subject,0);

            }
        });

        JPanel entry = new JPanel();
        entry.setBorder(customBorder.assignBorder(null, 0, 10, 0, 10, 0));
        entry.setBackground(colorTheme.getDarkTransColor());
        entry.setPreferredSize(new Dimension(500, 70));

        gbc.insets = new Insets(0,40,0,40);

        entry.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        entry.add(sub, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        entry.add(subPButton, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        entry.add(subAButton, gbc);

        return entry;
    }
}
