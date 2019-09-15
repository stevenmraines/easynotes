package easynotes.components;

import javax.swing.JLabel;

/*
 * This class is simply a JLabel that automatically wraps the text
 * passed to the setText method in HTML tags, for convenience.
 * 
 * This allows line wrapping with the JLabel text.
 */
public class JLabelHtml extends JLabel {
	private static final long serialVersionUID = 3438807806576374630L;

	@Override
	public void setText(String text) {
		super.setText("<html>" + text + "</html>");
	}
}
