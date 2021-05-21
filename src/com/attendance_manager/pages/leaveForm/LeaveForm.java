package com.attendance_manager.pages.leaveForm;

import com.attendance_manager.components.styles.CustomBorder;
import com.attendance_manager.components.styles.GothamFont;

import javax.swing.*;
import java.awt.*;

public class LeaveForm extends JDialog {

    GothamFont gothamFont = new GothamFont();
    GridBagConstraints gbc = new GridBagConstraints();
    CustomBorder customBorder = new CustomBorder();

    public LeaveForm(String leaveLetter) {

        JTextArea leaveLetterTextArea = new JTextArea(leaveLetter);
        leaveLetterTextArea.setFont(gothamFont.assignFont("GothamMedium", 14f));
        leaveLetterTextArea.setLineWrap(true);
        leaveLetterTextArea.setPreferredSize(new Dimension(420, 400));
        leaveLetterTextArea.setBorder(customBorder.assignBorder(Color.white, 0, 5, 5, 5, 5));

        setIconImage(new ImageIcon("src/resources/images/spotify.png").getImage());
        setTitle("Leave Form");
        setVisible(true);
        setSize(500, 500);

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
