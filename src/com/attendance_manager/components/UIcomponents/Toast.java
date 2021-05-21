package com.attendance_manager.components.UIcomponents;

import java.awt.*;
import javax.swing.*;

public class Toast extends JFrame {

    String s;
    JWindow w;

    public Toast(String s, int x, int y)
    {
        w = new JWindow();

        w.setBackground(new Color(0, 0, 0, 0));

        JPanel p = new JPanel() {
            public void paintComponent(Graphics g)
            {
                int wid = g.getFontMetrics().stringWidth(s);
                int hei = g.getFontMetrics().getHeight();

                g.setColor(Color.black);
                g.fillRect(10, 10, wid + 30, hei + 10);
                g.setColor(Color.black);
                g.drawRect(10, 10, wid + 30, hei + 10);

                g.setColor(new Color(255, 255, 255, 240));
                g.drawString(s, 25, 27);
                int t = 250;
                for (int i = 0; i < 4; i++) {
                    t -= 60;
                    g.setColor(new Color(0, 0, 0, t));
                    g.drawRect(10 - i, 10 - i, wid + 30 + i * 2,
                            hei + 10 + i * 2);
                }
            }
        };

        w.add(p);
        w.setLocation(x, y);
        w.setSize(300, 100);
    }

    public void showtoast()
    {
        try {
            w.setOpacity(1);
            w.setVisible(true);

            Thread.sleep(2000);

            for (double d = 1.0; d > 0.2; d -= 0.1) {
                Thread.sleep(100);
                w.setOpacity((float)d);
            }

            w.setVisible(false);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

