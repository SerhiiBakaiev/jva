package entity;

import controller.Controller;
import core.BoundingBox;
import core.Position;
import core.Size;
import display.Camera;
import game.state.State;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface IGameObject
{
   void update(State state);
   Position getPosition();
   Size getSize();
   BoundingBox getBoundingBox();
   boolean collidesWith(IGameObject other);
   Position getRenderPosition(Camera camera);
   void setParent(IGameObject bubble);
   Controller getController();
   void render(State state, Graphics2D g);
}
