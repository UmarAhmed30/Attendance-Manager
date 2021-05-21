package com.attendance_manager.components.styles;
import java.awt.*;

public class ColorTheme {

    Color priColor = Color.decode("#191414");
    Color accColorLight = Color.decode("#1ed760");
    Color accColorDark = Color.decode("#1db954");
    Color textColor = Color.decode("#ffffff");
    Color rounderCornerColor = Color.decode("#242424");
    Color adjustmentTone = Color.decode("#171717");
    Color darkTransColor = new Color(255, 255, 255, 10);
    Color lightTransColor = new Color(255, 255, 255, 5);

    public Color getAdjustmentTone() {
        return adjustmentTone;
    }

    public Color getRounderCornerColor() {
        return rounderCornerColor;
    }

    public Color getDarkTransColor() {
        return darkTransColor;
    }

    public Color getLightTransColor() {
        return lightTransColor;
    }

    public Color getPriColor() {
        return priColor;
    }

    public Color getAccColorLight() {
        return accColorLight;
    }

    public Color getAccColorDark() {
        return accColorDark;
    }

    public Color getTextColor() {
        return textColor;
    }

}
