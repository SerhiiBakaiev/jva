package display;

import core.Vector2d;
import entity.IGameObject;

import game.Game;
import game.state.State;
import map.GameMap;
import map.Tile;
import ui.UIContainer;

import java.awt.*;

public class Renderer implements IRenderer {

    private Display _display;
    public Renderer(Display display){
        _display = display;
    }

    @Override
    public  void render(State state, Graphics graphics) {
        Camera camera = state.getCamera();
        renderMap2(camera, state.getGameMap(), graphics);
        renderObjects(
                state,
                state.getGameObjects(),
                graphics);
        renderUi(state.getUiContainers(), graphics);
    }

    private void renderUi(java.util.List<UIContainer> elements, Graphics graphics){
        elements.forEach(e ->{
            Image sprite = e.getSprite();
            Vector2d position = e.getPosition();
            graphics.drawImage(sprite, (int)position.getX(), (int)position.getY(), null);
        });
    }

    private void renderMap2(Camera camera, GameMap map, Graphics graphics) {
        Vector2d cameraPosition = camera.getPosition();
        Tile[][] tiles = map.getTiles();
        int mapWidth = tiles.length;
        int mapHeight = tiles[0].length;

        // Рассчитаем начало и конец области отрисовки
        int startX = (int)cameraPosition.getX() / Game.SPRITE_SIZE - 1;
        int startY = (int)cameraPosition.getX() / Game.SPRITE_SIZE - 1;
        int endX = (int)(cameraPosition.getX() + _display.getHeight()) / Game.SPRITE_SIZE + 1;
        int endY = (int)(cameraPosition.getY() + _display.getWidth()) / Game.SPRITE_SIZE+ 1;
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                // Используем модульную арифметику для получения повторяющихся тайлов
                int tileX = (x % mapWidth + mapWidth) % mapWidth;
                int tileY = (y % mapHeight + mapHeight) % mapHeight;
                Tile tile = tiles[tileX][tileY];

                graphics.drawImage(
                        tile.getSprite(),
                        (int)(x * Game.SPRITE_SIZE - cameraPosition.getX()),
                        (int)(y * Game.SPRITE_SIZE - cameraPosition.getY()),
                        null
                );
            }
        }
    }

    private  void renderObjects(
            State state,
            java.util.List<IGameObject> gameObjectList,
            Graphics graphics) {
        Camera camera = state.getCamera();
        gameObjectList.stream()
                .filter(camera::isInView)
                .forEach(gameObject -> {
                    Graphics2D graphics2D = (Graphics2D)graphics;
                    gameObject.render(state, graphics2D);
                });
    }
}
