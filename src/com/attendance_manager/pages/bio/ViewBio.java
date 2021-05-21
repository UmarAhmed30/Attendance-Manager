package com.attendance_manager.pages.bio;

import com.attendance_manager.components.styles.ColorTheme;
import com.attendance_manager.components.styles.GothamFont;
import com.attendance_manager.services.DBHandler;
import models.BioData;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ViewBio extends JDialog {

    GridBagConstraints gbc = new GridBagConstraints();
    GridBagConstraints gbcL = new GridBagConstraints();

    String filePath = null;

    public ViewBio(String filePath) {
        DBHandler db=new DBHandler();
        BioData bioInfo;
        bioInfo=db.getBio();
        String  name=bioInfo.getName();
        String year=bioInfo.getYear();
        String college=bioInfo.getCollege();
        String fileP=bioInfo.getImagePath();
        this.filePath = fileP;

        ColorTheme colorTheme = new ColorTheme();
        GothamFont gothamFont = new GothamFont();

        BufferedImage profile = null;
        Image resizedProfile = null;
        JLabel profileLabel = new JLabel();

        try{
            profile = ImageIO.read(new File(this.filePath));
            resizedProfile = profile.getScaledInstance(200, 250, Image.SCALE_DEFAULT);
            profileLabel = new JLabel(new ImageIcon(resizedProfile));
        }
        catch (Exception e) {
            System.out.print("Image not found!");
        }

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(gothamFont.assignFont("GothamBold", 18f));
        nameLabel.setForeground(colorTheme.getTextColor());

        JLabel nameDataLabel = new JLabel(name);
        nameDataLabel.setFont(gothamFont.assignFont("GothamBook", 18f));
        nameDataLabel.setForeground(colorTheme.getTextColor());

        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setFont(gothamFont.assignFont("GothamBold", 18f));
        yearLabel.setForeground(colorTheme.getTextColor());

        JLabel yearDataLabel = new JLabel(year);
        yearDataLabel.setFont(gothamFont.assignFont("GothamBook", 18f));
        yearDataLabel.setForeground(colorTheme.getTextColor());

        JLabel collegeLabel = new JLabel("College:");
        collegeLabel.setFont(gothamFont.assignFont("GothamBold", 18f));
        collegeLabel.setForeground(colorTheme.getTextColor());

        JLabel collegeDataLabel = new JLabel(college);
        collegeDataLabel.setFont(gothamFont.assignFont("GothamBook", 18f));
        collegeDataLabel.setForeground(colorTheme.getTextColor());

        JPanel dataContainer = new JPanel();
        dataContainer.setPreferredSize(new Dimension(350, 250));
        dataContainer.setBackground(colorTheme.getLightTransColor());

        gbcL.insets = new Insets(10,10,10,10);

        dataContainer.setLayout(new GridBagLayout());
        gbcL.gridx = 0;
        gbcL.gridy = 0;
        gbcL.anchor = GridBagConstraints.WEST;
        dataContainer.add(nameLabel, gbcL);
        gbcL.gridx = 1;
        gbcL.gridy = 0;
        gbcL.anchor = GridBagConstraints.WEST;
        dataContainer.add(nameDataLabel, gbcL);
        gbcL.gridx = 0;
        gbcL.gridy = 1;
        gbcL.anchor = GridBagConstraints.WEST;
        dataContainer.add(yearLabel, gbcL);
        gbcL.gridx = 1;
        gbcL.gridy = 1;
        gbcL.anchor = GridBagConstraints.WEST;
        dataContainer.add(yearDataLabel, gbcL);
        gbcL.gridx = 0;
        gbcL.gridy = 2;
        gbcL.anchor = GridBagConstraints.WEST;
        dataContainer.add(collegeLabel, gbcL);
        gbcL.gridx = 1;
        gbcL.gridy = 2;
        gbcL.anchor = GridBagConstraints.WEST;
        dataContainer.add(collegeDataLabel, gbcL);

        setIconImage(new ImageIcon("src/resources/images/spotify.png").getImage());
        setTitle("User Bio");
        setVisible(true);
        setSize(700, 350);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int w = getSize().width;
        int h = getSize().height;

        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;

        setLocation(x, y);

        setContentPane(new JLabel(new ImageIcon("src/resources/images/background.jpg")));

        gbc.insets = new Insets(10,10,10,10);

        setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(profileLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(dataContainer, gbc);

    }
}
