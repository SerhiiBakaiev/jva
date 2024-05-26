package models;

import graphic.core.IGraphicContext;
import graphic.core.KeyBoardAction;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class Player implements IGameObject {
    private int _y = 0;
    private int _x = 0;
    private final int _speed = 4;
    private final HashMap<KeyBoardAction, BufferedImage> _movementMap;
    private KeyBoardAction _lastAction = KeyBoardAction.MoveDown;

    public Player() throws IOException {
        _movementMap = new HashMap<KeyBoardAction, BufferedImage>();
        _movementMap.put(KeyBoardAction.MoveUp, LoadImage("/player/walking/boy_up_1.png"));
        _movementMap.put(KeyBoardAction.MoveDown, LoadImage("/player/walking/boy_down_1.png"));
        _movementMap.put(KeyBoardAction.MoveLeft, LoadImage("/player/walking/boy_left_1.png"));
        _movementMap.put(KeyBoardAction.MoveRight, LoadImage("/player/walking/boy_right_1.png"));
    }

    @Override
    public void draw(IGraphicContext graphicContext) {
        render(graphicContext);
    }

    @Override
    public void update(IGraphicContext graphicContext) {
        move(graphicContext.getLastAction());
    }

    private void render(IGraphicContext graphicContext) {
        var scene = graphicContext.getGraphics();
        var key = graphicContext.getLastAction();
        BufferedImage image = _movementMap.get(_lastAction);
        scene.drawImage(image, _x, _y,  16 * 3, 16 * 3, null);
    }

    private  void move(KeyBoardAction action) {
        switch (action)
        {
            case Unknown -> {
                return;
            }
            case MoveUp -> {
                _y -= _speed;
            }
            case MoveDown -> {
                _y += _speed;
            }
            case MoveLeft -> {
                _x -= _speed;
            }
            case MoveRight -> {
                _x += _speed;
            }
        }

        _lastAction = action;
    }

    private  BufferedImage LoadImage(String resourceName) throws IOException {
        try {
             var stream = getClass().getResourceAsStream(resourceName);
             return ImageIO.read(Objects.requireNonNull(stream));
        }
        catch (IOException e) {
            e.printStackTrace();
            throw  e;
        }
    }
}
