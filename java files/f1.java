import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class f1 {
    public static void main(String[] args) {
        try {
            BufferedImage inputImage = ImageIO.read(new File("Doo.png"));
            BufferedImage outputImage = applyDerivativeFilter(inputImage);
            ImageIO.write(outputImage, "png", new File("output_derivative.png"));
            System.out.println("First-order derivative filter applied successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage applyDerivativeFilter(BufferedImage inputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 1; y < height - 1; y++)
            for (int x = 1; x < width - 1; x++) {
                int gx = getGradientX(inputImage, x, y);
                int gy = getGradientY(inputImage, x, y);
                int gradient = (int) Math.sqrt(gx * gx + gy * gy);
                outputImage.setRGB(x, y, (gradient << 16) | (gradient << 8) | gradient);
            }

        return outputImage;
    }

    private static int getGradientX(BufferedImage image, int x, int y) {
        return (getGray(image, x + 1, y - 1) + 2 * getGray(image, x + 1, y) +
                getGray(image, x + 1, y + 1)) - (getGray(image, x - 1, y - 1) +
                2 * getGray(image, x - 1, y) + getGray(image, x - 1, y + 1));
    }

    private static int getGradientY(BufferedImage image, int x, int y) {
        return (getGray(image, x - 1, y + 1) + 2 * getGray(image, x, y + 1) +
                getGray(image, x + 1, y + 1)) - (getGray(image, x - 1, y - 1) +
                2 * getGray(image, x, y - 1) + getGray(image, x + 1, y - 1));
    }

    private static int getGray(BufferedImage image, int x, int y) {
        return (image.getRGB(x, y) >> 16) & 0xFF;
    }
}
