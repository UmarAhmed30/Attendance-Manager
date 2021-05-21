package com.attendance_manager.pages.subject;

import com.attendance_manager.components.UIcomponents.Toast;
import com.attendance_manager.components.styles.*;
import com.attendance_manager.pages.Homepage;
import com.attendance_manager.services.DBHandler;
import models.Subject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

//added models
public class AddSubject extends JFrame {

    GridBagConstraints gbc = new GridBagConstraints();
    GridBagConstraints gbcL = new GridBagConstraints();

    public AddSubject() {

        DBHandler db = new DBHandler();

        BufferedImage logo = null;
        Image resizedLogo = null;
        JLabel logoLabel = new JLabel();

        GothamFont gothamFont = new GothamFont();

        ColorTheme colorTheme = new ColorTheme();

        CustomBorder customBorder = new CustomBorder();

        gbc.insets = new Insets(10, 10, 10, 10);

        try {
            logo = ImageIO.read(new File("src/resources/images/spotify.png"));
            resizedLogo = logo.getScaledInstance(75, 75, Image.SCALE_DEFAULT);
            logoLabel = new JLabel(new ImageIcon(resizedLogo));
        } catch (Exception e) {
            System.out.print("Image not found!");
        }

        JLabel appTitle = new JLabel("Attendance Manager");
        appTitle.setFont(gothamFont.assignFont("GothamBold", 30f));
        appTitle.setForeground(colorTheme.getAccColorLight());

        JPanel logoContainer = new JPanel();
        logoContainer.setBackground(colorTheme.getDarkTransColor());
        logoContainer.setPreferredSize(new Dimension(540, 150));

        logoContainer.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        logoContainer.add(logoLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        logoContainer.add(appTitle, gbc);

        gbc.insets = new Insets(20, 20, 20, 20);

        JLabel containerTitle = new JLabel("Add Subject");
        containerTitle.setFont(gothamFont.assignFont("GothamMedium", 20f));
        containerTitle.setForeground(colorTheme.getTextColor());
        containerTitle.setBorder(customBorder.assignBorder(Color.black, 0, 25, 10, 0, 10));

        JPanel fieldContainer = new JPanel();
        fieldContainer.setBackground(colorTheme.getLightTransColor());
        fieldContainer.setPreferredSize(new Dimension(500, 220));

        JLabel codeLabel = new JLabel("Code");
        codeLabel.setFont(gothamFont.assignFont("GothamBook", 14f));
        codeLabel.setForeground(colorTheme.getTextColor());

        JTextField codeField = new RoundedTextField(20);
        codeField.setBorder(customBorder.assignBorder(Color.black, 1, 5, 10, 5, 0));
        codeField.setFont(gothamFont.assignFont("GothamBook", 14f));

        JLabel subNameLabel = new JLabel("Name");
        subNameLabel.setFont(gothamFont.assignFont("GothamBook", 14f));
        subNameLabel.setForeground(colorTheme.getTextColor());

        JTextField subNameField = new RoundedTextField(20);
        subNameField.setBorder(customBorder.assignBorder(Color.black, 1, 5, 10, 5, 0));
        subNameField.setFont(gothamFont.assignFont("GothamBook", 14f));

        JLabel facultyLabel = new JLabel("Faculty");
        facultyLabel.setFont(gothamFont.assignFont("GothamBook", 14f));
        facultyLabel.setForeground(colorTheme.getTextColor());

        JTextField facultyField = new RoundedTextField(20);
        facultyField.setBorder(customBorder.assignBorder(Color.black, 1, 5, 10, 5, 0));
        facultyField.setFont(gothamFont.assignFont("GothamBook", 14f));

        JPanel addSubjectContainer = new JPanel();
        addSubjectContainer.setBackground(colorTheme.getLightTransColor());

        JButton addButton = new JButton("Add");
        addButton.setBorder(new RoundedBorder(20, colorTheme.getPriColor()));
        addButton.setFont(gothamFont.assignFont("GothamBold", 15f));
        addButton.setBackground(colorTheme.getAccColorLight());
        addButton.setForeground(colorTheme.getTextColor());

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputCode = codeField.getText();
                String inputSubName = subNameField.getText();
                String inputFaculty = facultyField.getText();
                System.out.println(inputCode + " " + inputSubName + " " + inputFaculty);

                Subject subject=new Subject(inputCode,inputSubName,inputFaculty);

                boolean res = db.addSubject(subject);

                Toast toast = new Toast("Subject Added!", 700, 50);
                toast.showtoast();
                new Homepage();
                dispose();
            }
        });

        fieldContainer.setLayout(new GridBagLayout());
        gbcL.gridx = 0;
        gbcL.gridy = 0;
        gbcL.anchor = GridBagConstraints.WEST;
        fieldContainer.add(codeLabel, gbcL);
        gbc.gridx = 1;
        gbc.gridy = 0;
        fieldContainer.add(codeField, gbc);
        gbcL.gridx = 0;
        gbcL.gridy = 1;
        gbcL.anchor = GridBagConstraints.WEST;
        fieldContainer.add(subNameLabel, gbcL);
        gbc.gridx = 1;
        gbc.gridy = 1;
        fieldContainer.add(subNameField, gbc);
        gbcL.gridx = 0;
        gbcL.gridy = 2;
        gbcL.anchor = GridBagConstraints.WEST;
        fieldContainer.add(facultyLabel, gbcL);
        gbc.gridx = 1;
        gbc.gridy = 2;
        fieldContainer.add(facultyField, gbc);

        addSubjectContainer.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        addSubjectContainer.add(containerTitle, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        addSubjectContainer.add(fieldContainer, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        addSubjectContainer.add(addButton, gbc);


        gbc.insets = new Insets(0, 0, 0, 0);

        setIconImage(new ImageIcon("src/resources/images/spotify.png").getImage());
        setTitle("Add Subject");
        setVisible(true);

        setContentPane(new JLabel(new ImageIcon("src/resources/images/background.jpg")));

        setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(logoContainer, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(addSubjectContainer, gbc);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

    }
}
