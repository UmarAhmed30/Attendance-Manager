package com.attendance_manager.pages;

import com.attendance_manager.components.ColorTheme;
import com.attendance_manager.components.GothamFont;
import com.attendance_manager.services.DBHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

import static javax.swing.BorderFactory.createEmptyBorder;

public class History extends JFrame{

    GothamFont gothamFont = new GothamFont();
    ColorTheme colorTheme=new ColorTheme();
    JTable table;
    GridBagConstraints gbc=new GridBagConstraints();
    DBHandler db=new DBHandler();
    String[][] history;
    public History(){

        //HEADING
        JLabel containerTitle = new JLabel("Attendance History");
        containerTitle.setFont(gothamFont.assignFont("GothamMedium", 20f));
        containerTitle.setForeground(colorTheme.getTextColor());


        //SETTING THE TABLE

        String[] columnNames={"Subject Name","Attendance"};

        ArrayList<ArrayList<String>> extractedHistory=db.fetchHistory();
        for(int i=0;i<extractedHistory.size();i++){
            for(int j=0;j<extractedHistory.get(0).size();j++  ){
                System.out.print(extractedHistory.get(i).get(j)+" ");

            }
            System.out.println();
        }

        history = new String[extractedHistory.size()][extractedHistory.get(0).size()];
        for(int i=0;i<extractedHistory.size();i++){

            //System.out.println(extractedHolidays.get(i));
            for(int j=0;j<extractedHistory.get(i).size();j++){
                //System.out.print(extractedHolidays.get(i).get(j));
                String line=extractedHistory.get(i).get(j);
                history[i][j]=line;

            }

        }


        table=new JTable(history,columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(1000,600));
        table.setRowHeight(table.getRowHeight() + 20);
        table.getTableHeader().setPreferredSize(new Dimension(1000,table.getRowHeight() + 20));
        table.setFillsViewportHeight(true);
        table.setShowVerticalLines(false);
        table.setBackground(new Color(0,0,0));
        table.setFont(gothamFont.assignFont("GothamMedium", 13f));
        table.setForeground(colorTheme.getTextColor());
        table.setBorder(createEmptyBorder());

        //table header row
        table.getTableHeader().setBackground(new Color(18,18,18));
        table.getTableHeader().setFont(gothamFont.assignFont("GothamBold", 16f));
        table.getTableHeader().setForeground(colorTheme.getAccColorLight());






        //aligning cell contents to center
        for(int i=0;i<2;i++){
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            table.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        }


        //scroll Pane
        JScrollPane jps=new JScrollPane(table);



        //OUTER FRAME
        setIconImage(new ImageIcon("src/resources/images/logo_1.png").getImage());
        setTitle("History");

        setVisible(true);
        setContentPane(new JLabel(new ImageIcon("src/resources/images/background.jpg")));


        gbc.insets= new Insets(10,0,10,0);
        setLayout(new GridBagLayout());
        gbc.gridx=0;
        gbc.gridy=0;
        add(containerTitle,gbc);

        //adding jps
        gbc.gridx=0;
        gbc.gridy=1;
        add(jps,gbc);


        setExtendedState(JFrame.MAXIMIZED_BOTH);

    }
}
