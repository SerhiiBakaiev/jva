package gfx;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SpriteSet {

    private final Map<String, Image> _animationSheets;

    public SpriteSet(){
        _animationSheets = new HashMap<String, Image>();
    }

    public  boolean isEmpty(){
        return _animationSheets.isEmpty();
    }
    public  void addSpriteSheet(String sheetName, Image image){
        _animationSheets.put(sheetName, image);
    }

    public Image getSpriteSheet(String sheetName){
        return _animationSheets.get(sheetName);
    }
}
