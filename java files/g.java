import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class g {
    public static void main(String[] args) {
        try {
            // Read the color image
            BufferedImage colorImage = ImageIO.read(new File("Doo.png"));

            // Convert to grayscale
            BufferedImage bwImage = convertToGrayScale(colorImage);

            // Save the grayscale image
            saveImage(bwImage, "output890.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage convertToGrayScale(BufferedImage colorImage) {
        int width = colorImage.getWidth();
        int height = colorImage.getHeight();
        BufferedImage bwImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = colorImage.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;
                int gray = (r + g + b) / 3;
                int grayPixel = (gray << 16) | (gray << 8) | gray;
                bwImage.setRGB(x, y, grayPixel);
            }
        }
        return bwImage;
    }

    private static void saveImage(BufferedImage image, String outputPath) throws IOException {
        File output = new File(outputPath);
        ImageIO.write(image, "jpg", output);
    }
}
