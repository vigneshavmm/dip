import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class roi1 {
    public static void main(String[] args) {
        try {
            BufferedImage inputImage = ImageIO.read(new File("Doo.png"));
            BufferedImage outputImage = segmentROI(inputImage, 150); // Adjust threshold as needed
            ImageIO.write(outputImage, "png", new File("output_roi.png"));
            System.out.println("Region of Interest segmented successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static BufferedImage segmentROI(BufferedImage inputImage, int threshold) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                int grayValue = getGray(inputImage, x, y);
                int segmentedValue = (grayValue > threshold) ? 255 : 0;
                outputImage.setRGB(x, y, (segmentedValue << 16) | (segmentedValue << 8) | segmentedValue);
            }

        return outputImage;
    }
    private static int getGray(BufferedImage image, int x, int y) {
        return (image.getRGB(x, y) >> 16) & 0xFF;
    }
}
