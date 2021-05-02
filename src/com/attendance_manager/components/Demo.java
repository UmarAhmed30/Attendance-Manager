package com.attendance_manager.components;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Demo extends JFrame {

    public com.attendance_manager.components.TProgressBar ctl_prb = new com.attendance_manager.components.TProgressBar();

    public void formWindowOpenend(WindowEvent evt) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<=100;i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Logger.getLogger(Demo.class.getName()).log(Level.SEVERE,null,e);
                    }
                    ctl_prb.RenderProgress(i);
                }
            }
        }).start();
    }

//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new Demo().setVisible(true);
//            }
//        });
//    }
}
