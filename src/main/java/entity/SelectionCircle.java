package entity;

import core.BoundingBox;
import core.Size;
import game.state.State;
import gfx.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SelectionCircle extends GameObject {

    private Color color;
    private BufferedImage sprite;

    public SelectionCircle() {
        color = Color.RED;
        _size = new Size(32,16);
        initilize();
    }

    @Override
    public void update(State state) {

    }

    @Override
    public Image getSprite() {
        return sprite;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return BoundingBox.of(getPosition(), getSize());
    }

    private void initilize(){
        sprite = ImageUtils.createCompatibleImage(_size, ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D grpahics = sprite.createGraphics();
        grpahics.setColor(color);
        grpahics.fillOval(0,0,_size.getWidth(),_size.getHeight());
        grpahics.dispose();
    }
}
