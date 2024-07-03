package entity;

import controller.NPCController;
import core.Vector2d;
import gfx.SpriteSheet;

import java.io.IOException;

public class MonsterFactory {
    private static MonsterFactory instance;
    private final SpriteSheet spriteSheet;

    private  MonsterFactory() {
        try {
            this.spriteSheet = new SpriteSheet(
                    "/sprites/enemy/minimonsters.png",
                    16,
                    16
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static MonsterFactory getInstance(){
        if(instance == null){
            instance = new MonsterFactory();
        }
        return instance;
    }

    public MiniMonster createMiniMonster(int id){
        SpriteSheet sheet = new SpriteSheet(
            spriteSheet.getSprite(
                    0,
                    id,
                    128,
                    32
            ),
            16,
            16
        );
        return new MiniMonster(
                new NPCController(),
                sheet
        );
    }
}
