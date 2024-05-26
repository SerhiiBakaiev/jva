package models;

import graphic.core.IGraphicContext;

import java.awt.*;

public interface IGameObject extends IShape
{
    void draw(IGraphicContext graphicContext);
    void update(IGraphicContext graphicContext);
}
