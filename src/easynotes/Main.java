package easynotes;

import javax.swing.SwingUtilities;

import easynotes.controllers.WindowController;

/**
 * 
 * The Main class is the entry point of the application.
 * It contains the main method which instantiates the WindowController.
 * 
 * @author sraines
 *
 */
public class Main
{

	public static void main(String[] args)
	{
		
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
