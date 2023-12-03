import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class rw {
    public static void main(String[] args) {
        try {
            BufferedImage image = ImageIO.read(new File("Lenna_(test_image).png"));
            ImageIO.write(image, "PNG", new File("output10.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} 