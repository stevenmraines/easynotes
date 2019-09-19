package events;

import java.util.EventObject;

public class CardAddedEvent extends EventObject
{

	private static final long serialVersionUID = -582929757494278377L;

	public CardAddedEvent(Object source)
	{
		
		super(source);
		
	}

}
