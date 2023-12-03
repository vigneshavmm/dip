import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;

public class mean {
    public static void main(String[] args) {
        try {
            BufferedImage i = ImageIO.read(new File("Lenna_(test_image).png"));
            ImageIO.write(apply(i, 3), "jpg", new File("output78.jpg"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static BufferedImage apply(BufferedImage i, int s) {
        int w = i.getWidth(), h = i.getHeight();
        BufferedImage f = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++)
                f.setRGB(x, y, getRGB(i, x, y, s));

        return f;
    }

    public static int getRGB(BufferedImage i, int x, int y, int s) {
        int r = 0, g = 0, b = 0, c = 0;

        for (int j = -s; j <= s; j++)
            for (int k = -s; k <= s; k++) {
                int px = x + k, py = y + j;

                if (px >= 0 && px < i.getWidth() && py >= 0 && py < i.getHeight()) {
                    int rgb = i.getRGB(px, py);
                    r += (rgb >> 16) & 0xFF;
                    g += (rgb >> 8) & 0xFF;
                    b += rgb & 0xFF;
                    c++;
                }
            }

        return ((r / c) << 16) | ((g / c) << 8) | (b / c);
    }
}