package gfx;

import core.Size;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ImageUtils {

    public  static final int ALPHA_OPAQUE = 1;
    public  static final int ALPHA_BIT_MASKED = 2;
    public  static final int ALPHA_BLEND = 2;
    public  static BufferedImage loadImage(String path) throws IOException {
        URL url = Objects.requireNonNull(ImageUtils.class.getResource(path));
        return ImageIO.read(url);
    }

    public  static  BufferedImage zoomImage(BufferedImage image, int zoomLevel) {
        int w = image.getWidth(null) * zoomLevel;
        int h = image.getHeight(null) * zoomLevel;
        BufferedImage newImage = new BufferedImage(w, h, image.getType());
        var graphics = newImage.createGraphics();
        graphics.drawImage(image, 0,0, h,w, null);
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
