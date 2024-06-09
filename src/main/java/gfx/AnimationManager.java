package gfx;

import core.Direction;
import game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimationManager {

    private SpriteSet _spriteSet;
    private BufferedImage _currentSheet;
    private int _updatesPerFrame;
    private int _frameTime;
    private int _frameIndex;
    private int _directionIndex = 0;
    private String _currentAnimationName;

    public AnimationManager(SpriteSet spriteSet) {
        _spriteSet = spriteSet;
        _updatesPerFrame = 20;
        _frameIndex = 0;
        _frameTime = 0;
    }

    public Image getSprite() {
        var image = _currentSheet.getSubimage(
                _frameIndex * Game.UNITS_SIZE,
                _directionIndex * Game.UNITS_SIZE,
                Game.UNITS_SIZE,
                Game.UNITS_SIZE
        );

        return ImageUtils.zoomImage(image, Game.DEFAULT_ZOOM);
    }

    public void update(Direction direction) {
        _frameTime++;
        _directionIndex = direction.getAnimationRow();
        if (_frameTime >= _updatesPerFrame) {
            _frameTime = 0;
            _frameIndex++;
            if (_frameIndex >= _currentSheet.getWidth() / Game.UNITS_SIZE) {
                _frameIndex = 0;
            }
        }
    }

    public void playAnimation(String name) {
        if(!name.equals(_currentAnimationName))
        {
            _currentSheet = (BufferedImage) _spriteSet.getSpriteSheet(name);
            _currentAnimationName = name;
            _frameIndex = 0;
        }
    }

    public void stopAnimation() {

    }
}
