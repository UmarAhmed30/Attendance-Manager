package com.attendance_manager.pages;

import com.attendance_manager.components.ColorTheme;

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

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    ColorTheme colorTheme=new ColorTheme();

    private static HttpURLConnection connection;


    public PublicHolidays() {


        Parser parser = new Parser();

        //API
        try {
            URL url = new URL("https://holidayapi.com/v1/holidays?pretty&country=IN&year=2020&key=60f5cc91-b484-44a2" +
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

            ArrayList<JLabel> holidays;
            //System.out.println(extractedHolidays);

             holiday = new String[extractedHolidays.size()][extractedHolidays.get(0).size()];

            for(int i=0;i<extractedHolidays.size();i++){

                //System.out.println(extractedHolidays.get(i));
                for(int j=0;j<extractedHolidays.get(i).size();j++){
                    //System.out.print(extractedHolidays.get(i).get(j));
                    String line=extractedHolidays.get(i).get(j);
                    holiday[i][j]=line;

                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        //SETTING THE TABLE
        String[] columnNames={"Date","Day","Occasion"};


        table=new JTable(holiday,columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(1000,600));
        table.setRowHeight(table.getRowHeight() + 20);
        table.getTableHeader().setPreferredSize(new Dimension(1000,table.getRowHeight() + 20));
        table.setFillsViewportHeight(true);
        table.setShowVerticalLines(false);
        table.setBackground(new Color(18,18,18));
        table.getTableHeader().setBackground(new Color(18,18,18));
        table.getTableHeader().setForeground(Color.WHITE);

        table.setBorder(createEmptyBorder());
        table.setForeground(Color.WHITE);



        //aligning cell contents to center
        for(int i=0;i<3;i++){
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            table.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        }


        //scroll Pane
        JScrollPane jps=new JScrollPane(table);




        //OUTER FRAME
        setIconImage(new ImageIcon("src/resources/images/logo_1.png").getImage());
        setTitle("Attendance Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(new JLabel(new ImageIcon("src/resources/images/background.jpg")));

        //adding jps
        setLayout(new GridBagLayout());
        gbc.gridx=0;
        gbc.gridy=0;
        add(jps,gbc);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }


}



