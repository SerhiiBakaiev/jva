package entity;

import core.BoundingBox;
import core.Position;
import core.Size;
import display.Camera;
import game.state.State;

import java.awt.*;

public interface IGameObject
{
   void update(State state);
   Image getSprite();
   Position getPosition();
   Size getSize();
   BoundingBox getBoundingBox();
   boolean collidesWith(IGameObject other);
   Position getRenderPosition(Camera camera);
}
