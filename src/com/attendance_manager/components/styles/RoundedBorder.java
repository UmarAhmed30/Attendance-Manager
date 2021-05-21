package com.attendance_manager.components.styles;

import javax.swing.border.Border;
import java.awt.*;

public class RoundedBorder implements Border {

    int radius;
    Color color;

    public RoundedBorder(int radius, Color color) {
        this.radius = radius;
        this.color = color;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius-1, this.radius+6, this.radius-1, this.radius+6);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
        g2.setStroke(new BasicStroke(10));
        g2.setColor(color);
        g2.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }

}
