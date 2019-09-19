package events;

import java.util.EventObject;

public class CardDuplicatedEvent extends EventObject
{

	private static final long serialVersionUID = 3120207407480241559L;

	public CardDuplicatedEvent(Object source)
	{
		
		super(source);

	}

}
