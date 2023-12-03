import java.io.File;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class hh {
    public static void main(String[] args){
        BufferedImage o=ImageIO.read(new File("Doo.png"));
        int[] histrogram=new int[256];
        for(int i=0;i<o.getHeight();i++)
           for(int j=0;j<o.getWidth();j++)
           histrogram[o.getRGB(i,j) & 0xFF]++;
        JFrame frame = new JFrame("image histogram");
        frame.setSize(300,200);
        
        JPanel panel = new panel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                i
            }
        }
    }
}
