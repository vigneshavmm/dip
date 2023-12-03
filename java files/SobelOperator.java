import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SobelOperator {

    public static void main(String[] args) {
        try {
            BufferedImage image = ImageIO.read(new File("Lenna_(test_image).png"));
            int width = image.getWidth();
            int height = image.getHeight();

            BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            for (int x = 1; x < width - 1; x++) {
                for (int y = 1; y < height - 1; y++) {
                    int gx = (image.getRGB(x - 1, y - 1) & 0xFF) +
                             2 * (image.getRGB(x - 1, y) & 0xFF) +
                             (image.getRGB(x - 1, y + 1) & 0xFF) -
                             (image.getRGB(x + 1, y - 1) & 0xFF) -
                             2 * (image.getRGB(x + 1, y) & 0xFF) -
                             (image.getRGB(x + 1, y + 1) & 0xFF);

                    int gy = (image.getRGB(x - 1, y - 1) & 0xFF) +
                             2 * (image.getRGB(x, y - 1) & 0xFF) +
                             (image.getRGB(x + 1, y - 1) & 0xFF) -
                             (image.getRGB(x - 1, y + 1) & 0xFF) -
                             2 * (image.getRGB(x, y + 1) & 0xFF) -
                             (image.getRGB(x + 1, y + 1) & 0xFF);

                    int gradient = (int) Math.sqrt(gx * gx + gy * gy);
                    int newPixel = 0xFF000000 | (gradient << 16) | (gradient << 8) | gradient;
                    result.setRGB(x, y, newPixel);
                }
            }

            ImageIO.write(result, "jpg", new File("sobel_output.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}