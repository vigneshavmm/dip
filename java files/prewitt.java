import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class prewitt{

    public static void main(String[] args) {
        try {
            BufferedImage image = ImageIO.read(new File("Lenna_(test_image).png"));
            int width = image.getWidth();
            int height = image.getHeight();

            BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            int[][] prewittX = {
                {-1, 0, 1},
                {-1, 0, 1},
                {-1, 0, 1}
            };

            int[][] prewittY = {
                {-1, -1, -1},
                {0, 0, 0},
                {1, 1, 1}
            };

            for (int x = 1; x < width - 1; x++) {
                for (int y = 1; y < height - 1; y++) {
                    int gx = 0;
                    int gy = 0;

                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            int pixel = (image.getRGB(x + i, y + j) & 0xFF);
                            gx += prewittX[i + 1][j + 1] * pixel;
                            gy += prewittY[i + 1][j + 1] * pixel;
                        }
                    }

                    int gradient = (int) Math.sqrt(gx * gx + gy * gy);
                    int newPixel = 0xFF000000 | (gradient << 16) | (gradient << 8) | gradient;
                    result.setRGB(x, y, newPixel);
                }
            }

            ImageIO.write(result, "jpg", new File("prewitt_output.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
