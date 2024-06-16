package map;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Tile {
    private BufferedImage _sprite;

    public  Tile(BufferedImage image) {
        _sprite = image;
    }

    public Image getSprite(){
        return  _sprite;
    }

}
