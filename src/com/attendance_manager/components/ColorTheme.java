package com.attendance_manager.components;
import java.awt.*;

public class ColorTheme {
    Color priColor = Color.decode("#191414");
    Color accColorLight = Color.decode("#1ed760");
    Color accColorDark = Color.decode("#1db954");
    Color textColor = Color.decode("#ffffff");
    Color darkTransColor = new Color(255, 255, 255, 10);

    public Color getDarkTransColor() {
        return darkTransColor;
    }

    public Color getLightTransColor() {
        return lightTransColor;
    }

    Color lightTransColor = new Color(255, 255, 255, 5);

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
