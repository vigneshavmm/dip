import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class GrayScale {
    public static void main(String[] args) {
        try {
            BufferedImage colorImage = ImageIO.read(new File("output.jpg"));
            BufferedImage bwImage = new BufferedImage(colorImage.getWidth(), colorImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
            bwImage.getGraphics().drawImage(colorImage, 0, 0, null);
            ImageIO.write(bwImage, "jpg", new File("output3.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }
}