package com.attendance_manager.components.styles;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CustomBorder {
    public CustomBorder() {}

    public CompoundBorder assignBorder(Color color, int thickness, int top, int left, int bottom, int right) {
        Border lineBorder = BorderFactory.createLineBorder(color, thickness);
        Border emptyBorder = new EmptyBorder(top, left, bottom, right);
        return new CompoundBorder(lineBorder, emptyBorder);
    }
}
