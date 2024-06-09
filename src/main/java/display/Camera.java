package display;

import core.Position;
import core.Size;
import entity.IGameObject;
import game.state.State;
import map.GameMap;

import java.awt.*;
import java.util.Optional;

public class Camera {

    private final Position _position;
    private final Size _windowSize;
    private Optional<IGameObject> _focusedObject;
    private Rectangle _viewBounds;

    public Camera(Size windowSize) {
        _focusedObject = Optional.empty();
        _position = new Position(0, 0);
        _windowSize = windowSize;
        calculateViewBounds();
    }

    public Position getPosition() {
        return _position;
    }

    public Size getSize(){
        return _windowSize;
    }

    public boolean isInView(IGameObject gameObject){
        return  _viewBounds.intersects(
                gameObject.getPosition().getIntX(),
                gameObject.getPosition().getIntY(),
                gameObject.getSize().getWidth(),
                gameObject.getSize().getHeight()
        );
    }

    public void focusOn(IGameObject object) {
        _focusedObject = Optional.of(object);
    }

    public void update(State state) {
        if (_focusedObject.isEmpty())
            return;
        Position objectPosition = _focusedObject.get().getPosition();
        _position.setXY(
                objectPosition.getX() - _windowSize.getWidth() / 2.0,
                objectPosition.getY() - _windowSize.getHeight() / 2.0
        );
        //clipToBound(state);
        calculateViewBounds();
    }

    private void calculateViewBounds() {
        _viewBounds = new Rectangle(_position.getIntX(), _position.getIntY(), _windowSize.getWidth(), _windowSize.getHeight());
    }
    private void clipToBound(State state) {
        if (_position.getX() < 0) {
            _position.setX(0);
        }

        if (_position.getY() < 0) {
            _position.setY(0);
        }
        GameMap gameMap = state.getGameMap();
        if(_position.getX() + _windowSize.getWidth() > gameMap.getWidth()){
            _position.setX(gameMap.getWidth() - _windowSize.getWidth());
        }

        if(_position.getY() + _windowSize.getHeight() > gameMap.getHeight()){
            _position.setX(gameMap.getHeight() - _windowSize.getHeight());
        }
    }

}
