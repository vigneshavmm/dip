import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class gray {
    public static void main(String[] args) {
        try {
            BufferedImage colorImage = ImageIO.read(new File("output_bwi.jpg"));
            BufferedImage grayscaleImage = new BufferedImage(colorImage.getWidth(), colorImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
            grayscaleImage.getGraphics().drawImage(colorImage, 0, 0, null);
            File output = new File("output_grayscale.jpg");
            ImageIO.write(grayscaleImage, "jpg", output);
            System.out.println("Grayscale image saved to output_grayscale.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
