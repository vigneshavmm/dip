import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class histo {
    public static void main(String[] args) {
        try {
            String input = "Lenna_(test_image).png";
            String output = "histo.png";

            BufferedImage inputImage = ImageIO.read(new File(input));
            int width = inputImage.getWidth();
            int height = inputImage.getHeight();

            BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            int[] histogram = new int[256];

            int totalPixels = width * height;

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = inputImage.getRGB(x, y);
                    int gray = (rgb >> 16) & 0xFF; // Extract the red component as grayscale
                    histogram[gray]++;
                }
            }

            int[] cdf = new int[256];
            cdf[0] = histogram[0];
            for (int i = 1; i < 256; i++) {
                cdf[i] = cdf[i - 1] + histogram[i];
            }

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = inputImage.getRGB(x, y);
                    int gray = (rgb >> 16) & 0xFF;
                    int newGray = (int) (cdf[gray] / (double) totalPixels * 255);
                    int newRGB = (newGray << 16) | (newGray << 8) | newGray; // Create new RGB value
                    outputImage.setRGB(x, y, newRGB);
                }
            }

            ImageIO.write(outputImage, "png", new File(output));
            System.out.println("Writing Complete");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
