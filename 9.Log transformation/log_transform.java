import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class log_transform {
    public static void main(String[] args) {
        try {
            BufferedImage inputImage = ImageIO.read(new File("output.jpg"));
            BufferedImage outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            double c = 255 / Math.log(256);

            for (int y = 0; y < inputImage.getHeight(); y++) {
                for (int x = 0; x < inputImage.getWidth(); x++) {
                    int rgb = inputImage.getRGB(x, y);
                    int r = (rgb >> 16) & 0xFF;
                    int g = (rgb >> 8) & 0xFF;
                    int b = (rgb >>24)& 0xFF;

                    int newR = (int) (c * Math.log(1 + r));
                    int newG = (int) (c * Math.log(1 + g));
                    int newB = (int) (c * Math.log(1 + b));

                    int newRGB = (newR << 16) | (newG << 8) | newB;
                    outputImage.setRGB(x, y, newRGB);
                }
            }
            ImageIO.write(outputImage, "png", new File("output_log.png"));
            System.out.println("Log-transformed image saved to output_log.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
}