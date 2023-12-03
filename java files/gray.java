import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class gray {
    public static void main(String[] args) {
        try {
            BufferedImage colorImage = ImageIO.read(new File("Doo.png"));
            BufferedImage bwImage = new BufferedImage(colorImage.getWidth(), colorImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
            bwImage.getGraphics().drawImage(colorImage, 0, 0, null);
            ImageIO.write(bwImage, "png", new File("output888.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }
}