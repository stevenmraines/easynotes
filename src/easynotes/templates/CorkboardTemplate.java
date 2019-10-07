package easynotes.templates;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSlider;

import easynotes.components.CorkboardPanel;

// TODO add JSlider for enlarging cards
public class CorkboardTemplate extends JPanel
{
	
	private static final long serialVersionUID = -3447234042759312451L;
	private CorkboardPanel corkboardPanel;
	private JScrollPane scrollPane;
	private JPanel sliderPanel;
	private JLabel zoomLabel;
	private JSlider zoomSlider;
	private JPopupMenu corkboardMenu;
	private JPopupMenu cardMenu;
	private JMenuItem addCardMenuItem;
	private JMenuItem flipAllCardsMenuItem;
	private JCheckBoxMenuItem showBackgroundMenuItem;	
	private JMenuItem flipCardMenuItem;
	private JMenuItem insertBeforeMenuItem;
	private JMenuItem insertAfterMenuItem;
	private JMenuItem editCardMenuItem;
	private JMenuItem duplicateCardMenuItem;
	private JMenuItem deleteCardMenuItem;
	
	public CorkboardTemplate()
	{
		
		super();
		
		// Initialize components
		corkboardPanel = new CorkboardPanel();
		scrollPane = new JScrollPane(corkboardPanel);
		sliderPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		zoomLabel = new JLabel("Zoom:");
		zoomSlider = new JSlider();
		corkboardMenu = new JPopupMenu();
		cardMenu = new JPopupMenu();
		addCardMenuItem = new JMenuItem("Add a card");
		flipAllCardsMenuItem = new JMenuItem("Flip all cards");
		showBackgroundMenuItem = new JCheckBoxMenuItem("Show background image");
		flipCardMenuItem = new JMenuItem("Flip this card");
		insertBeforeMenuItem = new JMenuItem("Insert before");
		insertAfterMenuItem = new JMenuItem("Insert after");
		editCardMenuItem = new JMenuItem("Edit this card");
		duplicateCardMenuItem = new JMenuItem("Duplicate this card");
		deleteCardMenuItem = new JMenuItem("Delete this card");
		
		// Set layout manager
		this.setLayout(new BorderLayout());
		
		// Add components
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(sliderPanel, BorderLayout.SOUTH);
		this.add(corkboardMenu);
		sliderPanel.add(zoomLabel);
		sliderPanel.add(zoomSlider);
		corkboardMenu.add(addCardMenuItem);
		corkboardMenu.add(flipAllCardsMenuItem);
		corkboardMenu.addSeparator();
		corkboardMenu.add(showBackgroundMenuItem);
		cardMenu.add(flipCardMenuItem);
		cardMenu.add(editCardMenuItem);
		cardMenu.add(duplicateCardMenuItem);
		cardMenu.add(deleteCardMenuItem);
		cardMenu.addSeparator();
		cardMenu.add(insertBeforeMenuItem);
		cardMenu.add(insertAfterMenuItem);
		
		// Set showBackgroundMenuItem checked by default
		showBackgroundMenuItem.setSelected(true);
		
		// Hide scroll pane background
		scrollPane.setOpaque(false);
		
		// Increase scroll pane scroll speed
		scrollPane.getVerticalScrollBar().setUnitIncrement(24);
		
	}
	
	/*
	 * Override add and remove methods so that CardLabels
	 * are added to and removed from the CorkboardScrollPane
	 */
	@Override
	public Component add(Component component)
	{
		return this.scrollPane.add(component);
	}
	
	@Override
	public void remove(Component component)
	{
		this.scrollPane.remove(component);
	}

	/*
	 * Setters and getters
	 */
	public JPopupMenu getCorkboardMenu()
	{
		return corkboardMenu;
	}

	public void setCorkboardMenu(JPopupMenu corkboardMenu)
	{
		this.corkboardMenu = corkboardMenu;
	}

	public JPopupMenu getCardMenu()
	{
		return cardMenu;
	}

	public void setCardMenu(JPopupMenu cardMenu)
	{
		this.cardMenu = cardMenu;
	}

	public JMenuItem getAddCardMenuItem()
	{
		return addCardMenuItem;
	}

	public void setAddCardMenuItem(JMenuItem addCardMenuItem)
	{
		this.addCardMenuItem = addCardMenuItem;
	}

	public JMenuItem getFlipAllCardsMenuItem()
	{
		return flipAllCardsMenuItem;
	}

	public void setFlipAllCardsMenuItem(JMenuItem flipAllCardsMenuItem)
	{
		this.flipAllCardsMenuItem = flipAllCardsMenuItem;
	}

	public JCheckBoxMenuItem getShowBackgroundMenuItem()
	{
		return showBackgroundMenuItem;
	}

	public void setShowBackgroundMenuItem(JCheckBoxMenuItem showBackgroundMenuItem)
	{
		this.showBackgroundMenuItem = showBackgroundMenuItem;
	}

	public JMenuItem getFlipCardMenuItem()
	{
		return flipCardMenuItem;
	}

	public void setFlipCardMenuItem(JMenuItem flipCardMenuItem)
	{
		this.flipCardMenuItem = flipCardMenuItem;
	}

	public JMenuItem getInsertBeforeMenuItem()
	{
		return insertBeforeMenuItem;
	}

	public void setInsertBeforeMenuItem(JMenuItem insertBeforeMenuItem)
	{
		this.insertBeforeMenuItem = insertBeforeMenuItem;
	}

	public JMenuItem getInsertAfterMenuItem()
	{
		return insertAfterMenuItem;
	}

	public void setInsertAfterMenuItem(JMenuItem insertAfterMenuItem)
	{
		this.insertAfterMenuItem = insertAfterMenuItem;
	}

	public JMenuItem getEditCardMenuItem()
	{
		return editCardMenuItem;
	}

	public void setEditCardMenuItem(JMenuItem editCardMenuItem)
	{
		this.editCardMenuItem = editCardMenuItem;
	}

	public JMenuItem getDuplicateCardMenuItem()
	{
		return duplicateCardMenuItem;
	}

	public void setDuplicateCardMenuItem(JMenuItem duplicateCardMenuItem)
	{
		this.duplicateCardMenuItem = duplicateCardMenuItem;
	}

	public JMenuItem getDeleteCardMenuItem()
	{
		return deleteCardMenuItem;
	}

	public void setDeleteCardMenuItem(JMenuItem deleteCardMenuItem)
	{
		this.deleteCardMenuItem = deleteCardMenuItem;
	}

	public JScrollPane getScrollPane()
	{
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane)
	{
		this.scrollPane = scrollPane;
	}

	public JPanel getSliderPanel()
	{
		return sliderPanel;
	}

	public void setSliderPanel(JPanel sliderPanel)
	{
		this.sliderPanel = sliderPanel;
	}

	public JLabel getZoomLabel()
	{
		return zoomLabel;
	}

	public void setZoomLabel(JLabel zoomLabel)
	{
		this.zoomLabel = zoomLabel;
	}

	public JSlider getZoomSlider()
	{
		return zoomSlider;
	}

	public void setZoomSlider(JSlider zoomSlider)
	{
		this.zoomSlider = zoomSlider;
	}

	public CorkboardPanel getCorkboardPanel()
	{
		return corkboardPanel;
	}

	public void setCorkboardPanel(CorkboardPanel corkboardPanel)
	{
		this.corkboardPanel = corkboardPanel;
	}
	
}
