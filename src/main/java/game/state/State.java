package game.state;

import controller.PlayerController;
import core.Size;
import core.Vector2d;
import display.Camera;
import entity.IGameObject;
import entity.Player2;
import game.Input;
import game.Time;
import map.GameMap;
import ui.UIContainer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public  abstract  class State {

    protected final List<IGameObject> gameObjects;
    protected final Input _input;
    protected GameMap _gameMap;
    protected final Camera _camera;
    protected final Time _time;
    protected final Player2 _player;
    protected List<UIContainer> uiContainers;

    public State(Size windowSize, Input input) throws IOException {
        _input = input;
        gameObjects = new ArrayList<>();
        uiContainers = new ArrayList<>();
        _camera = new Camera(windowSize);
        _time = new Time();
        _player = new Player2(new PlayerController(_input));
        gameObjects.add(_player);
        _camera.focusOn(_player);
    }

    public GameMap getGameMap() {
        return _gameMap;
    }

    public List<IGameObject> getGameObjects() {
        return gameObjects;
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

    public Vector2d getRandomPosition() {
        return  _gameMap.getRandomPosition();
    }

    public  void  update(){
        _time.update();
        sortObjectByPosition();
        for (IGameObject gameObject : gameObjects) {
            gameObject.update(this);
        }
        uiContainers.forEach(c -> c.update(this));
        _camera.update(this);
    }

    private void sortObjectByPosition(){
        gameObjects.sort(Comparator.comparing(gameObject -> gameObject.getOrigin().getY()));
    }

    public List<IGameObject> getCollidingGameObject(IGameObject gameObject) {
        return gameObjects.stream()
                .filter(other -> other.collidesWith(gameObject))
                .collect(Collectors.toList());
    }

    public List<UIContainer> getUiContainers() {
        return uiContainers;
    }


    public void spawn(IGameObject gameObject) {
        gameObjects.add(gameObject);
    }
}
