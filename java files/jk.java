import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
public class jk {
    public static void main(String[] args) {
        try {
            BufferedImage originalImage = ImageIO.read(new File("Doo.png"));
            BufferedImage meanFiltered = applyMeanFilter(originalImage, 3);
            BufferedImage medianFiltered = applyMedianFilter(originalImage, 3);
            ImageIO.write(meanFiltered, "jpg", new File("mean_filtered.jpg"));
            ImageIO.write(medianFiltered, "jpg", new File("median_filtered.jpg"));
            System.out.println("Filters applied and images saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static BufferedImage applyMeanFilter(BufferedImage img, int filterSize) {
        int w = img.getWidth(), h = img.getHeight();
        BufferedImage filtered = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++)
                filtered.setRGB(x, y, calculateMeanColor(img, x, y, filterSize).getRGB());
        return filtered;
    }
    public static BufferedImage applyMedianFilter(BufferedImage img, int filterSize) {
        int w = img.getWidth(), h = img.getHeight();
        BufferedImage filtered = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++)
                filtered.setRGB(x, y, calculateMedianColor(img, x, y, filterSize).getRGB());
        return filtered;
    }
    public static Color calculateMeanColor(BufferedImage img, int x, int y, int filterSize) {
        int totalPixels = filterSize * filterSize;
        int rSum = 0, gSum = 0, bSum = 0;
        int halfSize = filterSize / 2;
        for (int dy = -halfSize; dy <= halfSize; dy++)
            for (int dx = -halfSize; dx <= halfSize; dx++) {
                int px = Math.min(Math.max(x + dx, 0), img.getWidth() - 1);
                int py = Math.min(Math.max(y + dy, 0), img.getHeight() - 1);
                Color pixelColor = new Color(img.getRGB(px, py));
                rSum += pixelColor.getRed();
                gSum += pixelColor.getGreen();
                bSum += pixelColor.getBlue();
            }
        return new Color(rSum / totalPixels, gSum / totalPixels, bSum / totalPixels);
    }
    public static Color calculateMedianColor(BufferedImage img, int x, int y, int filterSize) {
        int[] rValues = new int[filterSize * filterSize];
        int[] gValues = new int[filterSize * filterSize];
        int[] bValues = new int[filterSize * filterSize];
        int halfSize = filterSize / 2;
        int index = 0;
        for (int dy = -halfSize; dy <= halfSize; dy++)
            for (int dx = -halfSize; dx <= halfSize; dx++) {
                int px = Math.min(Math.max(x + dx, 0), img.getWidth() - 1);
                int py = Math.min(Math.max(y + dy, 0), img.getHeight() - 1);
                Color pixelColor = new Color(img.getRGB(px, py));
                rValues[index] = pixelColor.getRed();
                gValues[index] = pixelColor.getGreen();
                bValues[index] = pixelColor.getBlue();
                index++;
            }
        java.util.Arrays.sort(rValues);
        java.util.Arrays.sort(gValues);
        java.util.Arrays.sort(bValues);
        int medianRed = rValues[rValues.length / 2];
        int medianGreen = gValues[gValues.length / 2];
        int medianBlue = bValues[bValues.length / 2];
        return new Color(medianRed, medianGreen, medianBlue);
    }
}