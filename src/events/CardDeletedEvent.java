package events;

import java.util.EventObject;

public class CardDeletedEvent extends EventObject
{

	private static final long serialVersionUID = -2999886121068328290L;

	public CardDeletedEvent(Object source)
	{
		
		super(source);
		
	}

}
