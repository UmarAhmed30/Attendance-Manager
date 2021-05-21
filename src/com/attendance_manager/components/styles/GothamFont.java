package com.attendance_manager.components.styles;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GothamFont {

    public GothamFont() {}

    public Font assignFont(String type, float size) {
        Font gothamFont;

        try{
            gothamFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/fonts/" + type + ".ttf")).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/fonts/" + type + ".ttf")));
            return gothamFont;
        } catch (IOException | FontFormatException e) {
            System.out.println("Font not found!");
            return null;
        }
    }
}
