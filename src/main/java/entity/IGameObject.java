package entity;

import controller.Controller;
import core.BoundingBox;
import core.Size;
import core.Vector2d;
import display.Camera;
import game.state.State;

import java.awt.*;

public interface IGameObject
{
   void update(State state);
   Vector2d getOrigin();
   Size getSize();
   BoundingBox getBoundingBox();
   boolean collidesWith(IGameObject other);
   Vector2d getRenderPosition(Camera camera);
   void setParent(IGameObject bubble);
   Controller getController();
   void render(State state, Graphics2D g);
}
