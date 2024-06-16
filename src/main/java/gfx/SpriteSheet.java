package gfx;

import core.Vector2d;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class SpriteSheet {
    private Sprite spriteSheet;
    private Sprite[][] spriteArray;
    private final int TILE_SIZE = 32;
    public int w;
    public int h;
    private int wSprite;
    private int hSprite;
    private String file;

    public static Font currentFont;

    public SpriteSheet(String file, int w, int h) throws IOException {
        this.file = file;
        this.w = w;
        this.h = h;
        System.out.println("Loading: " + file + "...");
        spriteSheet = new Sprite(ImageUtils.getOrLoadImage("/sprites/player/wizardPlayer.png"));
        wSprite = spriteSheet.getImage().getWidth() / w;
        hSprite = spriteSheet.getImage().getHeight() / h;
        loadSpriteArray();
    }


    private void loadSpriteArray() {
        spriteArray = new Sprite[hSprite][wSprite];

        for (int y = 0; y < hSprite; y++) {
            for (int x = 0; x < wSprite; x++) {
                spriteArray[y][x] = getSprite(x, y);
            }
        }
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int i) {
        w = i;
        wSprite = spriteSheet.getImage().getWidth() / w;
    }

    public void setHeight(int i) {
        h = i;
        hSprite = spriteSheet.getImage().getHeight() / h;
    }

    public void setEffect(Sprite.effect e) {
        spriteSheet.setEffect(e);
    }

    public Sprite getSpriteSheet() {
        return spriteSheet;
    }

    public Sprite getSprite(int x, int y) {
        return spriteSheet.getSubimage(x * w, y * h, w, h);
    }

    public Sprite getNewSprite(int x, int y) {
        return spriteSheet.getNewSubimage(x * w, y * h, w, h);
    }

    public Sprite getSprite(int x, int y, int w, int h) {
        return spriteSheet.getSubimage(x * w, y * h, w, h);
    }

    public BufferedImage getSubimage(int x, int y, int w, int h) {
        return spriteSheet.getImage().getSubimage(x, y, w, h);
    }

    public Sprite[] getSpriteArray(int i) {
        return spriteArray[i];
    }

    public Sprite[][] getSpriteArray2() {
        return spriteArray;
    }

    public static void drawArray(Graphics2D g, ArrayList<Sprite> img, Vector2d pos, int width, int height, int xOffset, int yOffset) {
        double x = pos.getX();
        double y = pos.getY();

        for (int i = 0; i < img.size(); i++) {
            if (img.get(i) != null) {
                g.drawImage(img.get(i).getImage(), (int) x, (int) y, width, height, null);
            }

            x += xOffset;
            y += yOffset;
        }
    }

    public static void drawArray(Graphics2D g, String word, Vector2d pos, int size) {
        drawArray(g, currentFont, word, pos, size, size, size, 0);
    }

    public static void drawArray(Graphics2D g, String word, Vector2d pos, int size, int xOffset) {
        drawArray(g, currentFont, word, pos, size, size, xOffset, 0);
    }

    public static void drawArray(Graphics2D g, String word, Vector2d pos, int width, int height, int xOffset) {
        drawArray(g, currentFont, word, pos, width, height, xOffset, 0);
    }

    public static void drawArray(Graphics2D g, Font f, String word, Vector2d pos, int size, int xOffset) {
        drawArray(g, f, word, pos, size, size, xOffset, 0);
    }

    public static void drawArray(Graphics2D g, Font f, String word, Vector2d pos, int width, int height, int xOffset, int yOffset) {
        double x = pos.getX();
        double y = pos.getY();

        currentFont = f;

        for (int i = 0; i < word.length(); i++) {
            //if (word.charAt(i) != 32)
              //  g.drawImage(f.getLetter(word.charAt(i)), (int) x, (int) y, width, height, null);

            x += xOffset;
            y += yOffset;
        }
    }
}
