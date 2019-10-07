package easynotes.components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import easynotes.layouts.ModifiedFlowLayout;

public class CorkboardPanel extends JPanel
{

	private static final long serialVersionUID = -6854211888632704237L;
	private BufferedImage background;
	private boolean backgroundPainted;
	
	public CorkboardPanel()
	{
		
		super(new ModifiedFlowLayout());
		
		backgroundPainted = true;
		
		// Prepare background image
		try {
			background = ImageIO.read(new File("img/cork.jpg"));
			setBackground(background);
		} catch(IOException e) {
			// TODO How to get WindowTemplate reference for JOptionPane here?
			JOptionPane.showMessageDialog(null, "Could not read background image file.");
		}
		
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		
		super.paintComponent(g);
		
		if(backgroundPainted) {
			
			// TODO need to get TOTAL panel height here so that the image isn't cut off when scrolling down
			// Get width and height of this panel and background image
			int w = this.getWidth();
			int h = this.getHeight();
			int bw = background.getWidth();
			int bh = background.getHeight();
			
			// Find out how many times the image needs to be repeated in each direction
			int rx = (int) Math.ceil((double) w / bw);
			int ry = (int) Math.ceil((double) h / bh);
			
			// Draw the image
			for(int i = 0; i < rx; i++) {
				for(int j = 0; j < ry; j++) {
					int x = this.getX() + i * bw;
					int y = this.getY() + j * bh;
					g.drawImage(background, x, y, null);
				}
			}
			
		}
		
	}

	// TODO add getter for background
//	public BufferedImage getBackground() {
//		return background;
//	}

	public void setBackground(BufferedImage background)
	{
		this.background = background;
	}

	public boolean isBackgroundPainted()
	{
		return backgroundPainted;
	}

	public void setBackgroundPainted(boolean backgroundPainted)
	{
		this.backgroundPainted = backgroundPainted;
	}

}
