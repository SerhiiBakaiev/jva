package map;

import gfx.SpriteLibrary;

import java.awt.image.BufferedImage;

public class MudTile extends Tile{
    public MudTile(SpriteLibrary spriteLibrary) {
        super(spriteLibrary.getTile("mud"));
    }
}
