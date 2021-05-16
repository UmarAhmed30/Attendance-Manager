package com.attendance_manager.components;

import javax.swing.*;

public class CustomRadioButton {

    String subject;

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
        return button;
    }
}
