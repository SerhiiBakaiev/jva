package map;

import core.Position;
import core.Size;
import display.Camera;
import game.Game;
import gfx.ImageUtils;
import gfx.SpriteLibrary;

import java.awt.*;
import java.util.Arrays;

public class GameMap {

    private final int _cols;
    private final int _rows;
    private final Tile[][] _tiles;

    public GameMap(Size size, SpriteLibrary spriteLibrary){
        _cols = size.getHeight();
        _rows = size.getWidth();
        _tiles = new Tile[_cols][_rows];
        for(Tile[] row: _tiles){
            Arrays.fill(row, new MudTile(spriteLibrary));
        }
    }

    public Tile[][] getTiles(){
        return _tiles;
    }
    public void render(Graphics graphics, Camera camera){

    }

    public Position getViewStartPosition(Camera camera){
        return  new Position(
            camera.getPosition().getX()  / Game.SPRITE_SIZE,
            camera.getPosition().getY()  / Game.SPRITE_SIZE
        );
    }
    public Position getViewEndPosition(Camera camera){
        return  new Position(
                camera.getPosition().getX()  / Game.SPRITE_SIZE + (double) camera.getSize().getWidth() / Game.SPRITE_SIZE,
                camera.getPosition().getY()  / Game.SPRITE_SIZE + (double) camera.getSize().getHeight() / Game.SPRITE_SIZE
        );
    }
    public double getWidth() {
        return _cols * Game.SPRITE_SIZE;
    }

    public double getHeight() {
        return _rows * Game.SPRITE_SIZE;
    }

    public Position getRandomPosition() {
        double x = Math.random() *getWidth();
        double y = Math.random() * getHeight();
        return  new Position(x, y);
    }
}
