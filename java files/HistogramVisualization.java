import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class HistogramVisualization extends JPanel {
    private int[] histogram = new int[256];

    public HistogramVisualization(BufferedImage image) {
        calculateHistogram(image);
    }

    private void calculateHistogram(BufferedImage image) {
        Arrays.fill(histogram, 0);

        ColorModel colorModel = image.getColorModel();
        WritableRaster raster = image.getRaster();

        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = colorModel.getRed(raster.getDataElements(x, y, null));
                histogram[pixel]++;
            }
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int maxCount = Arrays.stream(histogram).max().getAsInt();
        int histogramHeight = 200;

        for (int i = 0; i < 256; i++) {
            int barHeight = (int) ((double) histogram[i] / maxCount * histogramHeight);
            g.setColor(Color.black);
            g.drawLine(i, histogramHeight, i, histogramHeight - barHeight);
        }
    }

    public static void main(String[] args) {
        try {
            BufferedImage image = ImageIO.read(new File("Lenna_(test_image).png"));

            JFrame frame = new JFrame("Histogram Visualization");
            HistogramVisualization histogramPanel = new HistogramVisualization(image);

            frame.add(histogramPanel);
            frame.setSize(256, 256);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
