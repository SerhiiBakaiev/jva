package entity.npc;

import controller.EntityController;
import core.Vector2d;
import entity.IGameObject;
import game.state.State;
import gfx.ResourceProvider;

import java.awt.*;
import java.awt.image.BufferedImage;


public class OldMan extends Npc{

    public OldMan(EntityController controller, Vector2d origin) {
        super(controller, origin);

    }

    @Override
    protected void handleCollision(IGameObject gameObject) {

    }


    @Override
    public void render(State state, Graphics2D g) {

    }
}
