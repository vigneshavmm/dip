import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Merge{
    public static void main(String[] args) {
        try {
            BufferedImage foregroundImage = ImageIO.read(new File("output_grayscale.jpg"));
            BufferedImage backgroundImage = ImageIO.read(new File("Doo.png"));
            BufferedImage mergedImage = new BufferedImage(backgroundImage.getWidth(), backgroundImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = mergedImage.createGraphics();
            g2d.drawImage(backgroundImage, 0, 0, null);
            g2d.drawImage(foregroundImage, 100, 100, null);
            g2d.dispose();
            ImageIO.write(mergedImage, "PNG", new File("out.png"));
            System.out.println("Merged image saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}