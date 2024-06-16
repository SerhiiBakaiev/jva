package gfx;

import core.Size;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;

public class ImageUtils {

    public  static final int ALPHA_OPAQUE = 1;
    public  static final int ALPHA_BIT_MASKED = 2;
    public  static final int ALPHA_BLEND = 2;
    private static final HashMap<String, BufferedImage> _cache = new HashMap<>();

    public  static BufferedImage getOrLoadImage(String path) throws IOException {
        if(_cache.containsKey(path)) {
            return _cache.get(path);
        }
        URL url = Objects.requireNonNull(ImageUtils.class.getResource(path));
        BufferedImage image =  ImageIO.read(url);
        _cache.put(path, image);
        return image;
    }

    public static BufferedImage scaleImage(BufferedImage original,int width, int height)
    {
        BufferedImage scaledImage = new BufferedImage(width,height,original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original,0,0,width,height,null);
        g2.dispose();
        return scaledImage;
    }


    public  static  BufferedImage zoomImage(BufferedImage image, int zoomLevel) {
        int w = image.getWidth(null) * zoomLevel;
        int h = image.getHeight(null) * zoomLevel;
        return zoomImage(image, w, h);
    }

    public  static  BufferedImage zoomImage(BufferedImage image, int w, int h) {
        BufferedImage newImage = new BufferedImage(w, h, image.getType());
        var graphics = newImage.createGraphics();
        graphics.drawImage(image, 0,0, w,h, null);
        graphics.dispose();
        return newImage;
    }

    public  static  BufferedImage createCompatibleImage(Size size, int transparency) {
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        return gc.createCompatibleImage(size.getWidth(), size.getHeight(), transparency);
    }
}
