package easynotes;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import easynotes.controllers.WindowController;

public class Main
{

	public static void main(String[] args)
	{
		
		// Set System look and feel
        try {
        	
			UIManager.setLookAndFeel(
				UIManager.getSystemLookAndFeelClassName()
			);
			
		} catch (ClassNotFoundException | InstantiationException
			| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// No need to do anything if it fails, just go with default L&F
		}
		
		SwingUtilities.invokeLater(
			new Runnable()
			{
				public void run()
				{
					new WindowController();
				}
			}
		);
		
	}

}
