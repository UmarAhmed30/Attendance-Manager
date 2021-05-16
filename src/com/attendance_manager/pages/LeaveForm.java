package com.attendance_manager.pages;

import javax.swing.*;
import java.awt.*;

public class LeaveForm extends JDialog {

    GridBagConstraints gbc = new GridBagConstraints();

    public LeaveForm(String leaveLetter) {

        JTextArea leaveLetterTextArea = new JTextArea(leaveLetter);

        setIconImage(new ImageIcon("src/resources/images/spotify.png").getImage());
        setTitle("Attendance Manager");
        setVisible(true);
        setSize(700, 300);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int w = getSize().width;
        int h = getSize().height;

        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;

        setLocation(x, y);

        setContentPane(new JLabel(new ImageIcon("src/resources/images/background.jpg")));

        setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(leaveLetterTextArea);
    }
}
