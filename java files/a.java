import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
public class a{
    public static void main(String []args){
        try{
            BufferedImage a=ImageIO.read(new File("Doo.png"));
            BufferedImage b=new BufferedImage(a.getWidth(),a.getHeight(),BufferedImage.TYPE_INT_RGB);
            BufferedImage c=new BufferedImage(a.getWidth(),a.getHeight(),BufferedImage.TYPE_INT_RGB);
            BufferedImage d=new BufferedImage(a.getWidth(),a.getHeight(),BufferedImage.TYPE_INT_RGB);
            for(int i=0;i<a.getWidth();i++){
                for(int j=0;j<a.getHeight();j++){
                    int rgb=a.getRGB(i,j);
                   b.setRGB(i,j,(rgb & 0xFF0000)>>8);
                   c.setRGB(i,j,(rgb & 0xFF0000)>>16);
                   d.setRGB(i,j,(rgb & 0xFF0000)>>0);
                } 
            }
            ImageIO.write(b,"png",new File("x.png"));
            ImageIO.write(c,"png",new File("y.png"));
            ImageIO.write(d,"png",new File("z.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}