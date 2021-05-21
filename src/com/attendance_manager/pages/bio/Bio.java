package com.attendance_manager.pages.bio;

import com.attendance_manager.components.UIcomponents.Toast;
import com.attendance_manager.components.styles.*;
import com.attendance_manager.pages.Homepage;
import com.attendance_manager.services.DBHandler;
import models.BioData;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Bio extends JFrame {

    GridBagConstraints gbc = new GridBagConstraints();
    GridBagConstraints gbcL = new GridBagConstraints();

    String filePath = null;

    public Bio() {

        BufferedImage logo ;
        JLabel logoLabel = new JLabel();
        Image resizedLogo ;

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
        logoContainer.setPreferredSize(new Dimension(500, 150));

        logoContainer.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        logoContainer.add(logoLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        logoContainer.add(appTitle, gbc);

        gbc.insets = new Insets(0, 0, 0, 0);

        JButton uploadPhotoBtn = new JButton("Upload Photo");
        uploadPhotoBtn.setBorder(new RoundedBorder(20, colorTheme.getAdjustmentTone()));
        uploadPhotoBtn.setFont(gothamFont.assignFont("GothamBold", 15f));
        uploadPhotoBtn.setBackground(colorTheme.getAccColorLight());
        uploadPhotoBtn.setForeground(colorTheme.getTextColor());

        uploadPhotoBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setMultiSelectionEnabled(false);
            fileChooser.setFileSelectionMode(JFileChooser.APPROVE_OPTION);
            fileChooser.setFileHidingEnabled(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .jpeg/.jpg/png files", "jpeg","jpg","png");
            fileChooser.addChoosableFileFilter(restrict);
            if(JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(null)) {
                File file = fileChooser.getSelectedFile();
                filePath = file.getPath();
            }
        });

        JLabel containerTitle = new JLabel("Bio");
        containerTitle.setFont(gothamFont.assignFont("GothamMedium", 20f));
        containerTitle.setForeground(colorTheme.getTextColor());
        containerTitle.setBorder(customBorder.assignBorder(Color.black, 0, 25, 10, 0, 10));

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(gothamFont.assignFont("GothamBook", 14f));
        nameLabel.setForeground(colorTheme.getTextColor());

        JTextField nameField = new RoundedTextField(20);
        nameField.setBorder(customBorder.assignBorder(Color.black, 1, 5, 10, 5, 0));
        nameField.setFont(gothamFont.assignFont("GothamBook", 14f));

        JLabel yearLabel = new JLabel("Year");
        yearLabel.setFont(gothamFont.assignFont("GothamBook", 14f));
        yearLabel.setForeground(colorTheme.getTextColor());

        JTextField yearField = new RoundedTextField(20);
        yearField.setBorder(customBorder.assignBorder(Color.black, 1, 5, 10, 5, 0));
        yearField.setFont(gothamFont.assignFont("GothamBook", 14f));

        JLabel collegeLabel = new JLabel("College");
        collegeLabel.setFont(gothamFont.assignFont("GothamBook", 14f));
        collegeLabel.setForeground(colorTheme.getTextColor());

        JTextField collegeField = new RoundedTextField(20);
        collegeField.setBorder(customBorder.assignBorder(Color.black, 1, 5, 10, 5, 0));
        collegeField.setFont(gothamFont.assignFont("GothamBook", 14f));

        JPanel fieldContainer = new JPanel();
        fieldContainer.setBackground(colorTheme.getLightTransColor());
        fieldContainer.setPreferredSize(new Dimension(500, 220));

        gbc.insets = new Insets(20, 10, 20, 10);

        fieldContainer.setLayout(new GridBagLayout());
        gbcL.gridx = 0;
        gbcL.gridy = 0;
        gbcL.anchor = GridBagConstraints.WEST;
        fieldContainer.add(nameLabel, gbcL);
        gbc.gridx = 1;
        gbc.gridy = 0;
        fieldContainer.add(nameField, gbc);
        gbcL.gridx = 0;
        gbcL.gridy = 1;
        gbcL.anchor = GridBagConstraints.WEST;
        fieldContainer.add(yearLabel, gbcL);
        gbc.gridx = 1;
        gbc.gridy = 1;
        fieldContainer.add(yearField, gbc);
        gbcL.gridx = 0;
        gbcL.gridy = 2;
        gbcL.anchor = GridBagConstraints.WEST;
        fieldContainer.add(collegeLabel, gbcL);
        gbc.gridx = 1;
        gbc.gridy = 2;
        fieldContainer.add(collegeField, gbc);

        JButton updateBioBtn = new JButton("Update Bio");
        updateBioBtn.setBorder(new RoundedBorder(20, colorTheme.getAdjustmentTone()));
        updateBioBtn.setFont(gothamFont.assignFont("GothamBold", 15f));
        updateBioBtn.setBackground(colorTheme.getAccColorLight());
        updateBioBtn.setForeground(colorTheme.getTextColor());

        updateBioBtn.addActionListener(e -> {
            String inputName = nameField.getText();
            String inputYear = yearField.getText();
            String inputCollege = collegeField.getText();
            String inputFilePath=filePath;

            DBHandler db=new DBHandler();

            BioData bio=new BioData(inputName,inputYear,inputCollege,inputFilePath);
            db.updateBio(bio);
            Toast toast = new Toast("Bio Updated!", 700, 50);
            toast.showtoast();
            new Homepage();
            dispose();
        });

        JButton viewBioBtn = new JButton("View Bio");
        viewBioBtn.setBorder(new RoundedBorder(20, colorTheme.getAdjustmentTone()));
        viewBioBtn.setFont(gothamFont.assignFont("GothamBold", 15f));
        viewBioBtn.setBackground(colorTheme.getAccColorLight());
        viewBioBtn.setForeground(colorTheme.getTextColor());

        viewBioBtn.addActionListener(e -> new ViewBio(filePath));

        JPanel btnPane = new JPanel();
        btnPane.setBackground(colorTheme.getPriColor());

        btnPane.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        btnPane.add(updateBioBtn, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        btnPane.add(viewBioBtn, gbc);

        JPanel container = new JPanel();
        container.setBackground(colorTheme.getLightTransColor());

        gbc.insets = new Insets(10, 0, 10, 0);

        container.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        container.add(uploadPhotoBtn, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        container.add(containerTitle, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        container.add(fieldContainer, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        container.add(btnPane, gbc);

        setIconImage(new ImageIcon("src/resources/images/spotify.png").getImage());
        setTitle("User Bio");
        setVisible(true);

        setContentPane(new JLabel(new ImageIcon("src/resources/images/background.jpg")));

        gbc.insets = new Insets(0, 0, 0, 0);

        setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(logoContainer, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(container, gbc);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
