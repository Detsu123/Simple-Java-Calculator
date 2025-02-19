package simplejavacalculator;

import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;

/**
 * This class returns an image from binary data.
 */
class BufferedImageCustom {
    private static final String ICON_PATH = "/resources/icon/icon.png";

    public Image imageReturn() throws IOException {
        try (InputStream bis = getClass().getResourceAsStream(ICON_PATH)) {
            if (bis == null) {
                throw new FileNotFoundException("Icon resource not found: " + ICON_PATH);
            }
            BufferedImage bImage = ImageIO.read(bis);
            return bImage;
        }
    }
}
