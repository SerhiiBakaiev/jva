package display;

import core.BoundingBox;
import core.Position;
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
        Position cameraPosition = camera.getPosition();
        gameObjectList.stream()
                .filter(camera::isInView)
                .map(IGameObject::getBoundingBox)
                .forEach(b -> drawBoundingBox(b,graphics, camera));
    }

    private void drawBoundingBox(BoundingBox boundingBox, Graphics graphics, Camera camera) {
        graphics.setColor(Color.RED);
        Rectangle bounds = boundingBox.getBounds();
        Position cameraPosition = camera.getPosition();
        graphics.drawRect(
                (int)bounds.getX() - cameraPosition.getIntX(),
                (int)bounds.getY() - cameraPosition.getIntY(),
                (int)bounds.getWidth(),
                (int)bounds.getHeight()
        );
    }
}
