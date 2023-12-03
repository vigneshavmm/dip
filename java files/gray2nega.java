import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class gray2nega {
public static void main(String[] args) {
try {
BufferedImage grayscaleImage = ImageIO.read(new File("output8.jpg"));
BufferedImage negativeImage = convertToNegative(grayscaleImage);
ImageIO.write(negativeImage, "png", new File("negative_output3.png"));
System.out.println("Negative image processing complete.");
} catch (IOException e) {
e.printStackTrace();
}
}
private static BufferedImage convertToNegative(BufferedImage grayscaleImage) {
int width = grayscaleImage.getWidth();
int height = grayscaleImage.getHeight();
BufferedImage negativeImage = new BufferedImage(width, height,         BufferedImage.TYPE_INT_RGB);

for (int y = 0; y < height; y++)
for (int x = 0; x < width; x++) {
int rgb = grayscaleImage.getRGB(x, y);
int grayValue = rgb & 0xFF;

int negativeGrayValue = 255 - grayValue;
int negativeRGB = (negativeGrayValue << 16) | (negativeGrayValue << 8) | negativeGrayValue;
negativeImage.setRGB(x, y, negativeRGB);
}
return negativeImage;
}
}
