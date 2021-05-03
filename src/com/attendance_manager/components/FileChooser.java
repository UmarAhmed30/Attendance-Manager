package com.attendance_manager.components;

import com.attendance_manager.services.Client;

import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.filechooser.*;

public class FileChooser extends JFrame implements ActionListener {

    static JLabel l;

    public FileChooser() {
    }

    public void triggerDBox() {
        JFrame f = new JFrame("File Chooser");
        f.setSize(400, 400);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton openBtn = new JButton("Open");

        FileChooser f1 = new FileChooser();

        openBtn.addActionListener(f1);

        JPanel p = new JPanel();

        p.add(openBtn);

        l = new JLabel("No file selected");

        p.add(l);
        f.add(p);

        f.show();
    }

    public void actionPerformed(ActionEvent evt) {

        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        String file_path = null;

        j.setAcceptAllFileFilterUsed(false);

        j.setDialogTitle("Select a .jpeg file");

        FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .jpeg files", "jpeg");
        j.addChoosableFileFilter(restrict);

        int r = j.showOpenDialog(null);

        if (r == JFileChooser.APPROVE_OPTION) {
            l.setText(j.getSelectedFile().getAbsolutePath());
            file_path = j.getSelectedFile().getPath();
            System.out.println(file_path);
            Client client = new Client();
            try{
                client.sendRequest(file_path);
            } catch (Exception e) {
                System.out.println(e);
            }
        } else
            l.setText("The user cancelled the operation!");
    }
}
