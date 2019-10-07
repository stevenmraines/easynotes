package easynotes.components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import easynotes.layouts.ModifiedFlowLayout;

public class CorkboardPanel extends JPanel
{

	private static final long serialVersionUID = -6854211888632704237L;
	private BufferedImage background;
	private boolean paintBackgroundImage;
	
	public CorkboardPanel()
	{
		super(new ModifiedFlowLayout());
		paintBackgroundImage = false;
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		
		super.paintComponent(g);
		
		if(paintBackgroundImage) {
			
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

//	public BufferedImage getBackground() {
//		return background;
//	}

	public void setBackground(BufferedImage background) {
		this.background = background;
	}

	public boolean isPaintBackgroundImage() {
		return paintBackgroundImage;
	}

	public void setPaintBackgroundImage(boolean paintBackgroundImage) {
		this.paintBackgroundImage = paintBackgroundImage;
		this.revalidate();
		this.repaint();
	}

}
