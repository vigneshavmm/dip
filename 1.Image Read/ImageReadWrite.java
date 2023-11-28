import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageReadWrite {
    public static void main(String[] args) {
        try {
            BufferedImage image = ImageIO.read(new File("output.jpg"));
            ImageIO.write(image, "PNG", new File("output1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} 

