import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
public class firstorder {
    public static void main(String[] args) {
        try {
            BufferedImage image = ImageIO.read(new File("Doo.png"));
            BufferedImage sharpenedImage = applyFirstOrderDerivative(image);
            File output = new File("output19.jpg");
            ImageIO.write(sharpenedImage, "jpg", output);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static BufferedImage applyFirstOrderDerivative(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage sharpenedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int[][] kernel = {{-1, -1, -1}, {-1, 9, -1}, {-1, -1, -1}};
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                int[] sum = new int[3];
                for (int ky = 0; ky < 3; ky++) {
                    for (int kx = 0; kx < 3; kx++) {
                        int pixel = image.getRGB(x - 1 + kx, y - 1 + ky);
                        sum[0] += ((pixel >> 16) & 0xFF) * kernel[ky][kx];
                        sum[1] += ((pixel >> 8) & 0xFF) * kernel[ky][kx];
                        sum[2] += (pixel & 0xFF) * kernel[ky][kx];
                    }
                }
                int newPixel = (clamp(sum[0]) << 16) | (clamp(sum[1]) << 8) | clamp(sum[2]);
                sharpenedImage.setRGB(x, y, newPixel);
            }
        }
        return sharpenedImage;
    }
    public static int clamp(int value) {
        return Math.max(0, Math.min(value, 255));
    }
}