import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class sef {
    public static void main(String[] args) {
        try {
            BufferedImage originalImage = ImageIO.read(new File("out.png"));
            BufferedImage backgroundModel = ImageIO.read(new File("Doo.png"));

            int threshold = 25;
            
            BufferedImage foregroundImage = createForegroundImage(originalImage, backgroundModel, threshold);
            ImageIO.write(backgroundModel, "png", new File("j.png"));
            System.out.println("Resulting foreground image saved to: " );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static BufferedImage createForegroundImage(BufferedImage originalImage, BufferedImage backgroundModel, int threshold) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        BufferedImage foregroundImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                int alpha = Math.abs(getGrayValue(originalImage, x, y) - getGrayValue(backgroundModel, x, y)) > threshold ? 255 : 0;
                foregroundImage.setRGB(x, y, (alpha << 24) | (originalImage.getRGB(x, y) & 0x00FFFFFF));
            }
        return foregroundImage;
    }
    private static int getGrayValue(BufferedImage image, int x, int y) {
        int rgb = image.getRGB(x, y);
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;
        return (red + green + blue) / 3;
    }
}