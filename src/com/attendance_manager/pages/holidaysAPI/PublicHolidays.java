package com.attendance_manager.pages.holidaysAPI;

import com.attendance_manager.components.styles.ColorTheme;
import com.attendance_manager.components.styles.GothamFont;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static javax.swing.BorderFactory.createEmptyBorder;


public class PublicHolidays extends JFrame {
    BufferedReader reader;
    String line;
    StringBuffer responseContent = new StringBuffer();
    GridBagConstraints gbc=new GridBagConstraints();
    JTable table;
    String holiday[][];


    ColorTheme colorTheme=new ColorTheme();

    private static HttpURLConnection connection;


    public PublicHolidays() {

        Parser parser = new Parser();
        GothamFont gothamFont = new GothamFont();

        JLabel containerTitle = new JLabel("Public Holidays");
        containerTitle.setFont(gothamFont.assignFont("GothamMedium", 20f));
        containerTitle.setForeground(colorTheme.getTextColor());

        try {
            URL url = new URL("https://holidayapi.com/v1/holidays?pretty&country=" +
                    "IN&year=2020&key=60f5cc91-b484-44a2" +
                    "-a01f-af5d4571c758");
            connection = (HttpURLConnection) url.openConnection();
            int status = connection.getResponseCode();
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line + "\n");
                }
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line + "\n");
                }
                reader.close();

            }

            setLayout(new GridBagLayout());

            ArrayList<ArrayList<String>> extractedHolidays=parser.parse(responseContent.toString());

             holiday = new String[extractedHolidays.size()][extractedHolidays.get(0).size()];

            for(int i=0;i<extractedHolidays.size();i++){
                for(int j=0;j<extractedHolidays.get(i).size();j++){
                    String line=extractedHolidays.get(i).get(j);
                    holiday[i][j]=line;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] columnNames={"Date","Day","Occasion"};

        table=new JTable(holiday,columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(1000,600));
        table.setRowHeight(table.getRowHeight() + 20);
        table.getTableHeader().setPreferredSize(new Dimension(1000,table.getRowHeight() + 20));
        table.setFillsViewportHeight(true);
        table.setShowVerticalLines(false);
        table.setBackground(new Color(0,0,0));
        table.setFont(gothamFont.assignFont("GothamMedium", 13f));
        table.setForeground(colorTheme.getTextColor());
        table.setBorder(createEmptyBorder());

        table.getTableHeader().setBackground(new Color(18,18,18));
        table.getTableHeader().setFont(gothamFont.assignFont("GothamBold", 16f));
        table.getTableHeader().setForeground(colorTheme.getAccColorLight());

        for(int i=0;i<3;i++){
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            table.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        }

        JScrollPane jps=new JScrollPane(table);
        setIconImage(new ImageIcon("src/resources/images/logo_1.png").getImage());
        setTitle("Public Holidays");

        setVisible(true);
        setContentPane(new JLabel(new ImageIcon("src/resources/images/background.jpg")));

        gbc.insets= new Insets(10,0,10,0);
        setLayout(new GridBagLayout());
        gbc.gridx=0;
        gbc.gridy=0;
        add(containerTitle,gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        add(jps,gbc);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }


}



