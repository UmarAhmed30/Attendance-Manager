package com.attendance_manager.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class TProgressBar extends JPanel {

    int progress = 0;

    @Override
    protected void paintComponent(Graphics g) {
        AffineTransform at = new AffineTransform();
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        AffineTransform save_ctx = g2.getTransform();
        AffineTransform current_ctx = new AffineTransform();
        current_ctx.concatenate(at);
        current_ctx.translate(getWidth()/2,getHeight()/2);
        g2.transform(current_ctx);
        g2.setColor(Color.red);
        g2.drawArc(-(getHeight()-20/2)/2, -(getHeight()-20/2)/2, getHeight()-10/2, getHeight()-10/2, 0, (int) (progress*3.6));
        g2.transform(save_ctx);
    }

    public void RenderProgress(int current_progress) {
        this.progress = current_progress;
        repaint();
    }
}
