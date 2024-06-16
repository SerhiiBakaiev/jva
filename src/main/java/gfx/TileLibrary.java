package gfx;

import game.Game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TileLibrary {


    private static TileLibrary instance;
    private final Map<String, BufferedImage> images;

    private TileLibrary() throws IOException {
        images = new HashMap<>();
        loadTiles();
    }

    public static TileLibrary getInstance(){
        if(instance == null){
            try {
                instance = new TileLibrary();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return instance;
    }

    private void loadTiles() throws IOException {
        searchTiles("sprites/tiles");
    }

    private void searchTiles(String baseFolder) throws IOException {
        loadTiles(baseFolder);
        for (String folder : getFolderNames(baseFolder)) {
            String pathToFolder = baseFolder + "/" + folder;
            loadTiles(pathToFolder);
            searchTiles(pathToFolder);
        }
    }

    private void loadTiles(String pathToFolder) throws IOException {
        String[] images = getImagesInFolder(pathToFolder);
        for (String image : images) {
            BufferedImage img = ImageUtils.getOrLoadImage("/" + pathToFolder + "/" + image);
            img = ImageUtils.zoomImage(img, Game.DEFAULT_ZOOM);
            this.images.put(image.substring(0, image.length() - 4), img);
        }
    }


    public BufferedImage getImage(String name) {
        return images.get(name);
    }

    private String[] getImagesInFolder(String basePath) {
        URL resource = TileLibrary.class.getClassLoader().getResource(basePath);
        assert resource != null;
        File file = new File(resource.getFile());
        return file.list((current, name) -> new File(current, name).isFile());
    }

    private String[] getFolderNames(String basePath) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(basePath);
        assert resource != null;
        File file = new File(resource.getFile());
        return file.list((current, name) -> new File(current, name).isDirectory());
    }

}
