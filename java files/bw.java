import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class bw{
	public static void main(String[] args){
		try{
	BufferedImage image=ImageIO.read(new File("Doo.png"));
	BufferedImage black= new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_BYTE_BINARY);
	black.getGraphics().drawImage(image,0,0,null);
	ImageIO.write(black,"png",new File("black1.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}