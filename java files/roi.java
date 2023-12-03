import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class roi {
    public static void main(String[] args) {
        try {
            BufferedImage originalImage = ImageIO.read(new File("Lenna_(test_image).png"));

            // Define the color range for the region of interest
            int lowerThreshold = 100; // Adjust based on your image
            int upperThreshold = 200;

            BufferedImage segmentedROI = segmentROI(originalImage, lowerThreshold, upperThreshold);

            // Save the result
            saveImageToFile(segmentedROI, "roi_output.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage segmentROI(BufferedImage originalImage, int lowerThreshold, int upperThreshold) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        BufferedImage segmentedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                int rgb = originalImage.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                // Check if the pixel color is within the defined range
                if (red >= lowerThreshold && red <= upperThreshold &&
                        green >= lowerThreshold && green <= upperThreshold &&
                        blue >= lowerThreshold && blue <= upperThreshold) {
                    segmentedImage.setRGB(x, y, rgb); // Copy the pixel to the segmented image
                } else {
                    segmentedImage.setRGB(x, y, 0xFFFFFF); // Set non-ROI pixels to white
                }
            }

        return segmentedImage;
    }

    private static void saveImageToFile(BufferedImage image, String filePath) {
        try {
            ImageIO.write(image, "png", new File(filePath));
            System.out.println("Resulting ROI image saved to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
