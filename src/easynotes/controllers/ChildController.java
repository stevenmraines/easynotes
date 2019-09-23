package easynotes.controllers;

// TODO implement this class
public abstract class ChildController {
	
	protected WindowController windowController;

	public ChildController(WindowController windowController)
	{
		
		this.windowController = windowController;
		
	}
	
	public WindowController getWindowController()
	{
		
		return windowController;
		
	}
	
	public void setWindowController(WindowController windowController)
	{
		
		this.windowController = windowController;
		
	}
	
}
