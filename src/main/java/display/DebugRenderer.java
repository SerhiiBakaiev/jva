package display;

import core.BoundingBox;
import core.Vector2d;
import entity.IGameObject;
import game.state.State;

import java.awt.*;

public class DebugRenderer implements IRenderer{
    @Override
    public void render(State state, Graphics graphics) {
        Camera camera = state.getCamera();
        renderObjects(camera, state.getGameObjects(), graphics);
    }
    private  void renderObjects(
            Camera camera,
            java.util.List<IGameObject> gameObjectList,
            Graphics graphics) {
        Vector2d cameraPosition = camera.getPosition();
        gameObjectList.stream()
                .filter(camera::isInView)
                .map(IGameObject::getBoundingBox)
                .forEach(b -> drawBoundingBox(b,graphics, camera));
    }

    private void drawBoundingBox(BoundingBox boundingBox, Graphics graphics, Camera camera) {
        graphics.setColor(Color.RED);
        Rectangle bounds = boundingBox.getBounds();
        Vector2d cameraPosition = camera.getPosition();
        graphics.drawRect(
                (int)(bounds.getX() - cameraPosition.getX()),
                (int)(bounds.getY() - cameraPosition.getY()),
                (int)bounds.getWidth(),
                (int)bounds.getHeight()
        );
    }
}
