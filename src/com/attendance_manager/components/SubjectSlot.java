package com.attendance_manager.components;

import javax.swing.*;
import java.awt.*;

public class SubjectSlot {
    GridBagConstraints gbc = new GridBagConstraints();
    GothamFont gothamFont = new GothamFont();
    ColorTheme colorTheme = new ColorTheme();
    CustomBorder customBorder = new CustomBorder();
    String subject;

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

        JButton subAButton = new JButton("Absent");
        subAButton.setFont(gothamFont.assignFont("GothamBold", 15f));
        subAButton.setBackground(colorTheme.getAccColorDark());
        subAButton.setForeground(Color.black);

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
