import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageSegmentation {

    public static void main(String[] args) {
        try {
            BufferedImage originalImage = ImageIO.read(new File("output.jpg"));
            BufferedImage segmentedImage = segmentImage(originalImage, 117); // Adjust the threshold as needed
            ImageIO.write(segmentedImage, "jpg", new File("output_segmented.jpg"));
            System.out.println("Image segmentation completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage segmentImage(BufferedImage originalImage, int threshold) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        BufferedImage segmentedImage = new BufferedImage(width, height, originalImage.getType());

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = new Color(originalImage.getRGB(i, j));
                int grayscaleValue = (color.getRed() + color.getGreen() + color.getBlue()) / 3;

                if (grayscaleValue > threshold) {
                    segmentedImage.setRGB(i, j, Color.WHITE.getRGB()); // Object region (foreground)
                } else {
                    segmentedImage.setRGB(i, j, Color.BLACK.getRGB()); // Background region
                }
            }
        }

        return segmentedImage;
    }
}
