package gfx;

import java.awt.*;

public interface SpriteSet {
    String getName();
    Image getImage(int frameIndex, int directionIndex);
}
