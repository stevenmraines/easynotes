package easynotes.components;

import java.awt.Component;

import javax.swing.JScrollPane;

public class CorkboardScrollPane extends JScrollPane
{

	private static final long serialVersionUID = 4944097225535677327L;
	
	public CorkboardScrollPane()
	{
		
		super(new CorkboardPanel());
		
		// Hide the scroll pane background so the CorkboardPanel's background can be seen
		this.setOpaque(false);
		
		// Increase scroll speed
		this.getVerticalScrollBar().setUnitIncrement(16);
		
	}
	
	/*
	 * Override add and remove methods so that components are
	 * added to and removed from the inner JPanel.
	 */
	@Override
	public Component add(Component component)
	{
		
		((CorkboardPanel) this.getViewport().getView()).add(component);
		return component;
		
	}
	
	@Override
	public void remove(Component component)
	{
		
		((CorkboardPanel) this.getViewport().getView()).remove(component);
		
	}
	
	/*
	 * Setters and getters
	 */
	public CorkboardPanel getCorkboardPanel()
	{
		return (CorkboardPanel) this.getViewport().getView();
	}
	
	// TODO add corkboardpanel setter

}
