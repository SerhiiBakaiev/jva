package map;

import core.Size;
import core.Vector2d;
import display.Camera;
import game.Game;
import gfx.ResourceProvider;

import java.awt.*;
import java.util.Arrays;

public class GameMap {

    private final int _cols;
    private final int _rows;
    private final Tile[][] _tiles;

    public GameMap(Size size){
        _cols = size.getHeight();
        _rows = size.getWidth();
        _tiles = new Tile[_cols][_rows];
        for(Tile[] row: _tiles){
            Arrays.fill(row, new MudTile(ResourceProvider.getInstance().getTileLibrary()));
        }
    }

    public Tile[][] getTiles(){
        return _tiles;
    }
    public void render(Graphics graphics, Camera camera){

    }

    public Vector2d getViewStartPosition(Camera camera){

        return camera.getPosition().divide(Game.SPRITE_SIZE);
    }
    public Vector2d getViewEndPosition(Camera camera){
        return  Vector2d.of(
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

    public Vector2d getRandomPosition() {
        double x = Math.random() * getWidth();
        double y = Math.random() * getHeight();
        return Vector2d.of(x, y);
    }
}
