import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class h {
    public static void main(String[] args) {
        try {
            BufferedImage image = ImageIO.read(new File("Doo.png"));
            int[] histogram = new int[256];
            for (int y = 0; y < image.getHeight(); y++)
                for (int x = 0; x < image.getWidth(); x++)
                    histogram[(image.getRGB(x, y) >> 16) & 0xFF]++;

            JFrame frame = new JFrame("Image Histogram");
            frame.setSize(600, 600);
            frame.add(new JPanel() {
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    int w = getWidth(), h = getHeight(), max = getMax(histogram);
                    for (int i = 0; i < 256; i++) {
                        int barH = (int) ((double) histogram[i] / max * h);
                        g.fillRect(i * w / 256, h - barH, w / 256, barH);
                    }
                }
            });
            frame.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static int getMax(int[] array) {
        int max = array[0];
        for (int value : array)
            if (value > max) max = value;
        return max;
    }
}
