package com.attendance_manager.pages.timeTable;

import com.attendance_manager.components.styles.ColorTheme;
import com.attendance_manager.components.styles.GothamFont;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static javax.swing.BorderFactory.createEmptyBorder;

public class ViewTT extends JFrame {
    GothamFont gothamFont = new GothamFont();
    ColorTheme colorTheme=new ColorTheme();
    GridBagConstraints gbc=new GridBagConstraints();
    JTable table;
    public ViewTT(){
        String[][] namesList;

        try {
            FileInputStream fis = new FileInputStream("textfile.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            namesList = (String[][]) ois.readObject();

            ois.close();
            fis.close();

            JLabel containerTitle = new JLabel("Time Table");
            containerTitle.setFont(gothamFont.assignFont("GothamMedium", 20f));
            containerTitle.setForeground(colorTheme.getTextColor());

            String[] columnNames={"DAY","1","2","3","4","5","6","7","8"};

            String[][] TT=new String[5][9];
            TT[0][0]="Monday";
            TT[1][0]="Tuesday";
            TT[2][0]="Wednesday";
            TT[3][0]="Thursday";
            TT[4][0]="Friday";
            for(int i=0;i<5;i++){
                for(int j=1;j<9;j++){
                    TT[i][j]=namesList[i][j-1];
                }
            }

            table = new JTable(TT,columnNames);
            table.setPreferredScrollableViewportSize(new Dimension(1000,280));
            table.setRowHeight(table.getRowHeight() + 40);
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

            for(int i=0;i<9;i++){
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment( JLabel.CENTER );
                table.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
            }

            JScrollPane jps=new JScrollPane(table);

            setIconImage(new ImageIcon("src/resources/images/logo_1.png").getImage());
            setTitle("View Timetable");

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



        } catch (IOException ioe) {
            ioe.printStackTrace();

        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();

        }


    }
}