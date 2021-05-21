package com.attendance_manager.pages.timeTable;

import com.attendance_manager.components.UIcomponents.Toast;
import com.attendance_manager.components.styles.ColorTheme;
import com.attendance_manager.components.styles.GothamFont;
import com.attendance_manager.components.styles.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class AddTimetable extends JFrame implements Serializable {

    ColorTheme colorTheme = new ColorTheme();
    GothamFont gothamFont = new GothamFont();
    GridBagConstraints gbc = new GridBagConstraints();

    public AddTimetable() {
        JPanel containerPanel = new JPanel();
        containerPanel.setBackground(colorTheme.getDarkTransColor());
        containerPanel.setPreferredSize(new Dimension(1000, 100));

        JLabel containerTitle = new JLabel("Add Timetable");
        containerTitle.setFont(gothamFont.assignFont("GothamMedium", 20f));
        containerTitle.setForeground(colorTheme.getTextColor());

        containerPanel.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        containerPanel.add(containerTitle, gbc);

        ArrayList<String> days = new ArrayList<>() {
        };
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");

        JPanel containerTimetable = new JPanel();
        containerTimetable.setBackground(colorTheme.getLightTransColor());
        containerTimetable.setPreferredSize(new Dimension(1000, 300));

        JTextField[][] classesTextField = new JTextField[5][8];


        String[][] classes = new String[5][8];

        containerTimetable.setLayout(new GridBagLayout());

        for (int i = 0; i < days.size(); i++) {
            for (int j = 0; j < 9; j++) {
                gbc.gridx = j;
                gbc.gridy = i;
                if (j == 0) {
                    gbc.insets = new Insets(0, 0, 0, 20);
                    JLabel dayLabel = new JLabel(days.get(i));
                    dayLabel.setFont(gothamFont.assignFont("GothamMedium", 14f));
                    dayLabel.setForeground(colorTheme.getTextColor());
                    containerTimetable.add(dayLabel, gbc);
                } else {
                    gbc.insets = new Insets(0, 0, 0, 0);
                    JTextField textField = new JTextField();
                    textField.setPreferredSize(new Dimension(100, 40));
                    textField.setFont(gothamFont.assignFont("GothamBook", 14f));
                    textField.setBackground(Color.black);
                    textField.setForeground(colorTheme.getTextColor());
                    containerTimetable.add(textField, gbc);
                    classesTextField[i][j - 1] = textField;
                }
            }
        }

        JPanel containerBottom = new JPanel();
        containerBottom.setBackground(colorTheme.getLightTransColor());
        containerBottom.setPreferredSize(new Dimension(1000, 100));

        JButton updateBtn = new JButton("Update");
        updateBtn.setBorder(new RoundedBorder(20, colorTheme.getPriColor()));
        updateBtn.setFont(gothamFont.assignFont("GothamBold", 15f));
        updateBtn.setBackground(colorTheme.getAccColorLight());
        updateBtn.setForeground(colorTheme.getTextColor());

        containerBottom.add(updateBtn);

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < days.size(); i++) {
                    for (int j = 0; j < 8; j++) {
                        if (classesTextField[i][j].getText().isEmpty()) classes[i][j] = "-";
                        else classes[i][j] = classesTextField[i][j].getText();
                    }
                }

                try {
                    FileOutputStream fileOut = new FileOutputStream("textfile.txt");
                    ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                    objectOut.writeObject(classes);
                    objectOut.close();
                    System.out.println("The Object  was succesfully written to a file");
                    Toast toast = new Toast("Timetable Updated!", 700, 50);
                    toast.showtoast();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                for (int i = 0; i < days.size(); i++) {
                    for (int j = 0; j < 8; j++) {
                        System.out.print(classes[i][j] + " ");
                    }
                    System.out.println("");
                }
            }

        });

        setIconImage(new ImageIcon("src/resources/images/spotify.png").getImage());
        setTitle("Add Timetable");
        setVisible(true);
        setContentPane(new JLabel(new ImageIcon("src/resources/images/background.jpg")));

        setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(containerPanel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(containerTimetable, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(containerBottom, gbc);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
