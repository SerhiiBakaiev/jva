package org.example;

import java.awt.*;

public class Rectangle implements IShape
{
    private final int _height = 16;
    private final int _width = 16;

    @Override
    public void render(Graphics2D graphicScene) {
        graphicScene.setColor(Color.black);
        graphicScene.fillRect(0,0,_width,_height);
    }
}
