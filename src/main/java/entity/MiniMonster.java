package entity;

import controller.NPCController;
import core.Direction;
import core.Vector2d;
import gfx.SpriteSheet;

public class MiniMonster extends Enemy {
    public MiniMonster(NPCController controller, SpriteSheet spriteSheet) {
        super(controller, spriteSheet, Vector2d.Zero);
        animation.setNumFrames(3, 0);
        animation.setNumFrames(5, 1);
    }

    public void setOrigin(Vector2d origin) {
        this.origin = origin;
    }

    @Override
    protected int getAnimationRow(Direction direction) {
        return 1;
    }
}
