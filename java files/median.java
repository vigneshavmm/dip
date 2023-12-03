import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Arrays;

public class median {
    public static void main(String[] args) {
        try {
            BufferedImage image = ImageIO.read(new File("Lenna_(test_image).png"));
            ImageIO.write(applyMedianFilter(image, 3), "jpg", new File("output.jpg"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static BufferedImage applyMedianFilter(BufferedImage image, int filterSize) {
        int w = image.getWidth(), h = image.getHeight();
        BufferedImage f = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++)
                f.setRGB(x, y, getMedianRGB(image, x, y, filterSize));

        return f;
    }
    public static int getMedianRGB(BufferedImage image, int x, int y, int filterSize) {
        int[] red = new int[filterSize * filterSize];
        int[] green = new int[filterSize * filterSize];
        int[] blue = new int[filterSize * filterSize];
        int i = 0;

        for (int fy = -filterSize; fy <= filterSize; fy++)
            for (int fx = -filterSize; fx <= filterSize; fx++) {
                int ix = x + fx, iy = y + fy;
                if (ix >= 0 && ix < image.getWidth() && iy >= 0 && iy < image.getHeight()) {
                    int rgb = image.getRGB(ix, iy);
                    red[i] = (rgb >> 16) & 0xFF;
                    green[i] = (rgb >> 8) & 0xFF;
                    blue[i] = rgb & 0xFF;
                    i++;
                }
            }
        Arrays.sort(red);
        Arrays.sort(green);
        Arrays.sort(blue);

        int ih = i / 2;
        return (red[ih] << 16) | (green[ih] << 8) | blue[ih];
    }
}