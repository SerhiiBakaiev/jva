package gfx;

import game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SpriteLibrary {

    private final Map<String, SpriteSet> _units;
    private final Map<String, BufferedImage> _tiles;

    public SpriteLibrary() throws IOException {
        _units = new HashMap<>();
        _tiles = new HashMap<>();
        loadSprites();
        loadTiles();
    }

    private void loadTiles() throws IOException {
        searchTiles("sprites/tiles");
    }

    private void searchTiles(String baseFolder) throws IOException {
        loadTiles(baseFolder);
        for (String folder :  getFolderNames(baseFolder)) {
            String pathToFolder= baseFolder + "/" + folder;
            loadTiles(pathToFolder);
            searchTiles(pathToFolder);
        }
    }

    private void loadTiles(String pathToFolder) throws IOException {
        String[] images = getImagesInFolder(pathToFolder);
        for (String image : images) {
            BufferedImage img = ImageUtils.loadImage("/" + pathToFolder + "/" + image);
            img = ImageUtils.zoomImage(img, Game.DEFAULT_ZOOM);
            _tiles.put(image.substring(0, image.length() - 4), img);
        }
    }

    private void loadSprites() throws IOException {
        loadSprites("sprites/unit");
    }

    public SpriteSet getSpriteSet(String name) {
        return _units.get(name);
    }

    public  BufferedImage getTile(String name) {
        return _tiles.get(name);
    }

    private void loadSprites(String baseFolder) throws IOException {
        String[] folders = getFolderNames(baseFolder);
        for (String folder : folders) {
            SpriteSet set = new SpriteSet();
            String pathToFolder= baseFolder + "/" + folder;
            String[] sheets = getImagesInFolder(pathToFolder);
            for (String sheet : sheets) {
                set.addSpriteSheet(sheet.substring(0, sheet.length() - 4),
                        ImageUtils.loadImage("/" + pathToFolder + "/" + sheet) );
            }

            if(!set.isEmpty())
                _units.put(pathToFolder, set);
            loadSprites(pathToFolder);
        }
    }

    private String[] getImagesInFolder(String basePath) {
        URL resource = SpriteLibrary.class.getClassLoader().getResource(basePath);
        assert resource != null;
        File file = new File(resource.getFile());
        return file.list((current, name) -> new File(current, name).isFile());
    }

    private  String[] getFolderNames(String basePath) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(basePath);
        assert resource != null;
        File file = new File(resource.getFile());
        return file.list((current, name) -> new File(current, name).isDirectory());
    }

}
