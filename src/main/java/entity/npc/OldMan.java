package entity.npc;

import controller.EntityController;
import entity.IGameObject;
import game.state.State;
import gfx.ResourceProvider;

import java.awt.*;
import java.awt.image.BufferedImage;


public class OldMan extends Npc{

    public OldMan(EntityController controller) {
        super(controller);

    }

    @Override
    protected void handleCollision(IGameObject gameObject) {

    }


    @Override
    public void render(State state, Graphics2D g) {

    }
}
