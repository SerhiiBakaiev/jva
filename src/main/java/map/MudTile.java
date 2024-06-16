package map;

import gfx.TileLibrary;

public class MudTile extends Tile{
    public MudTile(TileLibrary tileLibrary) {
        super(tileLibrary.getImage("mud"));
    }
}
