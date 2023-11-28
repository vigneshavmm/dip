import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class colour {
    public static void main(String[] args) throws IOException {
        BufferedImage colorImage = ImageIO.read(new File("output.jpg"));
        BufferedImage redImage = new BufferedImage(colorImage.getWidth(), colorImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        BufferedImage greenImage = new BufferedImage(colorImage.getWidth(), colorImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        BufferedImage blueImage = new BufferedImage(colorImage.getWidth(), colorImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < colorImage.getHeight(); y++) {
            for (int x = 0; x < colorImage.getWidth(); x++) {
                int color = colorImage.getRGB(x, y);
                redImage.setRGB(x, y, (color & 0xFF0000) >> 0);
                greenImage.setRGB(x,y, (color & 0xFF0000)>> 8);
                blueImage.setRGB(x,y, (color & 0xFF0000)>> 16);
            }
        }
        ImageIO.write(redImage, "jpg", new File("red.jpg"));
        ImageIO.write(greenImage, "jpg", new File("green.jpg"));
        ImageIO.write(blueImage, "jpg", new File("blue.jpg"));
    }
}