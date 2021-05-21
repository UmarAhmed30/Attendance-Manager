package com.attendance_manager.pages.subject;

import com.attendance_manager.components.styles.ColorTheme;
import com.attendance_manager.components.styles.GothamFont;
import com.attendance_manager.components.styles.RoundedBorder;
import com.attendance_manager.pages.Homepage;
import com.attendance_manager.services.DBHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.BorderFactory.createEmptyBorder;

public class History extends JFrame{

    GothamFont gothamFont = new GothamFont();
    ColorTheme colorTheme = new ColorTheme();
    JTable table;
    GridBagConstraints gbc = new GridBagConstraints();
    DBHandler db = new DBHandler();
    String[][] history;

    public History() {


        JLabel containerTitle = new JLabel("Attendance History");
        containerTitle.setFont(gothamFont.assignFont("GothamMedium", 20f));
        containerTitle.setForeground(colorTheme.getTextColor());

        String[] columnNames={"Subject Name","Attendance"};
        ArrayList<ArrayList<String>> extractedHistory;
        try {
            extractedHistory= db.fetchHistory();
            for (int i = 0; i < extractedHistory.size(); i++) {
                for (int j = 0; j < extractedHistory.get(0).size(); j++) {
                    System.out.print(extractedHistory.get(i).get(j) + " ");

                }
                System.out.println();
            }

            history = new String[extractedHistory.size()][extractedHistory.get(0).size()];

            for (int i = 0; i <extractedHistory.size(); i++) {
                for (int j = 0; j < extractedHistory.get(i).size(); j++) {
                    String line = extractedHistory.get(i).get(j);
                    history[extractedHistory.size()-i-1][j] = line;
                }
            }
        }
        catch (Exception e){
            System.out.println(e);

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

        table.getTableHeader().setBackground(new Color(18,18,18));
        table.getTableHeader().setFont(gothamFont.assignFont("GothamBold", 16f));
        table.getTableHeader().setForeground(colorTheme.getAccColorLight());

        for(int i=0;i<2;i++){
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            table.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        }

        JScrollPane jps=new JScrollPane(table);

        JButton clearButton = new JButton("Clear History");
        clearButton.setBorder(new RoundedBorder(20, colorTheme.getPriColor()));
        clearButton.setFont(gothamFont.assignFont("GothamBold", 15f));
        clearButton.setBackground(colorTheme.getAccColorLight());
        clearButton.setForeground(colorTheme.getTextColor());

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.clearHistory();
                new Homepage();
                dispose();
            }
        });

        setIconImage(new ImageIcon("src/resources/images/spotify.png").getImage());
        setTitle("History");

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
        gbc.gridx=0;
        gbc.gridy=2;
        add(clearButton,gbc);

        setExtendedState(JFrame.MAXIMIZED_BOTH);


    }


}
