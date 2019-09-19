package events;

import java.util.EventObject;

public class CardFlippedEvent extends EventObject
{

	private static final long serialVersionUID = -207263445835695055L;

	public CardFlippedEvent(Object source)
	{
		
		super(source);

	}

}
