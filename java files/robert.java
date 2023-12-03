import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class robert {
    public static void main(String[] args) {
        try {
            BufferedImage image = ImageIO.read(new File("Lenna_(test_image).png"));
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            for (int x = 0; x < width - 1; x++) {
                for (int y = 0; y < height - 1; y++) {
                    int pixel00 = (image.getRGB(x, y) & 0xFF);
                    int pixel01 = (image.getRGB(x, y + 1) & 0xFF);
                    int pixel10 = (image.getRGB(x + 1, y) & 0xFF);
                    int pixel11 = (image.getRGB(x + 1, y + 1) & 0xFF);

                    int gx = Math.abs(pixel00 - pixel11);
                    int gy = Math.abs(pixel01 - pixel10);

                    int gradient = gx + gy;

                    int newPixel = 0xFF000000 | (gradient << 16) | (gradient << 8) | gradient;
                    result.setRGB(x, y, newPixel);
                }
            }
            ImageIO.write(result, "jpg", new File("roberts_output.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}