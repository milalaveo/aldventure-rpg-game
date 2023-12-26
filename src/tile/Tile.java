package tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Tile {
    public BufferedImage image;
    public boolean Collision = false;


    public Tile(String path) {
        try {
            InputStream s = getClass().getResourceAsStream(path);

            if (s != null) {
                image = ImageIO.read(s);
            }
        } catch (IOException e) {
            image = null;
        }
    }

    public Tile() {}
}
