package events;

import java.util.EventObject;

public class CardEditedEvent extends EventObject
{

	private static final long serialVersionUID = 4556248633339096033L;

	public CardEditedEvent(Object source)
	{
		
		super(source);
		
	}

}
