import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FrameWorker extends JFrame
{
	public FrameWorker(String s)
	{
		this.setTitle(s);
	}
	
	public void paint(Graphics g)
	{
		try {
			Image bg = ImageIO.read(new File("sprites/FrameBackground.png"));
			g.drawImage(bg, 0, 0, this);
		} catch (IOException e) {
			System.out.println("FrameBackground.png missing!!");
		}
	}
}
