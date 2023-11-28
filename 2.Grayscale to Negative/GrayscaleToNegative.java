import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class GrayscaleToNegative {
    public static void main(String[] args) {
        try {
            // Load the grayscale image
            BufferedImage grayscaleImage = ImageIO.read(new File("Tiger.jpg"));

            // Convert the grayscale image to a negative
            BufferedImage negativeImage = convertToNegative(grayscaleImage);

            // Save the negative image to a file
            ImageIO.write(negativeImage, "jpg", new File("Samplenegative.png"));

            System.out.println("Negative image created successfully!");
        } catch (IOException e) {
            System.out.println("Error while processing the image: " + e.getMessage());
        }
    }
    public static BufferedImage convertToNegative(BufferedImage grayscaleImage) {
        int width = grayscaleImage.getWidth();
        int height = grayscaleImage.getHeight();

        BufferedImage negativeImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = grayscaleImage.getRGB(x, y);
                int intensity = pixel & 0xFF; // Extract the intensity value (assuming grayscale image)
                int negativeIntensity = 255 - intensity; // Calculate the negative value
                int negativePixel = (negativeIntensity << 16) | (negativeIntensity << 8) | negativeIntensity; // Create the new pixel value

                negativeImage.setRGB(x, y, negativePixel);
            }
        }
        return negativeImage;
    }
}
