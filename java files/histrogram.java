import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
// import java.io.IOException; 

public class histrogram{
    public static void main(String[] args) {
        try {
            BufferedImage inputImage = ImageIO.read(new File("output987.jpg"));
            BufferedImage outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_INT_RGB);

            int[] histogram = new int[256];
            int totalPixels = inputImage.getWidth() * inputImage.getHeight();

            for (int y = 0; y < inputImage.getHeight(); y++) {
                for (int x = 0; x < inputImage.getWidth(); x++) {
                    int gray = (inputImage.getRGB(x, y) >> 16) & 0xFF;
                    histogram[gray]++;
                }
            }
            int[] cdf = new int[256];
            cdf[0] = histogram[0];
            for (int i = 1; i < 256; i++) {
                cdf[i] = cdf[i - 1] + histogram[i];
            }
            for (int y = 0; y < inputImage.getHeight(); y++) {
                for (int x = 0; x < inputImage.getWidth(); x++) {
                    int gray = (inputImage.getRGB(x, y) >> 16) & 0xFF;
                    int newGray = (int) (cdf[gray] / (double) totalPixels * 255);
                    int newRGB = (newGray << 16) | (newGray << 8) | newGray;
                    outputImage.setRGB(x, y, newRGB);
                }
            }
            ImageIO.write(outputImage, "jpg", new File("output_histogram_equalized.jpg"));
            System.out.println("Histogram equalized image saved to output_histogram_equalized.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}