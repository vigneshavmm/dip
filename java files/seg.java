import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class seg {
    public static void main(String[] args) {
        try {
            BufferedImage originalImage = ImageIO.read(new File("output8.jpg"));
            BufferedImage segmentedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        int threshold = 128; // Adjust this threshold based on your images

        for (int y = 0; y < originalImage.getHeight(); y++)
            for (int x = 0; x <originalImage.getWidth(); x++) {
                int rgb = originalImage.getRGB(x, y);
                int grayValue = rgb & 0xFF;
                int segmentValue = (grayValue > threshold) ? 255 : 0; // Foreground: White, Background: Black
                int segmentRGB = (segmentValue << 16) | (segmentValue << 8) | segmentValue;
                segmentedImage.setRGB(x, y, segmentRGB);
            }
            ImageIO.write(segmentedImage, "png", new File("segmented_00output.png"));
            System.out.println("Segmentation complete.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}   