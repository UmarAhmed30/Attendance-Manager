package com.attendance_manager.pages;

import com.attendance_manager.components.*;
import com.attendance_manager.services.DBHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;


public class GenerateLeaveForm extends JFrame {

    GridBagConstraints gbc = new GridBagConstraints();
    GridBagConstraints gbcL = new GridBagConstraints();
    ColorTheme colorTheme=new ColorTheme();

    public GenerateLeaveForm()
    {	//JDBC
        DBHandler db=new DBHandler();


        BufferedImage logo = null;
        Image resizedLogo = null;
        JLabel logoLabel = new JLabel();

        GothamFont gothamFont = new GothamFont();

        ColorTheme colorTheme = new ColorTheme();

        CustomBorder customBorder = new CustomBorder();

        gbc.insets = new Insets(10,10,10,10);

        try{
            logo = ImageIO.read(new File("src/resources/images/spotify.png"));
            resizedLogo = logo.getScaledInstance(75, 75, Image.SCALE_DEFAULT);
            logoLabel = new JLabel(new ImageIcon(resizedLogo));
        }
        catch (Exception e) {
            System.out.print("Image not found!");
        }

        JLabel appTitle = new JLabel("Attendance Manager");
        appTitle.setFont(gothamFont.assignFont("GothamBold", 30f));
        appTitle.setForeground(new ColorTheme().getAccColorLight());

        JPanel logoContainer = new JPanel();
        logoContainer.setBackground(colorTheme.getDarkTransColor());
        logoContainer.setPreferredSize(new Dimension(540, 150));

        logoContainer.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        logoContainer.add(logoLabel,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        logoContainer.add(appTitle,gbc);

        gbc.insets = new Insets(20,20,20,20);

        JLabel containerTitle = new JLabel("Generate");
        containerTitle.setFont(gothamFont.assignFont("GothamMedium", 20f));
        containerTitle.setForeground(colorTheme.getTextColor());
        containerTitle.setBorder(customBorder.assignBorder(Color.black, 0 , 25, 10, 0, 10));

        JPanel fieldContainer = new JPanel();
        fieldContainer.setBackground(colorTheme.getLightTransColor());
        fieldContainer.setPreferredSize(new Dimension(500, 350));

        JLabel dateLabel = new JLabel("Date");
        dateLabel.setFont(gothamFont.assignFont("GothamBook", 14f));
        dateLabel.setForeground(colorTheme.getTextColor());

        JTextField emailField = new RoundedTextField(20);
        emailField.setBorder(customBorder.assignBorder(colorTheme.getRounderCornerColor(), 1, 5, 10, 5, 0));
        emailField.setFont(gothamFont.assignFont("GothamBook", 14f));

        JLabel reasonLabel = new JLabel("Reason");
        reasonLabel.setFont(gothamFont.assignFont("GothamBook", 14f));
        reasonLabel.setForeground(colorTheme.getTextColor());

        JTextField reasonField = new RoundedTextField(20);
        reasonField.setBorder(customBorder.assignBorder(colorTheme.getRounderCornerColor(), 1, 5, 10, 5, 0));
        reasonField.setFont(gothamFont.assignFont("GothamBook", 14f));

        JLabel staffLabel = new JLabel("Staff");
        staffLabel.setFont(gothamFont.assignFont("GothamBook", 14f));
        staffLabel.setForeground(colorTheme.getTextColor());

        JTextField staffField = new RoundedTextField(20);
        staffField.setBorder(customBorder.assignBorder(colorTheme.getRounderCornerColor(), 1, 5, 10, 5, 0));
        staffField.setFont(gothamFont.assignFont("GothamBook", 14f));

        JLabel subjectLabel = new JLabel("Subject");
        subjectLabel.setFont(gothamFont.assignFont("GothamBook", 14f));
        subjectLabel.setForeground(colorTheme.getTextColor());

        JTextField subjectField = new RoundedTextField(20);
        subjectField.setBorder(customBorder.assignBorder(colorTheme.getRounderCornerColor(), 1, 5, 10, 5, 0));
        subjectField.setFont(gothamFont.assignFont("GothamBook", 14f));


        JPanel loginContainer = new JPanel();
        loginContainer.setBackground(colorTheme.getLightTransColor());



        JButton generateButton = new JButton("Generate");
        generateButton.setBorder(new RoundedBorder(20, colorTheme.getRounderCornerColor()));
        generateButton.setFont(gothamFont.assignFont("GothamBold", 15f));
        generateButton.setBackground(colorTheme.getAccColorLight());
        generateButton.setForeground(colorTheme.getTextColor());



        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputDate = emailField.getText();
                String inputReason = reasonField.getText();
                String inputStaff=staffField.getText();
                String inputSubject=subjectField.getText();

                //System.out.println(inputDate+" "+inputReason+" "+inputStaff+" "+inputSubject);

                String leaveLetter="From\n\tUmar.\nTo\n\t"+inputStaff +"\nRespected mam/sir,\n\tI " +
                        "was not able to attend the "+inputSubject +" class on "+ inputDate +" because "+inputReason +". " +
                        "\nKindly grant me leave for my absence.\n\t\t\t\t\t\t\tThanking you,\n\t\t\t\t\t\t\t\t\t\t" +
                        "Yours faithfully,\n\t\t\t\t\t\t\t\t\t\tUmar.";
                System.out.println(leaveLetter);

            }
        });

        fieldContainer.setLayout(new GridBagLayout());
        gbcL.gridx = 0;
        gbcL.gridy = 0;
        gbcL.anchor = GridBagConstraints.WEST;
        fieldContainer.add(dateLabel,gbcL);
        gbc.gridx = 1;
        gbc.gridy = 0;
        fieldContainer.add(emailField, gbc);
        gbcL.gridx = 0;
        gbcL.gridy = 1;
        gbcL.anchor = GridBagConstraints.WEST;
        fieldContainer.add(reasonLabel,gbcL);
        gbc.gridx = 1;
        gbc.gridy = 1;
        fieldContainer.add(reasonField,gbc);
        gbcL.gridx = 0;
        gbcL.gridy = 2;
        gbcL.anchor = GridBagConstraints.WEST;
        fieldContainer.add(staffLabel,gbcL);
        gbc.gridx=1;
        gbc.gridy=2;
        fieldContainer.add(staffField,gbc);
        gbcL.gridx = 0;
        gbcL.gridy = 3;
        gbcL.anchor = GridBagConstraints.WEST;
        fieldContainer.add(subjectLabel,gbcL);
        gbc.gridx=1;
        gbc.gridy=3;
        fieldContainer.add(subjectField,gbc);

        loginContainer.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginContainer.add(containerTitle,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginContainer.add(fieldContainer, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        loginContainer.add(generateButton, gbc);



        gbc.insets = new Insets(0,0,0,0);


        setIconImage(new ImageIcon("src/resources/images/spotify.png").getImage());
        setTitle("Attendance Manager");
        setVisible(true);

        setContentPane(new JLabel(new ImageIcon("src/resources/images/background.jpg")));

        setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(logoContainer,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(loginContainer,gbc);


        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

}