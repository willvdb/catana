package clientSide.guiThings.communication;

import java.util.List;

import clientSide.guiThings.base.*;

/**
 * Chat view interface
 */
public interface IChatView extends IView
{
	
	/**
	 * Sets the chat messages to be displayed in the view.
	 * 
	 * @param entries
	 *            The chat messages to display
	 */
	void setEntries(List<LogEntry> entries);
}

