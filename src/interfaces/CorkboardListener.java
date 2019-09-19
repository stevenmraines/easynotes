package interfaces;

import events.CardDeletedEvent;
import events.CardDuplicatedEvent;
import events.CardFlippedEvent;

public interface CorkboardListener
{
	
	public void cardDeleted(CardDeletedEvent e);
	
	public void cardDuplicated(CardDuplicatedEvent e);
	
	public void cardFlipped(CardFlippedEvent e);
	
}
