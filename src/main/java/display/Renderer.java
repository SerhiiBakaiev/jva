package display;

import core.Position;
import core.Size;
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
            graphics.drawImage(sprite, e.getPosition().getIntX(), e.getPosition().getIntY(), null);
        });
    }

    private void renderMap2(Camera camera, GameMap map, Graphics graphics) {
        Position cameraPosition = camera.getPosition();
        Tile[][] tiles = map.getTiles();
        int mapWidth = tiles.length;
        int mapHeight = tiles[0].length;

        // Рассчитаем начало и конец области отрисовки
        int startX = cameraPosition.getIntX() / Game.SPRITE_SIZE - 1;
        int startY = cameraPosition.getIntY() / Game.SPRITE_SIZE - 1;
        int endX = (cameraPosition.getIntX() + _display.getHeight()) / Game.SPRITE_SIZE + 1;
        int endY = (cameraPosition.getIntY() + _display.getWidth()) / Game.SPRITE_SIZE+ 1;
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                // Используем модульную арифметику для получения повторяющихся тайлов
                int tileX = (x % mapWidth + mapWidth) % mapWidth;
                int tileY = (y % mapHeight + mapHeight) % mapHeight;
                Tile tile = tiles[tileX][tileY];
                graphics.drawImage(
                        tile.getSprite(),
                        x * Game.SPRITE_SIZE - cameraPosition.getIntX(),
                        y * Game.SPRITE_SIZE - cameraPosition.getIntY(),
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
                    int x = gameObject.getRenderPosition(camera).getIntX();
                    int y = gameObject.getRenderPosition(camera).getIntY();
                    gameObject.render(state, graphics2D);
                });
    }
}
