import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Arrays;

public class med {
    public static void main(String[] args) {
        File inputImageFile = new File("Doo.png"); // Input Photo File
        File outputImageFile = new File("ou.jpg");
        try {
            BufferedImage img = ImageIO.read(inputImageFile);
            for (int i = 1; i < img.getWidth() - 1; i++) {
                for (int j = 1; j < img.getHeight() - 1; j++) {
                    Color[] pixel = new Color[9];
                    int[] R = new int[9];
                    int[] B = new int[9];
                    int[] G = new int[9];
                    // Gather the colors of the 3x3 neighborhood
                    int pixelIndex = 0;
                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            pixel[pixelIndex] = new Color(img.getRGB(i + x, j + y));
                            R[pixelIndex] = pixel[pixelIndex].getRed();
                            B[pixelIndex] = pixel[pixelIndex].getBlue();
                            G[pixelIndex] = pixel[pixelIndex].getGreen();
                            pixelIndex++;
                        }
                    }
                    // Sort the color channels
                    Arrays.sort(R);
                    Arrays.sort(G);
                    Arrays.sort(B);
                    // Set the central pixel to the median values
                    img.setRGB(i, j, new Color(R[4], B[4], G[4]).getRGB());
                }
            }
            // Save the filtered image
            ImageIO.write(img, "jpg", outputImageFile);
            System.out.println("Median filter applied, and the image is saved successfully as 'output.jpg'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}