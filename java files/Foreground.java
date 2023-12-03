import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Foreground {
    public static void main(String[] args) {
        try {
            String oi = "Lenna_(test_image).png";
            String bi ="Doo.png";
            BufferedImage originalImage = ImageIO.read(new File(oi));
            BufferedImage backgroundModel = ImageIO.read(new File(bi));
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();

            System.out.println("Original Image: " + width + "x" + height);

            int[][] originalMatrix = imageToMatrix(originalImage, width, height);
            int[][] backgroundMatrix = imageToMatrix(backgroundModel, width, height);

            int[][] differenceMatrix = computeDifference(originalMatrix, backgroundMatrix, width, height);

            int threshold = 25;
            int[][] binaryMask = thresholdMatrix(differenceMatrix, width, height, threshold);

            BufferedImage foregroundImage = createForegroundImage(originalImage, binaryMask, width, height);

            showImageInFrame(foregroundImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[][] imageToMatrix(BufferedImage image, int width, int height) {
        int[][] matrix = new int[height][width];
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                matrix[y][x] = (image.getRGB(x, y) & 0xFF);

        return matrix;
    }
    private static int[][] computeDifference(int[][] matrix1, int[][] matrix2, int width, int height) {
        int[][] difference = new int[height][width];
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                difference[y][x] = Math.abs(matrix1[y][x] - matrix2[y][x]);

        return difference;
    }

    private static int[][] thresholdMatrix(int[][] matrix, int width, int height, int threshold) {
        int[][] binaryMask = new int[height][width];
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                binaryMask[y][x] = (matrix[y][x] > threshold) ? 255 : 0;

        return binaryMask;
    }

    private static BufferedImage createForegroundImage(BufferedImage originalImage, int[][] binaryMask, int width, int height) {
        BufferedImage foregroundImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = originalImage.getRGB(x, y);
                int alpha = binaryMask[y][x];
                int newRgb = (alpha << 24) | (rgb & 0x00FFFFFF);
                foregroundImage.setRGB(x, y, newRgb);
            }
        }
        return foregroundImage;
    }


    private static void showImageInFrame(BufferedImage image) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int maxWidth = screenSize.width - 100;
        int maxHeight = screenSize.height - 100;
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        int newWidth = Math.min(imageWidth, maxWidth);
        int newHeight = Math.min(imageHeight, maxHeight);

        System.out.println("Displaying Foreground Image: " + newWidth + "x" + newHeight);

        ImageIcon icon = new ImageIcon(image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
        JLabel label = new JLabel(icon);

        JFrame frame = new JFrame("Foreground Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(label);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}