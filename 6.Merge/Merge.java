import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Merge{
    public static void main(String[] args) {
        try {
            BufferedImage foregroundImage = ImageIO.read(new File("output.jpg"));
            BufferedImage backgroundImage = ImageIO.read(new File("foreblackandwhite copy.jpg"));

            int width = backgroundImage.getWidth();
            int height = backgroundImage.getHeight();
            BufferedImage mergedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2d = mergedImage.createGraphics();
            g2d.drawImage(backgroundImage, 0, 0, null);

            int foregroundX = 100; 
            int foregroundY = 100; 

            g2d.drawImage(foregroundImage, foregroundX, foregroundY, null);
            g2d.dispose();

            ImageIO.write(mergedImage, "PNG", new File("out.png"));
            System.out.println("Merged image saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}