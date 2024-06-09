package game.state;

import controller.PlayerController;
import core.Position;
import core.Size;
import display.Camera;
import entity.IGameObject;
import entity.Player2;
import game.Input;
import game.Time;
import gfx.SpriteLibrary;
import map.GameMap;
import ui.UIContainer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public  abstract  class State {
    protected final SpriteLibrary _spriteLibrary;
    protected final List<IGameObject> _gameObjects;
    protected final Input _input;
    protected GameMap _gameMap;
    protected final Camera _camera;
    protected final Time _time;
    protected final Player2 _player;
    protected List<UIContainer> _uiContainers;

    public State(Size windowSize, Input input) throws IOException {
        _input = input;
        _spriteLibrary = new SpriteLibrary();
        _gameObjects = new ArrayList<>();
        _uiContainers = new ArrayList<>();
        _camera = new Camera(windowSize);
        _time = new Time();
        _player = new Player2(new PlayerController(_input), _spriteLibrary);
        _gameObjects.add(_player);
        _camera.focusOn(_player);
    }

    public GameMap getGameMap() {
        return _gameMap;
    }

    public List<IGameObject> getGameObjects() {
        return _gameObjects;
    }

    public Camera getCamera() {
        return _camera;
    }

    public Time getTime() {
        return _time;
    }

    public Player2 getPlayer(){
        return _player;
    }

    public Position getRandomPosition() {
        return  _gameMap.getRandomPosition();
    }

    public  void  update(){
        _time.update();
        sortObjectByPosition();
        for (IGameObject gameObject : _gameObjects) {
            gameObject.update(this);
        }
        _uiContainers.forEach(c -> c.update(this));
        _camera.update(this);
    }

    private void sortObjectByPosition(){
        _gameObjects.sort(Comparator.comparing(gameObject -> gameObject.getPosition().getY()));
    }

    public List<IGameObject> getCollidingGameObject(IGameObject gameObject) {
        return _gameObjects.stream()
                .filter(other -> other.collidesWith(gameObject))
                .collect(Collectors.toList());
    }

    public List<UIContainer> getUiContainers() {
        return _uiContainers;
    }
}
