package display;

import core.BoundingBox;
import core.Size;
import core.Vector2d;
import entity.IGameObject;
import game.state.State;

import java.util.Optional;

public class Camera {

    private Vector2d position;
    private final Size windowSize;
    private Optional<IGameObject> focusedObject;
    private BoundingBox viewBounds;

    public Camera(Size windowSize) {
        focusedObject = Optional.empty();
        position = Vector2d.Zero;
        this.windowSize = windowSize;
        calculateViewBounds();
    }

    public Vector2d getPosition() {
        return position;
    }

    public Size getSize(){
        return windowSize;
    }

    public boolean isInView(IGameObject gameObject){
        return  viewBounds.collidesWith(gameObject.getBoundingBox());
    }

    public void focusOn(IGameObject object) {
        focusedObject = Optional.of(object);
    }

    public void update(State state) {
        if (focusedObject.isEmpty())
            return;
        Vector2d objectPosition = focusedObject.get().getOrigin();
        position = Vector2d.of(
                objectPosition.getX() - windowSize.getWidth() / 2.0,
                objectPosition.getY() - windowSize.getHeight() / 2.0
        );
        calculateViewBounds();
    }

    private void calculateViewBounds() {
        viewBounds = new BoundingBox(
                position,
                windowSize.getWidth(),
                windowSize.getHeight()
        );
    }

}
