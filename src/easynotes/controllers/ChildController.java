package easynotes.controllers;

public abstract class ChildController
{
	
	// Register parent controller
	protected WindowController windowController;

	public ChildController(WindowController windowController)
	{
		
		// Initialize properties
		this.windowController = windowController;
		
	}
	
	/*
	 * Setters and getters
	 */
	public WindowController getWindowController()
	{
		
		return windowController;
		
	}
	
	public void setWindowController(WindowController windowController)
	{
		
		this.windowController = windowController;
		
	}
	
}
